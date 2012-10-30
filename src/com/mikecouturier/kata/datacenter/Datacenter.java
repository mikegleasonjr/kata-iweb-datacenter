package com.mikecouturier.kata.datacenter;

import java.util.List;

public class Datacenter {
    private List<Server> datacenter;

    public Datacenter(List<Server> datacenter) {
        this.datacenter = datacenter;
    }

    public boolean installVm(Vm vm) {
        Server lighterServer = findLessUsedServerWithEnoughSpace(vm);

        if (lighterServer != null) {
            return lighterServer.installVm(vm);
        }

        return false;
    }

    private Server findLessUsedServerWithEnoughSpace(Vm vm) {
        Server lighterServer = null;

        for (Server s : datacenter) {
            if (vmFitsOnServer(vm, s) && serverIsLighter(s, lighterServer != null ? lighterServer.getUtilizationPct() : 100)) {
                lighterServer = s;
            }
        }

        return lighterServer;
    }

    private boolean serverIsLighter(Server s, int lowestPct) {
        return s.getUtilizationPct() < lowestPct;
    }

    private boolean vmFitsOnServer(Vm vm, Server s) {
        return vm.getSize() <= s.getAvailableSpace();
    }

    public Server findVm(Vm vm) {
        for (Server s : datacenter) {
            if (s.isHostingVm(vm)) {
                return s;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (Server s : datacenter) {
            builder.append(s);
        }

        return builder.toString();
    }
}
