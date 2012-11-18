package com.mikecouturier.kataiwebdatacenter.utils;

import com.mikecouturier.kataiwebdatacenter.Datacenter;
import com.mikecouturier.kataiwebdatacenter.Server;
import com.mikecouturier.kataiwebdatacenter.strategy.LightestServerFirst;

import java.util.ArrayList;
import java.util.List;

public class DatacenterBuilder {
    private List<Server> datacenter = new ArrayList<Server>();

    public Datacenter build() {
        return new Datacenter(datacenter, new LightestServerFirst());
    }

    public static DatacenterBuilder aDatacenter() {
        return new DatacenterBuilder();
    }

    public DatacenterBuilder with(Server server) {
        datacenter.add(server);
        return this;
    }

    public DatacenterBuilder with(ServerBuilder serverBuilder) {
        return with(serverBuilder.build());
    }
}
