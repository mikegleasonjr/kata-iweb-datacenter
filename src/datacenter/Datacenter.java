package datacenter;

import java.util.List;

public class Datacenter {
    private List<Server> datacenter;

    public Datacenter(List<Server> datacenter) {
        this.datacenter = datacenter;
    }

    public boolean placeVm(Vm vm) {
        if (parkIsEmpty())
            return false;

        for (Server s : datacenter) {
            if (s.getAvailableSpace() >= vm.getSize()) {
                return s.installVm(vm);
            }
        }

        return false;
    }

    private boolean parkIsEmpty() {
        return datacenter.isEmpty();
    }

    public Server findVm(Vm vm) {
        for (Server s : datacenter) {
            if (s.isHostingVm(vm)) {
                return s;
            }
        }

        return null;
    }
}
