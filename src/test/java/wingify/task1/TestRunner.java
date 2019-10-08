package wingify.task1;

import org.testng.annotations.*;

import utilities.PropFileHandler;

public class TestRunner {

	TestSessionInitiator test;

	@BeforeSuite
	public void startSession() {
		test = new TestSessionInitiator(PropFileHandler.readProperty("browser"));
	}

	@Test
	public void launchVWOApplication() {
		TestSessionInitiator.homepage.launchVWOApplication();
	}

	@Test(dependsOnMethods = "launchVWOApplication")
	public void launchHeatMap() {
		TestSessionInitiator.homepage.launchHeatMap();
	}

	@Test(dependsOnMethods = "launchHeatMap")
	public void verifyHeatMap() {
		TestSessionInitiator.vwoOptimizedPlatform.verifyHeatMap("VWO Experience Optimization Platform");
	}

	@Test(dependsOnMethods = "verifyHeatMap")
	public void clickOnElementTab() {
		TestSessionInitiator.vwoOptimizedPlatform.clickOnElementTab();
	}

	@Test(dependsOnMethods = "clickOnElementTab")
	public void verifyElementTabIsHighlighted() {
		TestSessionInitiator.vwoOptimizedPlatform.verifyElementTabIsHighlighted();
	}

	@AfterSuite
	public void closeSession() {
		test.quit();
	}

}
