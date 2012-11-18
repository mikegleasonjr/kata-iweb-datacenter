package com.mikecouturier.kataiwebdatacenter;

import java.util.ArrayList;
import java.util.List;

public class Server {
    private String id;
    private int capacity;
    private List<Vm> vms = new ArrayList<Vm>();

    public Server(String id, int capacity) {
        this.id = id;
        this.capacity = capacity;
    }

    public String getId() {
        return id;
    }

    public boolean isHostingVm(Vm vm) {
        return vms.contains(vm);
    }

    public boolean installVm(Vm vm) {
        return vms.add(vm);
    }

    public int getAvailableSpace() {
        int spaceTaken = 0;

        for (Vm vm : vms) {
            spaceTaken += vm.getSize();
        }

        return capacity - spaceTaken;
    }

    public int getUtilizationPct() {
        return Math.round(((capacity - getAvailableSpace()) / (float)capacity) * 100f);
    }

    public List<Vm> getVms() {
        return new ArrayList<Vm>(vms);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append(String.format("%s (%d%% utilization)\n", id, getUtilizationPct()));

        for (Vm vm : vms) {
            builder.append(String.format("  %s", vm.toString()));
        }

        return builder.toString();
    }
}
