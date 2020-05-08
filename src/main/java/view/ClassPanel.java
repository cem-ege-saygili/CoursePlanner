package view;

import javax.swing.*;
import java.awt.*;

public class ClassPanel extends JPanel {

    public ClassPanel(int x, int y, int width, int height, String classComponent) {


        Color LEC_color = Color.ORANGE;
        Color PRB_DIS_color = Color.PINK;
        Color LAB_color = Color.lightGray;
        Color default_color = Color.CYAN;

        Color background_color;

        if(classComponent.equals("LEC")){
            background_color = LEC_color;
        }else if(classComponent.equals("PRB") || classComponent.equals("DIS")){
            background_color = PRB_DIS_color;
        }else if(classComponent.equals("LAB")){
            background_color = LAB_color;
        }else{
            background_color = default_color;
        }

        this.setBackground(background_color);
        this.setBounds(x, y, width, height);
        this.setOpaque(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
