package com.mikecouturier.kataiwebdatacenter.utils;

import com.mikecouturier.kataiwebdatacenter.Vm;

public class VmBuilder {
    private int size;
    private String id;

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
