package fr.tse.fise2.heapoverflow.authentication;

import fr.tse.fise2.heapoverflow.database.UserRow;
import fr.tse.fise2.heapoverflow.database.UsersTable;
import fr.tse.fise2.heapoverflow.interfaces.IUserObserver;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class UserAuthentication implements PasswordService {
    private static final transient Logger log = LoggerFactory.getLogger(UserAuthentication.class);
    private static Set<IUserObserver> userObservers = new HashSet<>();


    private static UserAuthentication userAuthentication;
    private static Subject currentUser = null;
    private final PasswordService passwordService;


    private UserAuthentication() {
        passwordService = new DefaultPasswordService();
        SaltRealm saltRealm = new SaltRealm();
        SecurityManager securityManager = new DefaultSecurityManager(saltRealm);
        SecurityUtils.setSecurityManager(securityManager);
        currentUser = SecurityUtils.getSubject();
    }

    public static void subscribe(IUserObserver userObserver) {
        userObservers.add(userObserver);
    }

    public static void unsubscribe(IUserObserver userObserver) {
        userObservers.remove(userObserver);
    }

    @NotNull
    public static UserAuthentication getUserAuthentication() {
        if (userAuthentication == null) {
            userAuthentication = new UserAuthentication();

        }
        return userAuthentication;
    }

    public static boolean isAuthenticated() {
        return currentUser != null && currentUser.isAuthenticated();
    }

    @Nullable
    public static User getUser() {
        if (!isAuthenticated()) {
            return null;
        }
        User user = null;
        try {
            // TODO DANGEROUS DO NOT USE USERNAME
            final UserRow userRow = UsersTable.findUserByUsername((String) currentUser.getPrincipal());
            user = getUser(userRow);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @NotNull
    private static User getUser(UserRow userRow) {
        return new User(userRow.getUsername(), userRow.getId(), userRow.getFirstName(), userRow.getLastName());
    }

    public void login(String username, String password) {
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            token.setRememberMe(true);
            try {
                currentUser.login(token);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (currentUser.isAuthenticated()) {
                for (IUserObserver observer : userObservers) {
                    observer.onLogin(username);
                }
            }
        }
    }

    public void logout() {
        currentUser.logout();
        for (IUserObserver observer : userObservers) {
            observer.onLogout();
        }
    }

    @Override
    public String encryptPassword(Object o) throws IllegalArgumentException {
        return passwordService.encryptPassword(o);
    }

    @Override
    public boolean passwordsMatch(Object o, String s) {
        return passwordService.passwordsMatch(o, s);
    }


}
