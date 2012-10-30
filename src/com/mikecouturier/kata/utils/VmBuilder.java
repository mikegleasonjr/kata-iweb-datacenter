package com.mikecouturier.kata.utils;
import com.mikecouturier.kata.datacenter.Vm;

public class VmBuilder {
    private int size;
    private String id;

    public static Vm a(VmBuilder builder) {
        return builder.build();
    }

    public static VmBuilder aVm() {
        return new VmBuilder();
    }

    public Vm build() {
        return new Vm(size, id);
    }

    public VmBuilder withSize(int size) {
        this.size = size;
        return this;
    }

    public VmBuilder withId(String id) {
        this.id = id;
        return this;
    }
}
