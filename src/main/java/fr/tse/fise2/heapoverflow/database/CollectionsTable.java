package fr.tse.fise2.heapoverflow.database;

import org.jetbrains.annotations.NotNull;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CollectionsTable {
    public static void insertCollection(String title, String description) throws SQLException {
        PreparedStatement statement = ConnectionDB.getInstance()
                .getConnection()
                .prepareStatement("INSERT INTO collections(title,description)" +
                        " VALUES (?,?)");
        statement.setString(1, title);
        statement.setString(2, description);

        statement.execute();
    }

    public static CollectionsRow findCollection(int id) throws SQLException {
        PreparedStatement statement = ConnectionDB.getInstance()
                .getConnection()
                .prepareStatement("SELECT * FROM collections WHERE COLLECTION_ID = ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        CollectionsRow collectionsRow = null;
        while (resultSet.next()) {
            collectionsRow = new CollectionsRow(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4));
        }
        return collectionsRow;
    }

    @NotNull
    public static List<CollectionsRow> findCollections() throws SQLException {
        PreparedStatement statement = ConnectionDB.getInstance()
                .getConnection()
                .prepareStatement("SELECT * FROM collections");
        ResultSet resultSet = statement.executeQuery();
        List<CollectionsRow> collectionsRows = new ArrayList<>();
        while (resultSet.next()) {
            collectionsRows.add(new CollectionsRow(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4)));
        }
        return collectionsRows;
    }


    public static boolean existCollectionById(int id) throws SQLException {
        boolean found = false;
        PreparedStatement preparedStatement = ConnectionDB.getInstance()
                .getConnection()
                .prepareStatement("SELECT COUNT(*) FROM COLLECTIONS WHERE COLLECTION_ID = ? ");

        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            if (resultSet.getInt(1) > 0) {
                found = true;
                System.out.println(id);
            }
        }
        return found;
    }
    /*
    public static boolean existCollectionByTitle(String title) throws SQLException {
        boolean found = false;
        PreparedStatement preparedStatement = ConnectionDB.getInstance()
                .getConnection()
                .prepareStatement("SELECT COUNT(*) FROM COLLECTIONS WHERE TITLE = ? ");

        preparedStatement.setString(1, title);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            if (resultSet.getString(1) == title) {
                found = true;
                System.out.println(title);
            }
        }
        return found;
    }
    */
}
