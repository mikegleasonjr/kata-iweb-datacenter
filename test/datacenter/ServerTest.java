package datacenter;

import junit.framework.TestCase;
import org.junit.Test;
import utils.ServerBuilder;
import utils.VmBuilder;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static utils.ServerBuilder.aServer;
import static utils.VmBuilder.aVm;

public class ServerTest extends TestCase {

    @Test
    public void testAServerWithNoSpaceCannotHostAVm() throws Exception {
        Given(aServer().withId("server_1").withTotalSpace(0));
        Given(aVm().withSize(1));

        WhenInstallingTheVmOnTheServer();

        assertThat(theVmWasInstalled(), is(false));
    }

    @Test
    public void testAServerWithExactlyEnoughSpaceForAVMCanHostIt() throws Exception {
        Given(aServer().withId("server_1").withTotalSpace(20));
        Given(aVm().withSize(20));

        WhenInstallingTheVmOnTheServer();

        assertThat(theVmWasInstalled(), is(true));
    }

    @Test
    public void testAServerWithMoreSpaceForAVMCanHostIt() throws Exception {
        Given(aServer().withId("server_1").withTotalSpace(50));
        Given(aVm().withSize(27));

        WhenInstallingTheVmOnTheServer();

        assertThat(theVmWasInstalled(), is(true));
    }

    @Test
    public void testAServerWithNotEnoughSpaceForAVMCannotHostIt() throws Exception {
        Given(aServer().withId("server_1").withTotalSpace(5));
        Given(aVm().withSize(6));

        WhenInstallingTheVmOnTheServer();

        assertThat(theVmWasInstalled(), is(false));
    }

    @Test
    public void testAServerWithVMsInstalledCanInstallAnotherOneWhenTheRemainingSpaceAvailableEqualsTheVmSize() throws Exception {
        Given(aServer().withId("server_1").withTotalSpace(30).withVm(aVm().withSize(13)).withVm(aVm().withSize(13)));
        Given(aVm().withSize(4));

        WhenInstallingTheVmOnTheServer();

        assertThat(theVmWasInstalled(), is(true));
    }

    @Test
    public void testAServerWithVMsInstalledCanInstallAnotherOneWhenTheRemainingSpaceAvailableIsGreaterThanTheVmSize() throws Exception {
        Given(aServer().withId("server_1").withTotalSpace(30).withVm(aVm().withSize(10)).withVm(aVm().withSize(10)));
        Given(aVm().withSize(5));

        WhenInstallingTheVmOnTheServer();

        assertThat(theVmWasInstalled(), is(true));
    }

    @Test
    public void testAServerWithVMsInstalledCanInstallAnotherOneWhenTheRemainingSpaceAvailableIsLowerThanTheVmSize() throws Exception {
        Given(aServer().withId("server_1").withTotalSpace(30).withVm(aVm().withSize(14)).withVm(aVm().withSize(14)));
        Given(aVm().withSize(3));

        WhenInstallingTheVmOnTheServer();

        assertThat(theVmWasInstalled(), is(false));
    }

    @Test
    public void testAServerHostsTheVMWhenItIsSuccessfullyInstalledOnIt() throws Exception {
        Given(aServer().withId("server_1").withTotalSpace(30));
        Given(aVm().withSize(12));

        WhenInstallingTheVmOnTheServer();

        assertThat(theServerHostsTheVmAfterInstallation(), is(true));
    }

    @Test
    public void testAServerDoesNotHostTheVMWhenItIsNotSuccessfullyInstalledOnIt() throws Exception {
        Given(aServer().withId("server_1").withTotalSpace(30));
        Given(aVm().withSize(45));

        WhenInstallingTheVmOnTheServer();

        assertThat(theServerHostsTheVmAfterInstallation(), is(false));
    }

    @Override
    public void tearDown() throws Exception {
        server = null;
        vm = null;
        vmInstalled = false;
    }

    private void Given(ServerBuilder serverBuilder) {
        server = serverBuilder.build();
    }

    private void Given(VmBuilder vmBuilder) {
        vm = vmBuilder.build();
    }

    private void WhenInstallingTheVmOnTheServer() {
        vmInstalled = server.installVm(vm);
    }

    private boolean theVmWasInstalled()
    {
        return vmInstalled;
    }

    private boolean theServerHostsTheVmAfterInstallation() {
        return server.isHostingVm(vm);
    }

    private int theAvailableSpaceOnTheServer() {
        return server.getAvailableSpace();
    }

    private Server server = null;
    private Vm vm = null;
    private boolean vmInstalled = false;
}
