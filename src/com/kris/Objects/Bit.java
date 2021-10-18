package com.kris.Objects;

/* this class is used in Holes.
 *
 * created to pass the references to the other holes objects which will share
 * the same state of bit, kind of connecting them together internally in a breadboard.
 */

public class Bit {
    
    public boolean bit;

    public Bit(){
        bit = false;
    }

    public boolean getValue(){
        return bit;
    }
    public void setValue(boolean bit){
        this.bit = bit;
    }
}
