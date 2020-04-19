package domain;

import javax.swing.*;
import java.awt.*;

public class BackgroundPanel2 extends JPanel {

    private int startx;
    private int starty;
    private int pwidth;
    private int pheight;
    private int lineInc;
    private Color bgColor;

    public BackgroundPanel2(int startx,int starty,int width,int height,int lineInc,Color color){
        this.startx=startx;
        this.starty=starty;
        this.pwidth=width;
        this.pheight=height;//limits until where to draw the line
        this.lineInc=lineInc;
        this.bgColor =color;
        this.setBackground(bgColor);
        this.setBounds(startx, starty, width, height);
        //this.setOpaque(true);
        System.out.println(this.toString());

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(int i=0;i<6;i++){
            int posx=(int)(startx+(i+1.0)*lineInc);
            g.drawLine(posx,starty,posx,pheight);
        }
        g.drawLine(200,0,200,600);
        g.drawLine(400,0,400,600);
        g.drawLine(600,0,600,600);
        g.drawLine(800,0,800,600);
    }

    public int getStartx() {
        return startx;
    }

    public void setStartx(int startx) {
        this.startx = startx;
    }

    public int getStarty() {
        return starty;
    }

    public void setStarty(int starty) {
        this.starty = starty;
    }

    public int getPwidth() {
        return pwidth;
    }

    public void setPwidth(int pwidth) {
        this.pwidth = pwidth;
    }

    public int getPheight() {
        return pheight;
    }

    public void setPheight(int pheight) {
        this.pheight = pheight;
    }

    public int getLineInc() {
        return lineInc;
    }

    public void setLineInc(int lineInc) {
        this.lineInc = lineInc;
    }

    public Color getBgColor() {
        return bgColor;
    }

    public void setBgColor(Color bgColor) {
        this.bgColor = bgColor;
    }



}
