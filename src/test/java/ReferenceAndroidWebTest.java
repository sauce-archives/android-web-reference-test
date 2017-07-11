import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testobject.integrations.LogentriesResultReporter;

import java.net.URL;

public class ReferenceAndroidWebTest {

	@ClassRule
	public static LogentriesResultReporter logentriesResultReporter = new LogentriesResultReporter();

	private AppiumDriver driver;

	/* This is the setup that will be run before the test. */
	@Before
	public void setUp() throws Exception {
		DesiredCapabilities capabilities = new DesiredCapabilities();

		capabilities.setCapability("testobject_app_id", System.getenv("TESTOBJECT_APP_ID"));
		capabilities.setCapability("testobject_api_key", System.getenv("TESTOBJECT_API_KEY")); // API key through env variable
		capabilities.setCapability("testobject_device", System.getenv("TESTOBJECT_DEVICE_ID")); // device id through env variable
		capabilities.setCapability("testobject_appium_version", System.getenv( "TESTOBJECT_APPIUM_VERSION"));
		capabilities.setCapability("testobject_cache_device", System.getenv("TESTOBJECT_CACHE_DEVICE"));

		driver = new AndroidDriver(new URL(System.getenv("APPIUM_SERVER")), capabilities);

		System.out.println(driver.getCapabilities().getCapability("testobject_test_report_url"));
		System.out.println(driver.getCapabilities().getCapability("testobject_test_live_view_url"));
	}

	@After
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test
	public void openWebpageAndTakeScreenshotFirst() {
		String url = "https://www.google.com";
		driver.get(url);
		takeScreenshot();
	}

	@Test
	public void openWebpageAndTakeScreenshotSecond() {
		String url = "https://www.google.com";
		driver.get(url);
		takeScreenshot();
	}

	private void takeScreenshot() {
		try {
			driver.getScreenshotAs(OutputType.FILE).delete();
		} catch (Exception e) {
			System.out.println("Exception while saving the file " + e);
		}
	}
}
