package com.kris.Frame;

import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.event.MouseInputAdapter;

public class Button extends JLabel{
 
    private ImageIcon icon;
    private Color color;

    /* the path specifies the relative path to the icon displayed.
     *
     * the color is the color of the background when mouse is hovered on the icon.
     */
    public Button(String path, Color color){

        this.color = color;
        icon = new ImageIcon(path);
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
            this.setBackground(color);
        else
            this.setBackground(Color.DARK_GRAY);
    }
}