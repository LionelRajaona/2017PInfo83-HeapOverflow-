package fr.tse.fise2.heapoverflow.gui;

import fr.tse.fise2.heapoverflow.interfaces.IMarvelElement;
import fr.tse.fise2.heapoverflow.main.AppConfig;
import fr.tse.fise2.heapoverflow.marvelapi.MarvelRequest;
import fr.tse.fise2.heapoverflow.marvelapi.UrlBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class TemplatePreviewListRenderer extends JPanel {
    private static final Color selectionColor = new Color(3, 169, 244);
    protected final FavoriteButton favoriteButton;
    final JLabel cardTitle;
    private final JPanel mainPanel;
    private final JPanel cardHeaderPanel;
    private final JPanel cardBodyPanel;
    private final JPanel cardFooterPanel;
    private final TemplatePreviewListRenderer.ImagePanel imagePanel;
    protected Object data;
    private boolean selected;


    public TemplatePreviewListRenderer() {
        this.imagePanel = new TemplatePreviewListRenderer.ImagePanel();
        this.mainPanel = new JPanel();
        this.cardTitle = new JLabel("default");
        this.favoriteButton = new FavoriteButton();
        this.cardHeaderPanel = new JPanel();
        this.cardBodyPanel = new JPanel();
        this.cardFooterPanel = new JPanel();
        this.init();
    }

    private void init() {
        Dimension fixedDimension = new Dimension(298, 126);
        this.setMinimumSize(fixedDimension);
        this.setPreferredSize(fixedDimension);
        this.setMaximumSize(fixedDimension);
        this.setLayout(new BorderLayout());
        this.add(this.imagePanel, BorderLayout.WEST);
        this.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 0));

        this.mainPanel.setLayout(new BorderLayout());
        this.mainPanel.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0, Color.GRAY));

        this.mainPanel.setOpaque(true);
        this.mainPanel.setBackground(UIColor.MAIN_BACKGROUND_COLOR);

        // card header
        this.cardHeaderPanel.setOpaque(false);
        this.cardHeaderPanel.add(this.cardTitle);
        this.mainPanel.add(this.cardHeaderPanel, BorderLayout.NORTH);

        // card body
        this.cardBodyPanel.setOpaque(false);
        this.cardBodyPanel.add(new GradePanel());
        this.mainPanel.add(this.cardBodyPanel, BorderLayout.CENTER);

        // card footer
        this.cardFooterPanel.setOpaque(false);
        this.cardFooterPanel.add(new LibraryButton());
        this.cardFooterPanel.add(this.favoriteButton);
        this.cardFooterPanel.add(new ReadButton());
        this.mainPanel.add(cardFooterPanel, BorderLayout.SOUTH);

        this.add(mainPanel, BorderLayout.CENTER);

    }

    protected abstract void fillCardData();

    public abstract Object getData();

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        this.mainPanel.setBackground(this.selected ? selectionColor : Color.WHITE);
    }


    public JButton getFavoriteButton() {
        return favoriteButton;
    }

    @Override
    public void paintComponent(Graphics graphics) {
        this.fillCardData();

        if (this.selected) {
            this.mainPanel.setOpaque(true);
            this.mainPanel.setBackground(selectionColor);
        }
        super.paintComponent(graphics);

    }

    public final class ImagePanel extends JPanel {

        public ImagePanel() {
            Dimension fixedDimension = new Dimension(84, 126);
            this.setMinimumSize(fixedDimension);
            this.setPreferredSize(fixedDimension);
            this.setMaximumSize(fixedDimension);


        }

        @Override
        public void paintComponent(Graphics graphics) {
            try {
                final BufferedImage imageIcon = MarvelRequest.getImage(((IMarvelElement) data).getThumbnail(), UrlBuilder.ImageVariant.PORTRAIT_FANTASTIC, AppConfig.tmpDir);
                graphics.drawImage(imageIcon, 0, 0, 84, 126, null);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}