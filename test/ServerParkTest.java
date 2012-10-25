import junit.framework.TestCase;
import org.junit.Test;
import park.Park;
import utils.ParkBuilder;
import utils.VmBuilder;
import park.Vm;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static utils.ParkBuilder.aPark;
import static utils.ServerBuilder.aServer;
import static utils.VmBuilder.aVm;

public class ServerParkTest extends TestCase {

    @Test
    public void testAParkWithNoServerCannotHostAVm() {
        Given(aPark());
        Given(aVm());

        WhenFindingAServerForTheVm();

        assertThat(theSuccessOfTheOperation(), equalTo(false));
    }

    @Test
    public void testAParkWithOneServerWithEnoughSpaceCanHostAVm() throws Exception {
        Given(aPark().with(aServer().withId("server_1").withTotalSpace(10)));
        Given(aVm().withSize(10));

        WhenFindingAServerForTheVm();

        assertThat(theSuccessOfTheOperation(), equalTo(true));
        assertThat(theVmWasPlacedOnServerId(), equalTo("server_1"));
    }

    @Override
    public void tearDown() throws Exception {
        park = null;
        vm = null;
        returnValue = false;
    }

    private void Given(ParkBuilder parkBuilder) {
        park = parkBuilder.build();
    }

    private void Given(VmBuilder vmBuilder) {
        vm = vmBuilder.build();
    }

    private void WhenFindingAServerForTheVm() {
        returnValue = park.placeVm(vm);
    }

    private boolean theSuccessOfTheOperation()
    {
        return returnValue;
    }

    private String theVmWasPlacedOnServerId() {
        return park.findVm(vm);
    }

    private Park park = null;
    private Vm vm = null;
    private boolean returnValue = false;
}
