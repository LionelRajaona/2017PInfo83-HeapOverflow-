package fr.tse.fise2.heapoverflow.gui;

import fr.tse.fise2.heapoverflow.interfaces.ISelectionChangedListener;
import fr.tse.fise2.heapoverflow.marvelapi.Character;
import fr.tse.fise2.heapoverflow.marvelapi.Comic;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Right panel
 *
 * @author Darios DJIMADO
 * @author Ismail BERRADA
 */
public class UIExtraComponent implements Observer {
    private final JPanel rightWrapperPanel;
    private final JPanel rightContainerPanel;
    private final JLabel rightTitleLabel;

    private ISelectionChangedListener selectionChangedListener;

    UIExtraComponent(JPanel rightWrapperPanel) {
        this.rightWrapperPanel = rightWrapperPanel;

        this.rightWrapperPanel.setBorder(BorderFactory.createLineBorder(UIColor.HEADER_SHADOW_COLOR));


        this.rightWrapperPanel.setLayout(new BorderLayout());
        this.rightContainerPanel = new JPanel();
        this.rightContainerPanel.setLayout(new BorderLayout());

        this.rightContainerPanel.setBackground(UIColor.MAIN_BACKGROUND_COLOR);


        this.rightWrapperPanel.setBackground(UIColor.MAIN_BACKGROUND_COLOR);


        JPanel rightTitlePanel = new JPanel();


        this.rightWrapperPanel.add(rightTitlePanel, BorderLayout.NORTH);
        this.rightWrapperPanel.add(this.rightContainerPanel, BorderLayout.CENTER);

        this.rightTitleLabel = new JLabel("Nothing to show");
        rightTitlePanel.setBackground(UIColor.PRIMARY_COLOR);
        this.rightTitleLabel.setForeground(UIColor.MAIN_BACKGROUND_COLOR);

        rightTitlePanel.add(this.rightTitleLabel);

    }

    public void setSelectionChangedListener(ISelectionChangedListener selectionChangedListener) {
        this.selectionChangedListener = selectionChangedListener;
    }

    public void setResultsComics(Comic[] comics) {
        this.rightTitleLabel.setText("Appears in the same series");
        this.rightContainerPanel.removeAll();
        if (comics != null) {
            JList<Comic> jList = new JList<>(comics);
            jList.setCellRenderer(new ComicsListRenderer());
            jList.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            jList.addListSelectionListener((ListSelectionEvent e) -> {
                if (e.getValueIsAdjusting()) {
                    this.selectionChangedListener.showComic(jList.getSelectedValue());
                }
            });

            JScrollPane scrollPane = new CustomScrollPane(jList,
                    JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


            this.rightContainerPanel.add(scrollPane, BorderLayout.CENTER);
        }
        this.rightContainerPanel.revalidate();
        this.rightContainerPanel.repaint();
    }


    public void setResultsCharacters(Character[] characters) {
        this.rightTitleLabel.setText("Appears in the same comic");
        this.rightContainerPanel.removeAll();
        if (characters != null) {
            JList<Character> jList = new JList<>(characters);
            jList.setCellRenderer(new CharactersListRenderer());
            jList.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            jList.addListSelectionListener((ListSelectionEvent e) -> {
                if (e.getValueIsAdjusting()) {
                    this.selectionChangedListener.showCharacter(jList.getSelectedValue());
                }
            });
            JScrollPane scrollPane = new CustomScrollPane(jList,
                    JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


            this.rightContainerPanel.add(scrollPane, BorderLayout.CENTER);
        }

        this.rightWrapperPanel.revalidate();
        this.rightWrapperPanel.repaint();
    }

    public JPanel getRightWrapperPanel() {
        return rightWrapperPanel;
    }

    /**
     * This method is called whenever the observed object is changed. An
     * application calls an <tt>Observable</tt> object's
     * <code>notifyObservers</code> method to have all the object's
     * observers notified of the change.
     *
     * @param o   the observable object.
     * @param arg an argument passed to the <code>notifyObservers</code>
     */
    @Override
    public void update(Observable o, Object arg) {
        this.rightWrapperPanel.repaint();
    }
}
