package wingify.task1;

import static org.testng.Assert.fail;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

/**
 * Created by Harshit on 02 Oct, 2019 for Task1
 */
public class BasePage {

	public WebDriver driver;

	protected BasePage(WebDriver driver) {
		this.driver = driver;
	}

	protected void visit(String appURL) {
		driver.get(appURL);
	}

	protected WebElement getElementWhenVisible(By elementToken) {
		WebElement foundElement=null;
		WebDriverWait wait = new WebDriverWait(driver, 20);
		try {
			foundElement = wait.until(ExpectedConditions.elementToBeClickable(elementToken));
		} catch (NoSuchElementException excp) {
			fail(logMessage("[ASSERT FAILED]: Element " + elementToken + " not found on the webPage !!!"));
		} catch (NullPointerException npe) {
			fail("[UNHANDLED EXCEPTION]: " + npe.getLocalizedMessage());
		}
		return foundElement;
	}
	
	protected boolean clickOnElement(By elementToken) {
		try {
			WebElement el = getElementWhenVisible(elementToken);
			el.click();
			return true;
		} catch (NullPointerException npe) {
			fail("[UNHANDLED EXCEPTION]: " + npe.getLocalizedMessage());
		}
		return false;
	}
	
	protected void clickOnElementViaJS(By elementToken) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", getElementWhenVisible(elementToken));
	}
	
	protected void hoverOnElement(By elementToken) {
		Actions ac = new Actions(driver);
		try {
			WebElement el = getElementWhenVisible(elementToken);
			ac.moveToElement(el).build().perform();;
		} catch (NullPointerException npe) {
			fail("[UNHANDLED EXCEPTION]: " + npe.getLocalizedMessage());
		}
		
	}
	
	protected void switchToLastWindow() {
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		int n=tabs.size();
		driver.switchTo().window(tabs.get(n-1));
	}
	
	protected void switchToFrame(By frame) {
		driver.switchTo().frame(getElementWhenVisible(frame));
	}
	
	protected boolean matchPageTitle(String title) {
		if(driver.getTitle()==title)
			return true;
		else
			return false;
	}
	
	protected String getPassedAttribute(By elementToken, String attribute) {
		String attributeValue = getElementWhenVisible(elementToken).getAttribute(attribute);
		return attributeValue;
	}

	protected String logMessage(String message) {
		Reporter.log(message, true);
		return message;
	}

}
