package com.mikecouturier.kataiwebdatacenter.strategy;

import com.mikecouturier.kataiwebdatacenter.Server;
import com.mikecouturier.kataiwebdatacenter.Vm;

import java.util.List;

public interface ServerFinder {
    Server findAvailableServer(Vm vm, List<Server> servers);
}
