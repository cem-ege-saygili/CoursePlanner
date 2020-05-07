package view;

import javax.swing.*;
import java.awt.*;

public class ClassPanel extends JPanel {

    public ClassPanel(int x, int y, int width, int height) {
        this.setBackground(Color.CYAN);
        this.setBounds(x, y, width, height);
        this.setOpaque(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
