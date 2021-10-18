package com.kris.Managers;

import java.awt.Color;
import java.awt.event.MouseEvent;

import com.kris.MainEngine.Renderer;
import com.kris.Objects.BreadBoard;
import com.kris.Objects.Hole;
import com.kris.Objects.Wire;

public class DESAppMouseInputManager {

    public Renderer renderer;
    private Hole h1;
    
    public void mousePressed(MouseEvent e){

        int mx = e.getX(), my = e.getY();

        if(e.getButton() == MouseEvent.BUTTON1)
            h1 = getHoleClicked(mx, my);
    }

    public void mouseReleased(MouseEvent e){

        int mx = e.getX(), my = e.getY();

        if(e.getButton() == MouseEvent.BUTTON1){
        
            Hole h2 = getHoleClicked(mx, my); 
        
            if(h1 == h2)
                return;

            if(h1.isConnected || h2.isConnected)
                return;

            Wire wire = new Wire(Color.BLUE);
            wire.connect(h1, h2);
            renderer.add(wire);
        }
    }

    public void mouseClicked(MouseEvent e){

        int mx = e.getX(), my = e.getY();

        if(e.getButton() == MouseEvent.BUTTON3){

            Hole hole = getHoleClicked(mx, my);

            if(hole.isConnected){

                Wire wire;
                for(int i = 1; i < renderer.getObjects().size(); i++){

                    wire = (Wire) (renderer.getObjects().get(i));
                    
                    if(wire.getHole1() == hole || wire.getHole2() == hole){
                        
                        wire.disconnect();
                        renderer.getObjects().remove(wire);
                    }
                }
            }
        }
    }

    public Hole getHoleClicked(int mx, int my){

        BreadBoard board = (BreadBoard) (renderer.getObjects().get(0));

        if(mx > board.getX() && mx < board.getX() + board.getWidth()){
            if(my > board.getY() && my < board.getY() + board.getHeight()){

                for(int i = 0; i < board.getHoles().size(); i++){

                    if(board.getHoles().get(i).isClicked(mx, my)){

                        return board.getHoles().get(i);
                    }
                }
            }
        }
        return null;
    }
}