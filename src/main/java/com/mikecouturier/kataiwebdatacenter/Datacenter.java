package com.mikecouturier.kataiwebdatacenter;

import com.mikecouturier.kataiwebdatacenter.strategy.ServerFinder;

import java.util.List;

public class Datacenter {
    private ServerFinder serverFinder;
    private List<Server> datacenter;

    public Datacenter(List<Server> datacenter, ServerFinder serverFinder) {
        this.datacenter = datacenter;
        this.serverFinder = serverFinder;
    }

    public boolean installVm(Vm vm) {
        Server availableServer = serverFinder.findAvailableServer(vm, datacenter);

        if (availableServer != null) {
            return availableServer.installVm(vm);
        }

        return false;
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
