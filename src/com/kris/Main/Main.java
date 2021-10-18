package com.kris.Main;

import javax.swing.event.MouseInputAdapter;

import java.awt.Color;
import java.awt.event.MouseEvent;

import com.kris.Frame.Window;
import com.kris.MainEngine.Engine;
import com.kris.Objects.BreadBoard;
import com.kris.Objects.Wire;

public class Main {
    
    public static void main(String[] args){

        Window window = Window.createDESApp();
        Engine engine = new Engine();

        /* the renderer is set to cover the whole mainArea space in the window.
         */
        window.mainArea.add(engine.getRenderer());
        engine.getRenderer().setBounds(0, 0, window.mainArea.getWidth(), window.mainArea.getHeight());

        /* breadBoard general layout is intilized and the postition is set in the center 
         * of the renderer
         * the board is added in the list of the objects of the renderer to be displayed.
         */
        BreadBoard board = new BreadBoard();
        board.generateDefaultLayout();
        board.setCenterPositionRelativeTo(engine.getRenderer());

        engine.getRenderer().add(board);

        engine.getRenderer().add(new Wire(Color.BLUE));
        engine.getRenderer().add(new Wire(Color.RED));

        ((Wire) engine.getRenderer().getObjects().get(1)).connect(board.getHoles().get(1), board.getHoles().get(361));
        ((Wire) engine.getRenderer().getObjects().get(2)).connect(board.getHoles().get(32), board.getHoles().get(392));
        

        /////////////////////////////////////////////////////////////////////////////////////
        engine.start();

        /* when the close button is clicked, the Engine thread is stop and then window is disposed
         * 
         * after this the system is made to exit.
         */
        window.titleBar.closeBtn.addMouseListener(new MouseInputAdapter(){
            
            @Override
            public void mouseClicked(MouseEvent e){

                engine.isRunning = false;
                window.dispose();
                System.gc();
                System.exit(0);
            }
        });
    }
}