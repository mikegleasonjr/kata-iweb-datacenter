package com.mikecouturier.kata;

import com.mikecouturier.kata.datacenter.Datacenter;
import com.mikecouturier.kata.datacenter.Vm;

import java.util.Arrays;
import java.util.List;

import static com.mikecouturier.kata.utils.DatacenterBuilder.aDatacenter;
import static com.mikecouturier.kata.utils.ServerBuilder.aServer;
import static com.mikecouturier.kata.utils.VmBuilder.aVm;

public class Program {

    public static void main(String[] args) {
        Datacenter datacenter = DatacenterAsInput();    // instead of reading input from JSON, too lazy ;)
        List<Vm> vms = VmsAsInput();                    // instead of reading input from JSON, too lazy ;)

        installVmsInDatacenter(vms, datacenter);
        printDatacenter(datacenter);
    }

    private static Datacenter DatacenterAsInput() {
        return aDatacenter()
                .with(aServer().withId("server1").withCapacity(4))
                .with(aServer().withId("server2").withCapacity(6))
                .build();
    }

    private static List<Vm> VmsAsInput() {
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
