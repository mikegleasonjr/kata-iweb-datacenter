package com.mikecouturier.kata.datacenter;

import junit.framework.TestCase;
import com.mikecouturier.kata.utils.DatacenterBuilder;
import com.mikecouturier.kata.utils.VmBuilder;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static com.mikecouturier.kata.utils.DatacenterBuilder.aDatacenter;
import static com.mikecouturier.kata.utils.ServerBuilder.aServer;
import static com.mikecouturier.kata.utils.VmBuilder.aVm;

public class DatacenterTest extends TestCase {

    public void testADatacenterWithNoServerCannotHostAVm() {
        Given(aVm().withSize(5));
        Given(aDatacenter());

        WhenFindingAServerForTheVm();

        assertThat(theVmWasInstalledOnAServer(), is(false));
    }

    public void testADatacenterWithAServerWithExactlyEnoughSpaceWillHostTheVm() {
        Given(aVm().withSize(7));
        Given(aDatacenter().with(aServer().withCapacity(17).withId("server_1").withVm(aVm().withSize(10))));

        WhenFindingAServerForTheVm();

        assertThat(theVmWasInstalledOnAServer(), is(true));
        assertThat(theServerNameTheVmWasInstalledOn(), is("server_1"));
    }

    public void testADatacenterWithAServerWithMoreThanEnoughSpaceWillHostTheVm() {
        Given(aVm().withSize(20));
        Given(aDatacenter().with(aServer().withCapacity(100).withId("server_1").withVm(aVm().withSize(50))));

        WhenFindingAServerForTheVm();

        assertThat(theVmWasInstalledOnAServer(), is(true));
        assertThat(theServerNameTheVmWasInstalledOn(), is("server_1"));
    }

    public void testADatacenterWithAServerWithoutEnoughSpaceWillNotHostTheVm() {
        Given(aVm().withSize(10));
        Given(aDatacenter().with(aServer().withCapacity(30).withId("server_1").withVm(aVm().withSize(21))));

        WhenFindingAServerForTheVm();

        assertThat(theVmWasInstalledOnAServer(), is(false));
    }

    public void testADatacenterWithTwoServersWithOnlyOneHavingEnoughSpaceForTheVmWillInstallTheVmOnIt() {
        Given(aVm().withSize(17));
        Given(aDatacenter()
                .with(aServer().withCapacity(50).withId("server_1").withVm(aVm().withSize(35)))
                .with(aServer().withCapacity(45).withId("server_2").withVm(aVm().withSize(25)))
        );

        WhenFindingAServerForTheVm();

        assertThat(theVmWasInstalledOnAServer(), is(true));
        assertThat(theServerNameTheVmWasInstalledOn(), is("server_2"));
    }

    public void testADatacenterWithServersWillInstallTheVmOnTheOneHavingLessUtilizationPercentage() {
        Given(aVm().withSize(20));
        Given(aDatacenter()
                .with(aServer().withCapacity(105).withId("server_1").withVm(aVm().withSize(55)).withVm(aVm().withSize(5)))  // 57% utilization
                .with(aServer().withCapacity(105).withId("server_2").withVm(aVm().withSize(57)).withVm(aVm().withSize(4)))  // 58% utilization
                .with(aServer().withCapacity(105).withId("server_3").withVm(aVm().withSize(56)).withVm(aVm().withSize(3)))  // 56% utilization
                .with(aServer().withCapacity(105).withId("server_4").withVm(aVm().withSize(60)).withVm(aVm().withSize(2)))  // 59% utilization
        );

        WhenFindingAServerForTheVm();

        assertThat(theVmWasInstalledOnAServer(), is(true));
        assertThat(theServerNameTheVmWasInstalledOn(), is("server_3"));
    }

    @Override
    public void tearDown() throws Exception {
        datacenter = null;
        vm = null;
        returnValue = false;
    }

    private void Given(DatacenterBuilder datacenterBuilder) {
        datacenter = datacenterBuilder.build();
    }

    private void Given(VmBuilder vmBuilder) {
        vm = vmBuilder.build();
    }

    private void WhenFindingAServerForTheVm() {
        returnValue = datacenter.installVm(vm);
    }

    private boolean theVmWasInstalledOnAServer()
    {
        return returnValue;
    }

    private String theServerNameTheVmWasInstalledOn() {
        Server s = datacenter.findVm(vm);
        return s != null ? s.getId() : "(unknown)";
    }

    private Datacenter datacenter = null;
    private Vm vm = null;
    private boolean returnValue = false;
}
