package com.kris.Objects.Components.IC;

import com.kris.Objects.Bit;

public class IC7408 extends IC_Component {

    public IC7408() {
        super(14);
        id = "7408";
    }

    @Override
    public void update(){

        if(pin[13].getValue() == Bit.Value.TRUE && pin[6].getValue() == Bit.Value.FALSE){
            
            updatePinValues(pin[0], pin[1], pin[2]);
            updatePinValues(pin[3], pin[4], pin[5]);
            updatePinValues(pin[9], pin[8], pin[7]);
            updatePinValues(pin[12], pin[11], pin[10]);
        }
        else{
            for(int i = 0; i < pins; i++)
                pin[i].setValue(Bit.Value.UNDETERMINED);
        }
    }

    private void updatePinValues(Bit pin1, Bit pin2, Bit pin3){

        if(pin1.getValue() == Bit.Value.TRUE && pin2.getValue() == Bit.Value.TRUE)
            pin3.setValue(Bit.Value.TRUE);
        else
            pin3.setValue(Bit.Value.FALSE);
    }
}