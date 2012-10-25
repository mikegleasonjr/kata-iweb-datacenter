package utils;

import park.Server;

import java.util.ArrayList;
import java.util.List;

public class ServerBuilder {
    private String id;
    private int space;

    public static Server a(ServerBuilder builder) {
        return builder.build();
    }

    public Server build() {
        return new Server(id, space);
    }

    public static ServerBuilder aServer() {
        return new ServerBuilder();
    }

    public ServerBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public ServerBuilder withTotalSpace(int space) {
        this.space = space;
        return this;
    }
}
