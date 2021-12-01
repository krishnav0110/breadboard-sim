package com.kris.Managers;

import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputAdapter;

import com.kris.Frame.Window;
import com.kris.MainEngine.Renderer;
import com.kris.Objects.*;
import com.kris.Objects.Object;

public class WirePlacer extends MouseInputAdapter {

    public Renderer renderer;

    private static Hole startHole;
    private static Hole endHole;
    private static Wire previewWire;
    private static int mouseX, mouseY;

    private boolean selecting = false;

    @Override
    public void mouseMoved(MouseEvent e){
        mouseX = e.getX(); mouseY = e.getY();
        updatePreviewWire();
    }

    @Override
    public void mouseClicked(MouseEvent e){
        mouseX = e.getX(); mouseY = e.getY();

        if(e.getButton() == MouseEvent.BUTTON1)
            placeWire();

        else if(e.getButton() == MouseEvent.BUTTON3)
            removeWire();
    }

    private void placeWire(){

        if(!checkSelectedValue())
            return;
        
        if(!selecting){
            startHole = getClickedHole();
            if(startHole == null)
                return;
            
            addPreviewWire();
        }
        else{
            endHole = getClickedHole();
            if(endHole == null)
                return;
            addWire();
        }
    }

    private void addWire(){

        Wire wire = new Wire(getWireColor());
        wire.connect(startHole, endHole);
        removePreviewWire();
        renderer.add(wire);
    }

    private void removeWire(){

        Hole hole = getClickedHole();
        if(hole == null)
            return;

        if(!hole.isConnected)
            return;

        Wire todeleteWire = null;
        for(Object object : renderer.getObjects()){

            if(object instanceof Wire){

                todeleteWire = (Wire) object;
                if(todeleteWire.getHole1() == hole || todeleteWire.getHole2() == hole)
                    break;
            }
        }
        if(todeleteWire != null){
            todeleteWire.disconnect();
            renderer.getObjects().remove(todeleteWire);
        }
    }

    private void addPreviewWire(){

        selecting = true;
        previewWire = new Wire(getPreviewWireColor());
        previewWire.setStartPosition(startHole.getX(), startHole.getY());
        previewWire.setEndPosition(startHole.getX(), startHole.getY());
        renderer.add(previewWire);
    }

    private void updatePreviewWire(){

        if(!selecting)
            return;
        
        previewWire.setEndPosition(mouseX, mouseY);
    }

    private void removePreviewWire(){
        startHole = endHole = null;
        selecting = false;

        if(renderer.getObjects().contains(previewWire));
            renderer.getObjects().remove(previewWire);
    }

    private Hole getClickedHole(){

        BreadBoard board = (BreadBoard) (renderer.getObjects().get(0));

        /* this loop checks for the holes on the breadboard
         */
        if(mouseX > board.getX() && mouseX < board.getX() + board.getWidth() &&
           mouseY > board.getY() && mouseY < board.getY() + board.getHeight()){

            for(Hole hole : board.getHoles()){
                if(hole.isClicked(mouseX, mouseY)){
                    return hole;
                }
            }
        }
        /* this loop checks for the holes which are not a part of the breadboard
         */
        else{
            for(Object object : renderer.getObjects()){
                if(object instanceof Hole){

                    if(((Hole) object).isClicked(mouseX, mouseY))
                        return (Hole) object;       
                }
            }
        }
        return null;
    }

    private Color getPreviewWireColor(){
        Color color = getWireColor();
        Color newColor = new Color(color.getRed(), color.getGreen(), color.getBlue(), 110);
        return newColor;
    }

    private Color getWireColor(){

        if(Window.list.getSelectedValue() == null)
            return Color.RED;
        else
            return Window.list.getSelectedValue().getColor();
    }

    private boolean checkSelectedValue(){

        if(Window.list.getSelectedValue() != null){
            if(Window.list.getSelectedValue().getName().endsWith("WIRE"))
                return true;
        }
        return false;
    }
}