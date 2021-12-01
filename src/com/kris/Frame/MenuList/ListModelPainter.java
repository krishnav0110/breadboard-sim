package com.kris.Frame.MenuList;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

@SuppressWarnings({ "rawtypes"})
public class ListModelPainter extends JLabel implements ListCellRenderer {

    public ListModelPainter(){

        setOpaque(true);
        this.setFont(new Font("Helvetica", Font.PLAIN, 15));
        this.setHorizontalAlignment(JLabel.CENTER);
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
            boolean cellHasFocus) {

        Item item = (Item) value;
        this.setText(item.getName());

        if(isSelected){
            setBackground(Color.LIGHT_GRAY);
            setForeground(Color.BLACK);
        }
        if(!isSelected){
            setBackground(Color.GRAY);
            setForeground(Color.WHITE);
        }

        return this;
    }  
}