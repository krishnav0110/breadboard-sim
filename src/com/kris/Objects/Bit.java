package com.kris.Objects;

/* this class is used in Holes.
 *
 * created to pass the references to the other holes objects which will share
 * the same state of bit, kind of connecting them together internally in a breadboard.
 */

public class Bit {
    
    private Value bit;

    public Bit(){
        bit = Value.UNDETERMINED;
    }

    public Value getValue(){
        return bit;
    }
    public void setValue(Value bit){
        this.bit = bit;
    }

    public enum Value {

        TRUE,
        FALSE,
        UNDETERMINED
    }
}
