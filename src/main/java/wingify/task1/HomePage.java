package wingify.task1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import utilities.PropFileHandler;

public class HomePage extends BasePage {

	public static By heatmapDefaultThumbnail = By.className("heatmap-thumb__default-heatmap");
	public static By viewHeatmap = By.xpath("//div[contains(text(),'View heatmap')]");

	public HomePage(WebDriver driver) {
		super(driver);
	}

	public void launchVWOApplication() {
		visit(PropFileHandler.readProperty("appURL"));
	}

	public void launchHeatMap() {
		hoverOnElement(heatmapDefaultThumbnail);
		Assert.assertEquals(clickOnElement(viewHeatmap), true);
	}
}
