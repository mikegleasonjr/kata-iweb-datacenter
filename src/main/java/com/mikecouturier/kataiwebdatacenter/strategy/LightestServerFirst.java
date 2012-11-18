package com.mikecouturier.kataiwebdatacenter.strategy;

import com.mikecouturier.kataiwebdatacenter.Server;
import com.mikecouturier.kataiwebdatacenter.Vm;

import java.util.List;

public class LightestServerFirst implements ServerFinder {
    @Override
    public Server findAvailableServer(Vm vm, List<Server> servers) {
        Server lightestServer = null;

        for (Server s : servers) {
            if (vmFitsOnServer(vm, s) && serverIsLighter(s, lightestServer != null ? lightestServer.getUtilizationPct() : 100)) {
                lightestServer = s;
            }
        }

        return lightestServer;
    }

    private boolean serverIsLighter(Server s, int lowestPct) {
        return s.getUtilizationPct() < lowestPct;
    }

    private boolean vmFitsOnServer(Vm vm, Server s) {
        return vm.getSize() <= s.getAvailableSpace();
    }
}
