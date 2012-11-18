package com.mikecouturier.kataiwebdatacenter;

import com.mikecouturier.kataiwebdatacenter.utils.ServerBuilder;
import org.junit.After;
import org.junit.Test;

import static com.mikecouturier.kataiwebdatacenter.utils.ServerBuilder.aServer;
import static com.mikecouturier.kataiwebdatacenter.utils.VmBuilder.aVm;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ServerTest {

    @Test
    public void anEmptyServerHas0PctUtilization() {
        given(aServer().withCapacity(100));

        assertThat(theUtilizationPercentageOfTheServer(), is(0));
    }

    @Test
    public void aFullServerHas100PctUtilization() {
        given(aServer().withCapacity(52).withVm(aVm().withSize(26)).withVm(aVm().withSize(26)));

        assertThat(theUtilizationPercentageOfTheServer(), is(100));
    }

    @Test
    public void aHalfFullServerHas50PctUtilization() {
        given(aServer().withCapacity(50).withVm(aVm().withSize(25)));

        assertThat(theUtilizationPercentageOfTheServer(), is(50));
    }

    @After
    public void tearDown() throws Exception {
        server = null;
    }

    private void given(ServerBuilder serverBuilder) {
        server = serverBuilder.build();
    }

    private int theUtilizationPercentageOfTheServer()
    {
        return server.getUtilizationPct();
    }

    private Server server = null;
}
