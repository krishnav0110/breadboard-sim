package com.kris.Objects.Components.IC;

import com.kris.Objects.Bit;
import com.kris.Objects.BreadBoard;
import com.kris.Objects.Hole;
import com.kris.Objects.Components.Component;

import java.awt.Color;
import java.awt.Graphics;

public abstract class IC_Component extends Component{

    protected int pins;
    protected Bit pin[];

    public IC_Component(int pins){

        this.pins = pins;
        pin = new Bit[pins];

        x = y = 0;
        width = pins / 2 * Hole.TILE_WIDTH;
        height = Hole.TILE_WIDTH;

        for(int i = 0; i < pins; i++)
            pin[i] = new Bit();
    }

    @Override
    public void render(Graphics g){

        g.setColor(Color.DARK_GRAY);
        g.fillRect(x, y, width, height);
        g.setColor(Color.WHITE);
        g.drawString("IC" + id, x + width / 4, y + height * 3 / 4);
    }

    @Override
    public void connect(BreadBoard board, int index){

        Hole hole = board.getHoles().get(index);
        x = hole.getX();
        y = board.getY() + Hole.TILE_WIDTH * 9;

        int newIndex = index + 150;
        for(int i = pins - 1; i >= pins / 2; i--){
            hole = board.getHoles().get(index);
            pin[i] = hole.getBit();
            hole.isConnected = true;
            index += 5;
        }
        for(int i = 0; i < pins / 2; i++){
            hole = board.getHoles().get(newIndex);
            pin[i] = hole.getBit();
            hole.isConnected = true;
            newIndex += 5;
        }
    }

    public boolean isClicked(int mx, int my){

        if(mx > x && mx < x + width){
            if(my > y && my < y + height){

                return true;
            }
        }
        return false;
    }
}