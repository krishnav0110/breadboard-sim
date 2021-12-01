package com.kris.MainEngine;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;

import com.kris.Managers.ComponentPlacer;
import com.kris.Managers.WirePlacer;
import com.kris.Objects.Object;

/* objects that needs to be rendered in the simulation is added via add(Object) function
 * 
 * the window also captures the Mouse Events and passes the position of the cursor to
 * the necessary individual objects in the scene which updates its state if they are clicked.
 */

public class Renderer extends Canvas {

    private LinkedList<Object> objects;
    private BufferStrategy bs;
    private Graphics g;
    
    public Renderer(){

        objects = new LinkedList<>();
        this.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        this.setBackground(new Color(20, 20, 20));
    }

    public void render(){

        bs = this.getBufferStrategy();
        
        if(bs == null){
            this.createBufferStrategy(2);
            return;
        }

        g = bs.getDrawGraphics();

        g.clearRect(0, 0, this.getWidth(), this.getHeight());

        for (Object object : objects) {
            object.render(g);
        }
        
        bs.show();
    }

    public void dispose(){
        g.dispose();
        bs.dispose();
    }

    public void add(Object object){
        objects.add(object);
    }
    
    public LinkedList<Object> getObjects(){
        return objects;
    }

    public void addManager(WirePlacer inputManager){
        inputManager.renderer = this;
        this.addMouseListener(inputManager);
        this.addMouseMotionListener(inputManager);
    }

    public void addManager(ComponentPlacer inputManager){
        inputManager.renderer = this;
        inputManager.init();
        this.addMouseListener(inputManager);
        this.addMouseMotionListener(inputManager);
    }
}