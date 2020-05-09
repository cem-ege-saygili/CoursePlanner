package view;

import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {

    int width;
    int height;
    int startY;
    int endY;
    int startX;
    int endX;

    public BackgroundPanel(int startY, int endY){
        this.startX = 100;
        this.width = 1000;
        this.startY = startY;
        this.endY = endY;
        this.endX = startX + width;
        this.height = endY - startY;
        this.setBackground(Color.WHITE);
        this.setBounds(startX, startY, width, height);
        this.setOpaque(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawLine(200,0,200,height);
        g.drawLine(400,0,400,height);
        g.drawLine(600,0,600,height);
        g.drawLine(800,0,800,height);

    }
}
