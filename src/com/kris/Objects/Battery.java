package com.kris.Objects;

import com.kris.Loader.BufferedImageLoader;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Battery extends Object {
    
    private int x, y, width, height;
    public Hole positive_terminal;
    public Hole negative_terminal;
    private BufferedImage image;

    public Battery(){

        x = 50;
        y = 500;
        width = 100;
        height = 2 * Hole.TILE_WIDTH;

        negative_terminal = new Hole(x + width - Hole.PADDING, y, 0, 0, new Bit());
        positive_terminal = new Hole(x + width - Hole.PADDING, y + height - Hole.TILE_WIDTH, 0, 0, new Bit());

        positive_terminal.getBit().setValue(Bit.Value.TRUE);
        negative_terminal.getBit().setValue(Bit.Value.FALSE);

        image = BufferedImageLoader.loadImage("src\\res\\components\\Battery.png");
    }

    @Override
    public void update(){

        positive_terminal.getBit().setValue(Bit.Value.TRUE);
        negative_terminal.getBit().setValue(Bit.Value.FALSE);
    }

    @Override
    public void render(Graphics g){

        //g.setColor(Color.ORANGE);
        //g.fillRect(x, y, width, height);
        g.drawImage(image, x, y - 10, null);
    }
}