package com.kris.Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Component;

import java.util.ArrayList;

public class BreadBoard extends Object{
    
    public static final int PADDING = 20;
    public static final int CENTER = 1;

    private int x;
    private int y;
    private int width;
    private int height;

    public ArrayList<Hole> holes;

    public BreadBoard(){

        x = 0;
        y = 0;
        width = 0;
        height = 0;
        holes = new ArrayList<>(0);
    }
    
    @Override
    public void render(Graphics g){

        g.setColor(new Color(220, 220, 220));
        g.fillRect(x, y, width, height);

        g.setColor(new Color(150, 150, 150));
        g.fillRect(x, y + 2 * Hole.TILE_WIDTH + PADDING, width, Hole.TILE_WIDTH);
        g.fillRect(x, y + (height - Hole.TILE_WIDTH) / 2, width, Hole.TILE_WIDTH);
        g.fillRect(x, y + 14 * Hole.TILE_WIDTH + PADDING, width, Hole.TILE_WIDTH);

        g.setColor(Color.RED);
        g.fillRect(x + 20, y + PADDING, width - 40, 2);
        g.fillRect(x + 20, y + 15 * Hole.TILE_WIDTH + PADDING, width - 40, 2);

        g.setColor(Color.BLUE);
        g.fillRect(x + 20, y + 2 * Hole.TILE_WIDTH + PADDING, width - 40, 2);
        g.fillRect(x + 20, y + height - PADDING, width - 40, 2);

        for(int i = 0; i < holes.size(); i++)
            holes.get(i).render(g);
    }

    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }

    public void setCenterPositionRelativeTo(Component c){

        this.x = (c.getWidth() - width) / 2;
        this.y = (c.getHeight() - height) / 2;
        
        recalculateHolesPosition();
    }

    private void recalculateHolesPosition(){

        int new_x, new_y;

        for(int i = 0; i < holes.size(); i++){

            new_x = this.x + holes.get(i).getX();
            new_y = this.y + holes.get(i).getY();

            holes.get(i).setPosition(new_x, new_y);
        }
    }

    public void generateDefaultLayout(){

        width = 30 * Hole.TILE_WIDTH + 2 * PADDING;
        height = 17 * Hole.TILE_WIDTH + 2 * PADDING;

        //first two rows
        generateConnectedHoles(0, 1, 1, 2, 30, false);

        //next five rows
        generateConnectedHoles(1, 3, 1, 5, 30, true);

        //next five rows
        generateConnectedHoles(2, 8, 1, 5, 30, true);

        //last two rows
        generateConnectedHoles(3, 13, 1, 2, 30, false);
    }

    private void generateConnectedHoles(int y_off, int row, int col, int rows, int cols, boolean VerticallyConnected){

        int x = 0, y = 0;
        rows = rows + row - 1;
        cols = cols + col - 1;

        if(VerticallyConnected){

            for(int i = col; i <= cols; i++){

                Bit bit = new Bit();

                for(int j = row; j <= rows; j++){

                    x = PADDING + (i - 1) * Hole.TILE_WIDTH;
                    y = PADDING + (j - 1) * Hole.TILE_WIDTH + y_off * Hole.TILE_WIDTH;

                    holes.add(new Hole(x, y, j, i, bit));
                }
            }
        }
        else{

            for(int i = row; i <= rows; i++){

                Bit bit = new Bit();

                for(int j = col; j <= cols; j++){

                    x = PADDING + (j - 1) * Hole.TILE_WIDTH;
                    y = PADDING + (i - 1) * Hole.TILE_WIDTH + y_off * Hole.TILE_WIDTH;

                    holes.add(new Hole(x, y, i, j, bit));
                }
            }
        }
    }

    @Override
    public void finalize(){

        holes.clear();
    }
}