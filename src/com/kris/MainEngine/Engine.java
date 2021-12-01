package com.kris.MainEngine;

import java.util.ConcurrentModificationException;

import com.kris.Objects.Object;

/* generates a new thread for updating and rendering the simulation part containing the breadboard.
 *
 * start funciton is called to start updating the objects states at particular intervals.
 * By default, the interval is set to 30 times per second.
 * 
 * To stop this thread, the field value 'isRunning' should be set to false.
 */

public class Engine implements Runnable {

    private Renderer renderer;
    public boolean isRunning = false;
    private Thread thread;
    private static final float FPS_CAP = (float)(1.0 / 30);
    public int testTime = 0;
    
    public Engine(){

        renderer = new Renderer();
        thread = new Thread(this);
    }

    public Renderer getRenderer(){
        return renderer;
    }

    public void start(){

        if(isRunning)
            return;

        isRunning = true;
        thread.start();
        System.out.println("starting");
    }

    public void stop(){

        if(!isRunning)
            return;

        isRunning = false;

        try{
            thread.join();
        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        long currentTime = System.nanoTime();
        long elapsedTime = currentTime;
        double delta = 0.0;
        long timer = System.currentTimeMillis();
        
        while(isRunning){

            currentTime = System.nanoTime();
            delta += (currentTime - elapsedTime) / 1000000000.0;
            elapsedTime = currentTime;

            if(delta >= FPS_CAP){

                //update
                try{
                    for (Object object : renderer.getObjects()) {
                        object.update();
                    }
                    renderer.render();
                } catch (ConcurrentModificationException e){
                    e.printStackTrace();
                }

                testTime++;
                delta -= FPS_CAP;
            }
            else{

                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if(System.currentTimeMillis() - timer >= 1000){
                testTime = 0;
                timer = System.currentTimeMillis();
            }
        }   
    }
}