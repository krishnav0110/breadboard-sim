package com.kris.Frame;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;

import com.kris.MainEngine.Renderer;

public class Window extends JFrame{

    public JPanel titleBar;

    public Window(String title, int width, int height){

        this.setLocationRelativeTo(null);
        this.setLocation(0, 0);
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(width, height));
        this.setAlwaysOnTop(false);

        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setUndecorated(true);

        this.pack();
        this.setVisible(true);
    }

    public void createDESApp(Renderer renderer){
        
        JPanel mainArea = new JPanel();
        JPanel menu = new JPanel();
        JPanel close_btn = new JPanel();
        titleBar = new JPanel();

        this.add(titleBar);
        this.add(mainArea);
        this.add(menu);

        titleBar.add(close_btn);
        mainArea.add(renderer);

        titleBar.setBackground(Color.DARK_GRAY);
        titleBar.setBounds(0, 0, this.getWidth(), 30);
        

        close_btn.setBackground(titleBar.getBackground());
        close_btn.setBounds(titleBar.getWidth() - 40, 0, 40, titleBar.getHeight());

        mainArea.setBackground(Color.BLACK);
        mainArea.setBounds(0, 30, (int) (this.getWidth() * 0.8), this.getHeight() - 30);

        menu.setBackground(Color.GRAY);
        menu.setBounds((int) (this.getWidth() * 0.8), 30, (int) (this.getWidth() * 0.2), this.getHeight() - 30);

        renderer.setBounds(0, 0, mainArea.getWidth(), mainArea.getHeight());

        close_btn.addMouseListener(new MouseInputAdapter(){
            
            @Override
            public void mouseEntered(MouseEvent e){

                close_btn.setBackground(Color.RED);
            }

            @Override
            public void mouseExited(MouseEvent e){

                close_btn.setBackground(titleBar.getBackground());
            }
        });
    }
}