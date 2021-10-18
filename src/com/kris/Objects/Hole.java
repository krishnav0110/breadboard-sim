package com.kris.Objects;

import java.awt.Graphics;

import java.awt.Color;

/* Holes on breadboard will also act as tiles to capture Mouse Events
 */
public class Hole extends Object {
    
    public static final int PADDING = 5;
    private static final int WIDTH = 10;
    public static final int TILE_WIDTH = PADDING * 2 + WIDTH;
    
    private int x;
    private int y;

    private int row;
    private int col;

    private Bit bit;

    public Hole(int x, int y, int row, int col, Bit bit){

        this.x = x;
        this.y = y;
        this.row = row;
        this.col = col;
        this.bit = bit;
    }

    public void mouseClicked(int mx, int my){

        if(mx > x && mx < x + TILE_WIDTH){
            if(my > y && my < y + TILE_WIDTH){

                if(bit.getValue())
                    bit.setValue(false);
                else
                    bit.setValue(true);
            }
        }
    }

    @Override
    public void render(Graphics g){

        if(bit.getValue())
            g.setColor(Color.GREEN);
        else
            g.setColor(Color.RED);
        
        g.fillRect(x + PADDING, y + PADDING, WIDTH, WIDTH);
    }

    public int getRow(){
        return row;
    }
    public int getCol(){
        return col;
    }
    public void setRow(int row){
        this.row = row;
    }
    public void setCol(int col){
        this.col = col;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }
    public Bit getBit(){
        return bit;
    }
}