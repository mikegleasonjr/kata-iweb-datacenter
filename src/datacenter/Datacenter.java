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

        return true;

        //Server firstServer = datacenter.get(0);
        //return firstServer.installVm(vm);
    }

    private boolean parkIsEmpty() {
        return datacenter.isEmpty();
    }
/*
    public String findVm(Vm vm) {
        for (Server s : datacenter)
            if (s.isHostingVm(vm))
                return s.getId();

        return "";
    }
*/
}
