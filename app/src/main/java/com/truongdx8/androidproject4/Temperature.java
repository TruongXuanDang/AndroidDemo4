package com.truongdx8.androidproject4;

public class Temperature {
    private float Value;
    private String Unit;
    private int UnitType;

    public float getValue() {
        return Value;
    }

    public void setValue(float value) {
        Value = value;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }

    public int getUnitType() {
        return UnitType;
    }

    public void setUnitType(int unitType) {
        UnitType = unitType;
    }
}
