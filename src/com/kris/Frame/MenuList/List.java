package com.kris.Frame.MenuList;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

@SuppressWarnings({ "rawtypes"})
public class List extends JPanel{

    private ListModel model;
    private ListModelPainter painter;
    private JList list;
    private JScrollPane scroll;
    
    @SuppressWarnings("unchecked")
    public List(){
        model = new ListModel();
        painter = new ListModelPainter();
        list = new JList(model);
        scroll = new JScrollPane(list);

        this.setBackground(Color.DARK_GRAY);
    }

    @SuppressWarnings("unchecked")
    public void init(){

        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        this.setBounds((int) (getParent().getWidth() * 0.8), 30, (int) (getParent().getWidth() * 0.2), getParent().getHeight() - 30);

        list.setCellRenderer(painter);
        list.setFixedCellWidth(getWidth() - 20);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        this.add(scroll);
        int visibleRowCount = 5;
        int cellHeight = getHeight() / visibleRowCount;
        list.setFixedCellHeight(cellHeight);
        list.setVisibleRowCount(visibleRowCount);
    }

    @SuppressWarnings("unchecked")
    public void addItem(Item item){
        model.addElement(item);
    }

    public Item getSelectedValue(){ return (Item) list.getSelectedValue(); }
}