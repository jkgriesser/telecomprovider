package digital.and.telecomprovider;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TelecomProviderApplicationTest {

	@Autowired
	private ApplicationContext applicationContext;

	@Test
	public void contextLoads() {
		assertThat(applicationContext, is(notNullValue()));
	}

}
