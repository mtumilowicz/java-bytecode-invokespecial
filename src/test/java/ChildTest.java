import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by mtumilowicz on 2018-09-30.
 */
public class ChildTest {

    @Test
    public void where() {
        assertThat(new Child().where(), is("In Child"));
    }

    @Test
    public void locate() {
        assertThat(new Child().locate(), is("In Child"));
    }
}