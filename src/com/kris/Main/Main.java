package com.kris.Main;

import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;

import com.kris.Frame.Window;
import com.kris.MainEngine.Engine;
import com.kris.MainEngine.Renderer;
import com.kris.Objects.BreadBoard;

public class Main {
    
    public static void main(String[] args){

        Window window = new Window("DES", 1240, 700);
        Engine engine = new Engine();
        Renderer renderer = engine.getRenderer();
        
        window.createDESApp(engine.getRenderer());
        
        engine.start();

        BreadBoard breadBoard = new BreadBoard();
        breadBoard.generateDefaultLayout();
        breadBoard.setCenterPositionRelativeTo(renderer);

        renderer.add(breadBoard);
        
        window.titleBar.getComponent(0).addMouseListener(new MouseInputAdapter(){
            
            @Override
            public void mouseClicked(MouseEvent e){

                engine.isRunning = false;
                System.gc();
                window.dispose();
            }
        });
    }
}