package datacenter;

import junit.framework.TestCase;
import org.junit.Test;
import datacenter.Datacenter;
import utils.DatacenterBuilder;
import utils.VmBuilder;
import datacenter.Vm;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static utils.DatacenterBuilder.aDatacenter;
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
