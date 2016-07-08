import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class ReferenceAndroidWebTest {

	private AppiumDriver driver;
	private long startTime;

	/* This is the setup that will be run before the test. */
	@Before
	public void setUp() throws Exception {
		DesiredCapabilities capabilities = new DesiredCapabilities();

		capabilities.setCapability("testobject_app_id", "1");
		capabilities.setCapability("testobject_api_key", System.getenv("TESTOBJECT_API_KEY")); // API key through env variable
		capabilities.setCapability("testobject_device", System.getenv("TESTOBJECT_ANDROID_DEVICE_ID")); // device id through env variable
		capabilities.setCapability("testobject_appium_version", "1.5.2");

		this.startTime = System.currentTimeMillis();
		driver = new AndroidDriver(new URL("https://app.testobject.com:443/api/appium/wd/hub"), capabilities);

	}

	@After
	public void tearDown() {
		System.out.println("Test took: " + (System.currentTimeMillis() - startTime));
		if (driver != null) {
			driver.quit();
		}
	}

	@Test
	public void openWebpageAndTakeScreenshot() {
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
