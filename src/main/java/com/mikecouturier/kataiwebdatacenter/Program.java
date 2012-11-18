package com.mikecouturier.kataiwebdatacenter;

import java.util.Arrays;
import java.util.List;

import static com.mikecouturier.kataiwebdatacenter.utils.DatacenterBuilder.aDatacenter;
import static com.mikecouturier.kataiwebdatacenter.utils.ServerBuilder.aServer;
import static com.mikecouturier.kataiwebdatacenter.utils.VmBuilder.aVm;

public class Program {

    public static void main(String[] args) {
        Datacenter datacenter = datacenterAsInput();    // instead of reading input from JSON, too lazy ;)
        List<Vm> vms = vmsAsInput();                    // instead of reading input from JSON, too lazy ;)

        installVmsInDatacenter(vms, datacenter);
        printDatacenter(datacenter);
    }

    private static Datacenter datacenterAsInput() {
        return aDatacenter()
                .with(aServer().withId("server1").withCapacity(4))
                .with(aServer().withId("server2").withCapacity(6))
                .build();
    }

    private static List<Vm> vmsAsInput() {
        return Arrays.asList(
                aVm().withId("VM1").withSize(1).build(),
                aVm().withId("VM2").withSize(4).build(),
                aVm().withId("VM3").withSize(2).build()
        );
    }

    private static void installVmsInDatacenter(List<Vm> vms, Datacenter datacenter) {
        for (Vm vm : vms) {
            datacenter.installVm(vm);
        }
    }

    private static void printDatacenter(Datacenter datacenter) {
        System.out.print(datacenter);
    }
}
