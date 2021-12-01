package com.kris.Frame;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JLabel;

/* generates the custom titlebar for the app with the close button
 * 
 * this class is made so that titleBar generated is independent of the system on 
 * which it is running.
 *
 * the functionality of the close button is added in the Window class to
 * clean up all the resources and then exit.
 */
public class TitleBar extends JLabel {

    public Button minmizeBtn;
    public Button closeBtn;
    
    public TitleBar(String title){

        closeBtn = new Button("src/res/assets/closeBtn.png", Color.RED);
        minmizeBtn = new Button("src/res/assets/minmizeBtn.png", Color.GRAY);
        this.setText(title);
        this.setBackground(Color.DARK_GRAY);
        this.setForeground(Color.WHITE);
        this.setOpaque(true);
        this.add(closeBtn);
        this.add(minmizeBtn);
    }

    public void init(){

        this.setBounds(0, 0, this.getParent().getWidth(), 30);
        this.setFont(new Font("Helvetica", Font.PLAIN, 15));
        this.setHorizontalAlignment(JLabel.CENTER);

        closeBtn.setBounds(this.getWidth() - 50, 0, 50, this.getHeight());

        minmizeBtn.setBounds(this.getWidth() - 100, 0, 50, this.getHeight());
    }
}