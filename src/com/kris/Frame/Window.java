package com.kris.Frame;

import java.awt.GraphicsEnvironment;
import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import com.kris.Frame.MenuList.List;

public class Window extends JFrame{

    private static Window window;
    public static TitleBar titleBar;
    public static JPanel scene;
    public static List list;

    private Window(String title){

        titleBar = new TitleBar(title);
        scene = new JPanel();
        list = new List();

        this.setTitle(title);
        this.setBackground(Color.BLACK);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setUndecorated(true);
        this.setLayout(null);

        /* setting out window frame to cover maximum space area of user display window
         */
        this.setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds());

        this.setVisible(true);
        initComponents();
    }

    /* implemeting singleton
     */
    public static Window createDESApp(){

        if(window == null)
            window = new Window("Digital Electronics Simulator");
        
        return window;
    }

    public void initComponents(){

        this.add(titleBar);
        this.add(scene);
        this.add(list);

        scene.setBackground(Color.BLACK);
        scene.setBounds(0, 30, (int) (this.getWidth() * 0.8), this.getHeight() - 30);

        list.init();

        /* the functionality of minimize button where the window is minimized by
         * clicking the button
         */
        titleBar.init();
        titleBar.minmizeBtn.addMouseListener(new MouseInputAdapter(){
            
            @Override
            public void mouseClicked(MouseEvent e){

                if(window.getExtendedState() == JFrame.NORMAL)
                    window.setExtendedState(JFrame.ICONIFIED);
                
                else
                    window.setExtendedState(JFrame.NORMAL);
            }
        });

        /* the functionality of close button is added to close the program
         */
        titleBar.closeBtn.addMouseListener(new MouseInputAdapter(){
            
            @Override
            public void mouseClicked(MouseEvent e){

                window.dispose();
                System.gc();
                System.exit(0);
            }
        });
    }
}