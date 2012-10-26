package datacenter;

import java.util.ArrayList;
import java.util.List;

public class Server {
    private String id;
    private int capacity;
    private List<Vm> vms = new ArrayList<Vm>();

    public Server(String id, int capacity) {
        this.id = id;
        this.capacity = capacity;
    }

    public String getId() {
        return id;
    }

    public boolean isHostingVm(Vm vm) {
        return vms.contains(vm);
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean installVm(Vm vm) {
        return vms.add(vm);
    }
}
