package com.resourses;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class BaseClass {
	public static WebDriver driver;
	public static Select sel;
	public static Actions act;
	public static JavascriptExecutor js;

	public static void launchBrowser(String url) {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\hi\\eclipse-workspace\\KalaiVaniV\\SeleniumLaunch\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(url);
	}
	
	public static String getURL() {
		String url = driver.getCurrentUrl();
		return url;
	}
	
	public static String getTitle() {
		String title = driver.getTitle();
		return title;
	}

	public static void type(WebElement e, String s) {
		e.sendKeys(s);
	}

	public static void click(WebElement e) {
		e.click();
	}

	public static void closeBrowser() {
		driver.quit();
	}

	public static void selectByValue(WebElement e, String s) {
		sel = new Select(e);
		sel.selectByValue(s);
	}

	public static void selectByText(WebElement e, String s) {
		sel = new Select(e);
		sel.selectByVisibleText(s);
	}

	public static void selectByIndex(WebElement e, int i) {
		sel = new Select(e);
		sel.selectByIndex(i);
	}

	public static void multipleValueDropDown(WebElement e, List<String> s) {
		sel = new Select(e);
		List<WebElement> opt = sel.getOptions();
		for (String value : s) {
			for (WebElement getOpt : opt) {
				String t = getOpt.getText();
				if (t.equals(value)) {
					sel.selectByVisibleText(value);
				}
			}
		}
	}

	public static void moveToElement(WebElement e) {
		act = new Actions(driver);
		act.moveToElement(e).perform();
	}

	public static void doubleClick(WebElement e) {
		act = new Actions(driver);
		act.doubleClick(e).perform();
	}

	public static void rightClick(WebElement e) {
		act = new Actions(driver);
		act.contextClick(e).perform();
	}

	public static void dragAndDrop(WebElement src, WebElement dec) {
		act = new Actions(driver);
		act.dragAndDrop(src, dec).perform();
	}

	public static void webTable(String date) {
		date = "12-Oct-2019";
		String[] sp = date.split("-");
		String day = sp[0];
		String mon = sp[1];
		String year = sp[2];
		List<WebElement> rows = driver.findElements(By.tagName("tr"));
		for (WebElement tRow : rows) {
			List<WebElement> cells = tRow.findElements(By.tagName("td"));
			for (WebElement tCell : cells) {
				String t = tCell.getText();
				if (t.equals(day)) {
					tCell.click();
				}
			}
		}
	}

	public static void windowsHandling(int win) {
		String pWind = driver.getWindowHandle();
		Set<String> allWind = driver.getWindowHandles();
		ArrayList<String> windows = new ArrayList<String>();
		windows.addAll(allWind);
		driver.switchTo().window(windows.get(win));
	}

	public static void screenShot(String path) throws IOException {
		TakesScreenshot tk = (TakesScreenshot) driver;
		File src = tk.getScreenshotAs(OutputType.FILE);
		File dec = new File(path);
		FileUtils.copyFile(src, dec);
	}

	public static void scrollDown(WebElement down) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)", down);
	}

	public static void scrollUp(WebElement up) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(false)", up);
	}

	public static void implicitWait() {
		driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
	}

	public static void alert(String alertType, String actions) {
		Alert a = driver.switchTo().alert();
		if (alertType.equals("Simple")) {
			a.accept();
		}
		if (alertType.equals("Confirm")) {
			if (actions.equals("Yes")) {
				a.accept();
			} else {
				a.dismiss();
			}
		}
		if (alertType.equals("Promt")) {
			if (actions.equals("Yes")) {
				a.sendKeys("Yes");
				a.accept();
			} else {
				a.sendKeys("No");
				a.dismiss();
			}
		}
	}
}
