package ui.pages;

import javax.swing.*;
import java.awt.*;

public class CollectionUI extends JPanel {
    private static final int WIDTH = 100;
    private static final int HEIGHT = 30;
    private static final int TEXT_INDENT = 30;
    private static final String data = "ACED";
    private Color fillColor;

    /**
     * Constructor creates interface to display status of alarm
     */
    public CollectionUI() {
        fillColor = new Color(205, 239, 255);
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(fillColor);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.BLACK);
        g.drawChars(data.toCharArray(),
                0,
                data.length(),
                TEXT_INDENT,
                2 * getHeight() / 3);
    }

}
