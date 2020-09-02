package com.course.components;

import javax.swing.*;
import java.awt.*;

/**
 * @author WangDebao
 * @create 2020-08-30 15:51
 */
public class BackGroundPanel extends JPanel {

    /**
     * 声明图片
     */
    private Image backIcon;

    public BackGroundPanel(Image backIcon){
        this.backIcon = backIcon;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backIcon, 0, 0,this.getWidth(),this.getHeight(), null);
    }
}
