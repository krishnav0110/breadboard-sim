package com.kris.Frame;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JLabel;

public class TitleBar extends JLabel {

    public Button closeBtn;
    
    public TitleBar(String title){

        closeBtn = new Button();
        this.setText(title);
        this.setBackground(Color.DARK_GRAY);
        this.setForeground(Color.WHITE);
        this.setOpaque(true);
        this.add(closeBtn);
    }

    public void init(){

        this.setBounds(0, 0, this.getParent().getWidth(), 30);
        this.setFont(new Font("Helvetica", Font.PLAIN, 15));
        this.setHorizontalAlignment(JLabel.CENTER);

        closeBtn.setBounds(this.getWidth() - 50, 0, 50, this.getHeight());
    }
}