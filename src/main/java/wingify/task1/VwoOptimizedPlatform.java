package wingify.task1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class VwoOptimizedPlatform extends BasePage {

	public static By switchToHeatMapFrame = By.id("heatmap-iframe");
	public static By elementTab = By.cssSelector("#heatmap-player [type='5']");
	public static By selectedTab = By.cssSelector(".option-tab.option-tab--selected");

	public VwoOptimizedPlatform(WebDriver driver) {
		super(driver);
	}

	public void verifyHeatMap(String title) {
		switchToLastWindow();
		switchToFrame(switchToHeatMapFrame);
		Assert.assertEquals(getElementWhenVisible(elementTab).isDisplayed(), true);
	}

	public void clickOnElementTab() {
		clickOnElementViaJS(elementTab);
	}

	public void verifyElementTabIsHighlighted() {
		String style = getPassedAttribute(selectedTab, "style");
		Assert.assertEquals(style, "left: calc(80% + 2px);");
	}

}
