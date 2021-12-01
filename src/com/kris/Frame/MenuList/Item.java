package com.kris.Frame.MenuList;

import java.awt.Color;

import javax.swing.ImageIcon;

public class Item {
    
    private String name;
    private ImageIcon icon;
    private Color color;

    public Item(String name, String path){
        this.name = name;
        this.icon = new ImageIcon(path);
        color = Color.BLACK;
    }

    public Item(String name, String path, Color color){
        this.name = name;
        this.icon = new ImageIcon(path);
        this.color = color;
    }

    public String getName(){ return name; }
    public void setColor(Color color){ this.color = color; }
    public Color getColor(){ return color; }
}