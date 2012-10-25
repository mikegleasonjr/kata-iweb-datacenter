package park;

import java.util.List;

public class Park {
    private List<Server> park;

    public Park(List<Server> park) {
        this.park = park;
    }

    public boolean placeVm(Vm vm) {
        if (parkIsEmpty())
            return false;

        Server firstServer = park.get(0);
        return firstServer.installVm(vm);
    }

    private boolean parkIsEmpty() {
        return park.isEmpty();
    }

    public String findVm(Vm vm) {
        for (Server s : park)
            if (s.isHostingVm(vm))
                return s.getId();

        return "";
    }
}
