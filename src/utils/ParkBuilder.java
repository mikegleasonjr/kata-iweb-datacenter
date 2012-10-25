package utils;

import park.Park;
import park.Server;

import java.util.ArrayList;
import java.util.List;

public class ParkBuilder {
    private List<Server> servers = new ArrayList<Server>();

    public static Park a(ParkBuilder builder) {
        return builder.build();
    }

    public Park build() {
        return new Park(servers);
    }

    public static ParkBuilder aPark() {
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
