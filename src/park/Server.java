package park;

import java.util.ArrayList;
import java.util.List;

public class Server {
    private String id;
    private int totalSpace;
    private List<Vm> vms = new ArrayList<Vm>();

    public Server(String id, int totalSpace) {
        this.id = id;
        this.totalSpace = totalSpace;
    }

    public String getId() {
        return id;
    }

    public boolean isHostingVm(Vm vm) {
        return vms.contains(vm);
    }

    public int getTotalSpace() {
        return totalSpace;
    }

    public boolean installVm(Vm vm) {
        return vms.add(vm);
    }
}
