package com.kris.Frame;

import java.awt.GraphicsEnvironment;
import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

public class Window extends JFrame{

    private static Window window;
    public TitleBar titleBar;
    public JPanel mainArea;
    public JPanel menu;

    private Window(String title){

        titleBar = new TitleBar(title);
        mainArea = new JPanel();
        menu = new JPanel();

        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setUndecorated(true);
        this.setLayout(null);

        this.setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds());

        this.setVisible(true);
        initComponents();
    }

    public static Window createDESApp(){

        if(window == null)
            window = new Window("Digital Electronics Simulator");
        
        return window;
    }

    public void initComponents(){

        this.add(titleBar);
        this.add(mainArea);
        this.add(menu);

        titleBar.init();

        mainArea.setBackground(Color.BLACK);
        mainArea.setBounds(0, 30, (int) (this.getWidth() * 0.8), this.getHeight() - 30);

        menu.setBackground(Color.GRAY);
        menu.setBounds((int) (this.getWidth() * 0.8), 30, (int) (this.getWidth() * 0.2), this.getHeight() - 30);
        menu.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));

        titleBar.minmizeBtn.addMouseListener(new MouseInputAdapter(){
            
            @Override
            public void mouseClicked(MouseEvent e){

                if(window.getExtendedState() == JFrame.NORMAL)
                    window.setExtendedState(JFrame.ICONIFIED);
                
                else
                    window.setExtendedState(JFrame.NORMAL);
            }
        });
    }
}