package com.kris.Managers;

import com.kris.Frame.Window;
import com.kris.Frame.MenuList.Item;
import com.kris.MainEngine.Renderer;
import com.kris.Objects.*;
import com.kris.Objects.Object;
import com.kris.Objects.Components.*;
import com.kris.Objects.Components.IC.*;

import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputAdapter;

public class ComponentPlacer extends MouseInputAdapter {
    
    public Renderer renderer;
    private BreadBoard board;

    private Component previewComponent;
    private int mouseX, mouseY;
    private boolean selecting = false;

    public void init(){
        board = (BreadBoard) renderer.getObjects().get(0);
    }

    @Override
    public void mouseMoved(MouseEvent e){
        mouseX = e.getX(); mouseY = e.getY();
        updatePreviewComponent();
    }

    @Override
    public void mouseClicked(MouseEvent e){
        mouseX = e.getX(); mouseY = e.getY();

        if(e.getButton() == MouseEvent.BUTTON1)
            placeComponent();
        
        else if(e.getButton() == MouseEvent.BUTTON3)
            removeComponent();
    }

    private void placeComponent(){

        if(!checkSelectedValue())
            return;

        if(!selecting){
            placePreviewComponent();
            return;
        }
        Component newComponent = previewComponent;
        newComponent.connect(board, getSelectedHoleIndex());
        previewComponent = null;
        selecting = false;
    }

    private void removeComponent(){
        if(selecting){
            removePreviewComponent();
            return;
        }
        Component toremoveComponent = getClickedComponent();
        if(toremoveComponent == null)
            return;
        renderer.getObjects().remove(toremoveComponent);
    }

    private void placePreviewComponent(){
        selecting = true;
        previewComponent = getSelectedComponent();
        previewComponent.setY(board.getY() + 8 * Hole.TILE_WIDTH + BreadBoard.PADDING);
        renderer.add(previewComponent);
    }

    private void updatePreviewComponent(){

        if(!selecting)
            return;
        
        int x = mouseX;
        if(x < board.getX())
            x = board.getX() + BreadBoard.PADDING;
        else if(x + previewComponent.getWidth() > board.getX() + board.getWidth())
            x = board.getX() + board.getWidth() - previewComponent.getWidth() - BreadBoard.PADDING;
        previewComponent.setX(x);
    }

    private void removePreviewComponent(){

        if(!selecting)
            return;
        selecting = false;
        renderer.getObjects().remove(previewComponent);
    }

    private Component getClickedComponent(){

        BreadBoard board = (BreadBoard) renderer.getObjects().get(0);

        if(mouseX < board.getX() || mouseX > board.getX() + board.getWidth() ||
           mouseY < board.getX() || mouseY > board.getY() + board.getHeight())
                return null;
           
        for(Object object : renderer.getObjects()){

            if(object instanceof Component){
                if(((Component) object).isClicked(mouseX, mouseY))
                    return (Component) object;
            }
        }
        return null;
    }

    private int getSelectedHoleIndex(){

        int selectedHoleIndex = -1;
        Hole hole;
        for(int index = 0; index < board.getHoles().size(); index++){

            hole = board.getHoles().get(index);
            if(mouseX > hole.getX() && mouseX < hole.getX() + Hole.TILE_WIDTH){
                selectedHoleIndex = index;
                break;
            }
        }
        return selectedHoleIndex;
    }

    private Component getSelectedComponent(){

        Item selectedItem = Window.list.getSelectedValue();
        if(selectedItem == null)
            return null;
        
        if(selectedItem.getName() == "IC7408")
            return new IC7408();
        return null;
    }

    private boolean checkSelectedValue(){

        if(Window.list.getSelectedValue() != null){
            if(Window.list.getSelectedValue().getName().startsWith("IC"))
                return true;
        }
        return false;
    }
}