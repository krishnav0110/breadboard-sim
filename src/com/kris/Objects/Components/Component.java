package com.kris.Objects.Components;

import com.kris.Objects.BreadBoard;
import com.kris.Objects.Object;

public class Component extends Object {

    protected int x, y, width, height;
    protected String id;
    
    public boolean isClicked(int mx, int my){

        if(mx > x && mx < x + width){
            if(my > y && my < y + height){
                return true;
            }
        }
        return false;
    }

    public void connect(BreadBoard board, int index){};

    public void setX(int x){ this.x = x; }
    public void setY(int y){ this.y = y; }
    public int getX(){ return x; }
    public int getY(){ return y; }
    public int getWidth(){ return width; }
    public int getHeight(){ return height; }
}