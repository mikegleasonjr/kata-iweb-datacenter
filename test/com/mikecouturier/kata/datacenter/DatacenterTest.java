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
        given(aVm().withSize(5));
        given(aDatacenter());

        whenFindingAServerForTheVm();

        assertThat(theVmWasInstalledOnAServer(), is(false));
    }

    public void testADatacenterWithAServerWithExactlyEnoughSpaceWillHostTheVm() {
        given(aVm().withSize(7));
        given(aDatacenter().with(aServer().withCapacity(17).withId("server_1").withVm(aVm().withSize(10))));

        whenFindingAServerForTheVm();

        assertThat(theVmWasInstalledOnAServer(), is(true));
        assertThat(theServerNameTheVmWasInstalledOn(), is("server_1"));
    }

    public void testADatacenterWithAServerWithMoreThanEnoughSpaceWillHostTheVm() {
        given(aVm().withSize(20));
        given(aDatacenter().with(aServer().withCapacity(100).withId("server_1").withVm(aVm().withSize(50))));

        whenFindingAServerForTheVm();

        assertThat(theVmWasInstalledOnAServer(), is(true));
        assertThat(theServerNameTheVmWasInstalledOn(), is("server_1"));
    }

    public void testADatacenterWithAServerWithoutEnoughSpaceWillNotHostTheVm() {
        given(aVm().withSize(10));
        given(aDatacenter().with(aServer().withCapacity(30).withId("server_1").withVm(aVm().withSize(21))));

        whenFindingAServerForTheVm();

        assertThat(theVmWasInstalledOnAServer(), is(false));
    }

    public void testADatacenterWithTwoServersWithOnlyOneHavingEnoughSpaceForTheVmWillInstallTheVmOnIt() {
        given(aVm().withSize(17));
        given(aDatacenter()
                .with(aServer().withCapacity(50).withId("server_1").withVm(aVm().withSize(35)))
                .with(aServer().withCapacity(45).withId("server_2").withVm(aVm().withSize(25)))
        );

        whenFindingAServerForTheVm();

        assertThat(theVmWasInstalledOnAServer(), is(true));
        assertThat(theServerNameTheVmWasInstalledOn(), is("server_2"));
    }

    public void testADatacenterWithServersWillInstallTheVmOnTheOneHavingLessUtilizationPercentage() {
        given(aVm().withSize(20));
        given(aDatacenter()
                .with(aServer().withCapacity(105).withId("server_1").withVm(aVm().withSize(55)).withVm(aVm().withSize(5)))  // 57% utilization
                .with(aServer().withCapacity(105).withId("server_2").withVm(aVm().withSize(57)).withVm(aVm().withSize(4)))  // 58% utilization
                .with(aServer().withCapacity(105).withId("server_3").withVm(aVm().withSize(56)).withVm(aVm().withSize(3)))  // 56% utilization
                .with(aServer().withCapacity(105).withId("server_4").withVm(aVm().withSize(60)).withVm(aVm().withSize(2)))  // 59% utilization
        );

        whenFindingAServerForTheVm();

        assertThat(theVmWasInstalledOnAServer(), is(true));
        assertThat(theServerNameTheVmWasInstalledOn(), is("server_3"));
    }

    @Override
    public void tearDown() throws Exception {
        datacenter = null;
        vm = null;
        returnValue = false;
    }

    private void given(DatacenterBuilder datacenterBuilder) {
        datacenter = datacenterBuilder.build();
    }

    private void given(VmBuilder vmBuilder) {
        vm = vmBuilder.build();
    }

    private void whenFindingAServerForTheVm() {
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
