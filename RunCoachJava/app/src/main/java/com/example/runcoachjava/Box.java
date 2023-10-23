package com.example.runcoachjava;

public class Box<T> {
    private T boxObj;
    public Box(T obj) {
        this.boxObj = obj;
    }

    @Override
    public String toString() {
        return this.boxObj.toString();
    }
}
