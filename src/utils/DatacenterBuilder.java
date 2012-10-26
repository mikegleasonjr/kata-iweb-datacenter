package utils;

import datacenter.Datacenter;
import datacenter.Server;

import java.util.ArrayList;
import java.util.List;

public class DatacenterBuilder {
    private List<Server> datacenter = new ArrayList<Server>();

    public static Datacenter a(DatacenterBuilder builder) {
        return builder.build();
    }

    public Datacenter build() {
        return new Datacenter(datacenter);
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
