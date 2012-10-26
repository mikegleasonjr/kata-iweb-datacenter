import junit.framework.TestCase;
import org.junit.Test;
import datacenter.Datacenter;
import utils.ParkBuilder;
import utils.VmBuilder;
import datacenter.Vm;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static utils.ParkBuilder.aDatacenter;
import static utils.ServerBuilder.aServer;
import static utils.VmBuilder.aVm;

public class DatacenterTest extends TestCase {

    @Test
    public void testADatacenterWithNoServerCannotHostAVm() {
        Given(aDatacenter());
        Given(aVm());

        WhenFindingAServerForTheVm();

        assertThat(theVmWasInstalledOnAServer(), is(false));
    }

    @Test
    public void testADatacenterWithOneServerWithEnoughSpaceCanHostAVm() throws Exception {
        Given(aDatacenter().with(aServer().withId("server_1").withTotalSpace(10)));
        Given(aVm().withSize(10));

        WhenFindingAServerForTheVm();

        assertThat(theVmWasInstalledOnAServer(), is(true));
        assertThat(theServerNameTheVmWasInstalledOn(), is("server_1"));
    }

    @Override
    public void tearDown() throws Exception {
        datacenter = null;
        vm = null;
        returnValue = false;
    }

    private void Given(ParkBuilder parkBuilder) {
        datacenter = parkBuilder.build();
    }

    private void Given(VmBuilder vmBuilder) {
        vm = vmBuilder.build();
    }

    private void WhenFindingAServerForTheVm() {
        returnValue = datacenter.placeVm(vm);
    }

    private boolean theVmWasInstalledOnAServer()
    {
        return returnValue;
    }

    private String theServerNameTheVmWasInstalledOn() {
        return datacenter.findVm(vm);
    }

    private Datacenter datacenter = null;
    private Vm vm = null;
    private boolean returnValue = false;
}
