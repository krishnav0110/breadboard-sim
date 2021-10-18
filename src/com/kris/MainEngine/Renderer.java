package com.kris.MainEngine;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;

import java.util.LinkedList;

import javax.swing.event.MouseInputListener;

import com.kris.Managers.DESAppMouseInputManager;
import com.kris.Objects.Object;

/* objects needs to be rendered in the simulation is added via add(Object) function
 * 
 * the window also captures the Mouse Events and passes the position of the cursor to
 * the necessary individual objects in the scene which updates its state if they are clicked.
 */

public class Renderer extends Canvas implements MouseInputListener {

    private LinkedList<Object> objects;
    private BufferStrategy bs;
    private Graphics g;

    private DESAppMouseInputManager inputManager;

    public void setManager(DESAppMouseInputManager inputManager){
        this.inputManager = inputManager;
        inputManager.renderer = this;
    }
    
    public Renderer(){

        objects = new LinkedList<>();
        this.addMouseListener(this);
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
    
    public LinkedList<Object> getObjects(){
        return objects;
    }

    /* passes the cursor position to clickable objects in the scene
     *
     * those objects check themselves if they are clicked and if so they update their state.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        
        inputManager.mouseClicked(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
        inputManager.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
        inputManager.mouseReleased(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        
        
    }
}