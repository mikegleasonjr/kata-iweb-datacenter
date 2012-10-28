package utils;

import datacenter.Server;
import datacenter.Vm;

import java.rmi.ServerError;
import java.util.ArrayList;
import java.util.List;

public class ServerBuilder {
    private String id;
    private int space;
    private List<Vm> initialVms = new ArrayList<Vm>();

    public static Server a(ServerBuilder builder) {
        return builder.build();
    }

    public Server build() {
        Server s = new Server(id, space);

        for (Vm vm : initialVms) {
            s.installVm(vm);
        }

        return s;
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

    public ServerBuilder withVm(Vm vm) {
        initialVms.add(vm);
        return this;
    }

    public ServerBuilder withVm(VmBuilder vmBuilder) {
        return withVm(vmBuilder.build());
    }
}
