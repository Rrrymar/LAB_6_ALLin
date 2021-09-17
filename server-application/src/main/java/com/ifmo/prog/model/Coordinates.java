package com.ifmo.prog.model;

import java.io.Serializable;

public class Coordinates implements Serializable {
    private Double x; //Значение поля должно быть больше -926, Поле не может быть null
    private Float y; //Значение поля должно быть больше -658, Поле не может быть null

    public Coordinates(Double x, Float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public Double getX() {
        return x;
    }

    public Float getY() {
        return y;
    }

}