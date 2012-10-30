package com.mikecouturier.kata.utils;

import com.mikecouturier.kata.datacenter.Server;
import com.mikecouturier.kata.datacenter.Vm;

import java.util.ArrayList;
import java.util.List;

public class ServerBuilder {
    private String id;
    private int capacity;
    private List<Vm> initialVms = new ArrayList<Vm>();

    public Server build() {
        Server s = new Server(id, capacity);

        for (Vm vm : initialVms) {
            s.installVm(vm);
        }

        return s;
    }

    public static ServerBuilder aServer() {
        return new ServerBuilder();
    }

    public ServerBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public ServerBuilder withCapacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

    public ServerBuilder withVm(Vm vm) {
        initialVms.add(vm);
        return this;
    }

    public ServerBuilder withVm(VmBuilder vmBuilder) {
        return withVm(vmBuilder.build());
    }
}
