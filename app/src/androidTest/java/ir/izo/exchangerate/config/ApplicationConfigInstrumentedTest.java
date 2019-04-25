package ir.izo.exchangerate.config;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import ir.izo.exchangerate.enums.ConfigEnum;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static ir.izo.exchangerate.TestUtility.clearSharedPrefs;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(AndroidJUnit4.class)
public class ApplicationConfigInstrumentedTest {

	@Before
	public void setUp() throws Exception {
		clearSharedPrefs(InstrumentationRegistry.getTargetContext());
	}

	@Test
	public void applicationConfigTest() throws Exception {
		Context appContext = InstrumentationRegistry.getTargetContext();
		ApplicationConfig.init(appContext);
		assertNull("Name should be null!", ApplicationConfig.get(ConfigEnum.NAME));
		ApplicationConfig.store(ConfigEnum.NAME, "Jan");
		String name = ApplicationConfig.get(ConfigEnum.NAME);
		assertNotNull("Name should not be null!", name);
		assertEquals("Jan", name);
	}


}
