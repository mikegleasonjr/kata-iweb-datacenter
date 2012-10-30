package com.mikecouturier.kata.datacenter;

public class Vm {
    private int size;
    private String id;

    public Vm(int size, String id) {
        this.size = size;
        this.id = id;
    }

    public int getSize() {
        return size;
    }

    public String getId() {
        return id;
    }
}
