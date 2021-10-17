package com.kris.MainEngine;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import java.util.LinkedList;

import com.kris.Objects.Object;

public class Renderer extends Canvas {

    private LinkedList<Object> objects;
    private BufferStrategy bs;
    private Graphics g;
    
    public Renderer(){

        objects = new LinkedList<>();
    }

    public void render(){

        bs = this.getBufferStrategy();
        
        if(bs == null){
            this.createBufferStrategy(2);
            return;
        }

        g = bs.getDrawGraphics();

        g.clearRect(0, 0, this.getWidth(), this.getHeight());

        for(int i = 0; i < objects.size(); i++){

            objects.get(i).render(g);
        }
        
        g.dispose();
        bs.show();
    }

    public void dispose(){
        g.dispose();
        bs.dispose();
    }

    public void add(Object object){
        objects.add(object);
    }   
}