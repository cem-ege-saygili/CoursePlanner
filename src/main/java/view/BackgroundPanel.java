package view;

import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {

    public BackgroundPanel(){
        this.setBackground(Color.WHITE);
        this.setBounds(100, 100, 1000, 600);
        this.setOpaque(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawLine(200,0,200,600);
        g.drawLine(400,0,400,600);
        g.drawLine(600,0,600,600);
        g.drawLine(800,0,800,600);

    }
}
