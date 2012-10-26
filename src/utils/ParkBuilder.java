package utils;

import datacenter.Datacenter;
import datacenter.Server;

import java.util.ArrayList;
import java.util.List;

public class ParkBuilder {
    private List<Server> servers = new ArrayList<Server>();

    public static Datacenter a(ParkBuilder builder) {
        return builder.build();
    }

    public Datacenter build() {
        return new Datacenter(servers);
    }

    public static ParkBuilder aDatacenter() {
        return new ParkBuilder();
    }

    public ParkBuilder with(Server server) {
        servers.add(server);
        return this;
    }

    public ParkBuilder with(ServerBuilder serverBuilder) {
        return with(serverBuilder.build());
    }
}
