package com.kris.Objects;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.BasicStroke;

public class Wire extends Object {
    
    private int x1, y1, x2, y2;
    public Color color;

    private Hole hole1;
    private Hole hole2;

    public Wire(Color color){

        this.color = color;
    }

    public Hole getHole1(){ return hole1; }
    public Hole getHole2(){ return hole2; }

    /* x1, y1, x2, y2 are only used to render the wire connected to the holes
     */
    public void connect(Hole h1, Hole h2){
        
        hole1 = h1;
        hole2 = h2;
        
        hole1.isConnected = true;
        hole2.isConnected = true;

        x1 = hole1.getX() + Hole.TILE_WIDTH / 2;
        y1 = hole1.getY() + Hole.TILE_WIDTH / 2;
        x2 = hole2.getX() + Hole.TILE_WIDTH / 2;
        y2 = hole2.getY() + Hole.TILE_WIDTH / 2;
    }

    public void disconnect(){

        hole1.isConnected = false;
        hole2.isConnected = false;
        hole1.getBit().setValue(false);
        hole2.getBit().setValue(false);
        hole1 = null;
        hole2 = null;
    }

    @Override
    public void update(){

        /* checks if either of the holes connected are active, if yes
         * then both the holes are active
         * 
         * else both are set to false value
         */
        if(hole1.getBit().getValue() || hole2.getBit().getValue()){

            hole1.getBit().setValue(true);
            hole2.getBit().setValue(true);
        }
        else{

            hole1.getBit().setValue(false);
            hole2.getBit().setValue(false);
        }
    }

    @Override
    public void render(Graphics g){
        
        Graphics2D  g2d = (Graphics2D) g;

        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(5));
        g2d.drawLine(x1, y1, x2, y2);
    }
}