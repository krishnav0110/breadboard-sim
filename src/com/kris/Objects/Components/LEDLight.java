package com.kris.Objects.Components;

import com.kris.Loader.BufferedImageLoader;
import com.kris.Objects.Bit;
import com.kris.Objects.BreadBoard;
import com.kris.Objects.Hole;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.image.BufferedImage;

public class LEDLight extends Component {
 
    private int r;
    private Bit pin1, pin2;

    private BufferedImage image;
    
    public LEDLight(){

        x = y = 0;
        width = 2 * Hole.TILE_WIDTH;
        height = 2 * Hole.TILE_WIDTH;
        r = 30;
        image = BufferedImageLoader.loadImage("src\\res\\components\\LED.png");
    }

    @Override
    public void render(Graphics g){

        g.drawImage(image, x, y, null);

        Graphics2D g2d = (Graphics2D) g;

        if(pin1.getValue() == Bit.Value.TRUE && pin2.getValue() == Bit.Value.FALSE){
            g2d.setColor(new Color(255, 0, 0, 30));
            g2d.setStroke(new BasicStroke(5));
            int x1 = x + Hole.TILE_WIDTH, y1 = y + Hole.TILE_WIDTH;
            int x2, y2;
            for(int angle = 0; angle <= 360; angle += 5){

                x2 = x1 + (int) (r * Math.cos(Math.toRadians(angle)));
                y2 = y1 + (int) (r * Math.sin(Math.toRadians(angle)));
                g2d.drawLine(x1, y1, x2, y2);
            }
        }       
    }

    @Override
    public void connect(BreadBoard board, int index){

        Hole hole = board.getHoles().get(index);
        this.pin1 = hole.getBit();
        hole.isConnected = true;
        hole = board.getHoles().get(index + 5);
        this.pin2 = hole.getBit();
        hole.isConnected = true;

        this.x = hole.getX() - Hole.TILE_WIDTH;
        this.y = hole.getY() + Hole.TILE_WIDTH / 2 - this.height;
    }
}