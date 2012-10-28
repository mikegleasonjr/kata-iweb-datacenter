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

    public boolean installVm(Vm vm) {
        if (vmFitsOnServer(vm))
            return vms.add(vm);

        return false;
    }

    private boolean vmFitsOnServer(Vm vm) {
        return getAvailableSpace() >= vm.getSize();
    }

    public int getAvailableSpace() {
        int spaceTaken = 0;

        for (Vm vm : vms) {
            spaceTaken += vm.getSize();
        }

        return capacity - spaceTaken;
    }
}
