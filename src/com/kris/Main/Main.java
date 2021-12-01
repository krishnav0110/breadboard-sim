package com.kris.Main;

import java.awt.Color;

import com.kris.Frame.Window;
import com.kris.Frame.MenuList.Item;
import com.kris.MainEngine.Engine;
import com.kris.Managers.ComponentPlacer;
import com.kris.Managers.WirePlacer;
import com.kris.Objects.*;
import com.kris.Objects.Components.LEDLight;
import com.kris.Objects.Components.IC.IC7408;

public class Main {
    
    public static void main(String[] args){

        //all testing stuff, a bit messy

        Window.createDESApp();
        Engine engine = new Engine();

        Window.scene.add(engine.getRenderer());
        engine.getRenderer().setBounds(0, 0, Window.scene.getWidth(), Window.scene.getHeight());

        BreadBoard board = new BreadBoard();
        board.generateDefaultLayout();
        board.setCenterPositionRelativeTo(engine.getRenderer());
        engine.getRenderer().add(board);

        engine.getRenderer().addManager(new WirePlacer());
        engine.getRenderer().addManager(new ComponentPlacer());

        Window.list.addItem(new Item("BLUE WIRE", "", Color.BLUE));
        Window.list.addItem(new Item("RED WIRE", "", Color.RED));
        Window.list.addItem(new Item("GREEN WIRE", "", Color.GREEN));
        Window.list.addItem(new Item("YELLOW WIRE", "", Color.YELLOW));
        Window.list.addItem(new Item("IC7408", "", Color.BLACK));




        Battery testBattery = new Battery();
        engine.getRenderer().add(testBattery);

        engine.getRenderer().add(testBattery.negative_terminal);
        engine.getRenderer().add(testBattery.positive_terminal);

        LEDLight testLight = new LEDLight();
        testLight.connect(board, 100);
        engine.getRenderer().add(testLight);

        IC7408 testIC = new IC7408();
        testIC.connect(board, 64);
        engine.getRenderer().add(testIC);

        engine.start();
    }
}