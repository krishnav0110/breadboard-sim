package com.kris.Frame;

import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.event.MouseInputAdapter;

public class Button extends JLabel{
 
    private ImageIcon icon;

    public Button(){

        icon = new ImageIcon("src/res/closeBtn.png");
        this.setIcon(icon);
        this.setBackground(Color.DARK_GRAY);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.CENTER);
        this.setOpaque(true);

        this.addMouseListener(new MouseInputAdapter(){

            @Override
            public void mouseEntered(MouseEvent e){

                toggle();
            }

            @Override
            public void mouseExited(MouseEvent e){

                toggle();
            }
        });
    }

    private void toggle(){

        if(this.getBackground() == Color.DARK_GRAY)
            this.setBackground(Color.RED);
        else
            this.setBackground(Color.DARK_GRAY);
    }
}