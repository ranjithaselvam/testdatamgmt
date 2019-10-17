package com.atmecs.atmecs.base;

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
	import org.openqa.selenium.WebDriverException;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.openqa.selenium.ie.InternetExplorerDriver;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.Select;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import org.testng.Assert;

import com.atmecs.atmecs.config.Constants;
import com.atmecs.atmecs.utility.Utility;

	/**
	 * Purpose:To create reusable methods in the base class like
	 * sendKeys,getValue,isDisplayed and etc.
	 * 
	 * @author ranjitha.selvam
	 *
	 */
	public class Base {

		public static WebDriver driver;
		

		/**
		 * Different browser setup(Chrome,Firefox,Internetexplorer).
		 */
		
		public  WebDriver getBrowser( ) throws Exception {
			try {

				String browserName = Utility.propertyRead(Constants.config_file, "browserName");
				if (browserName.equalsIgnoreCase("chrome")) {
					System.setProperty("webdriver.chrome.driver", Constants.chrome_file);
					driver = new ChromeDriver();
				} else if (browserName.equalsIgnoreCase("firefox")) {
					System.setProperty("webdriver.gecko.driver", Constants.fireFox_file);
					driver = new FirefoxDriver();
				} else if (browserName.equalsIgnoreCase("internetExplorer")) {
					System.setProperty("webdriver.ie.driver", Constants.internetExplorer_file);
					driver = new InternetExplorerDriver();
				}
				Utility.logInfo("browser opened");
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				return driver;
			} catch (Exception e) {
				Utility.logInfo("browser not open");
			}
			return driver;
		}

		/*
		 * Get url from property file.
		 */
		public  void getUrl() throws Exception {
			try {
				String url = Utility.propertyRead(Constants.config_file, "url");
				
				driver.get(url);  
				Utility.logInfo("Entered url");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				Utility.logInfo("url not enter");
			}
		}

		/*
		 * To wait for certain conditions (Expected Conditions) or the maximum time
		 * exceeded before throwing an "ElementNotVisibleException" exception.
		 */
		public static void waitForElementVisibility(WebDriver driver,WebElement element) throws Exception {

			try {
				WebDriverWait wb = new WebDriverWait(driver, 60);
				wb.until(ExpectedConditions.visibilityOf(element));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
	

		/**
		 * Determine the the state of the application whether it is the same what we are
		 * expecting or not.
		 *
		 */
		public static void pageValidation(String actualDetails, String expectedDetails) {
			try {

				Assert.assertEquals(actualDetails, expectedDetails);
				Utility.logInfo(" Passed : " + " Expected : " + expectedDetails + " Actual : " + actualDetails);
			} catch (AssertionError assertionError) {
				Utility.logInfo(" Failed : " + " Expected : " + expectedDetails + " Actual : " + actualDetails);
			}

		}

		/**
		 * capable to check for the presence of all kinds of web elements available.
		 * used to verify if the web element is enabled or disabled within the webpage.
		 */
		public static boolean elementIsDisplayed(WebElement element) throws Exception {
			try {
				boolean displayed = element.isDisplayed();

				return displayed;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new Exception();
			}
		}

		/*
		 * Check particular text box is enable to print or not(visibility of the web
		 * element).
		 * 
		 */
		public boolean elementIsEnabled(WebDriver driver, String element) throws Exception {
			try {
				WebElement webElement = Utility.getbjectLocator(driver, element);
				boolean enabled = webElement.isEnabled();
				return enabled;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new Exception();
			}
		}

		/*
		 * Check particular radiobutton,dropdown,checkbox is selected or not
		 * 
		 */
		public static boolean elementIsSelected(WebElement element) throws Exception {
			try {
				boolean selected = element.isSelected();
				return selected;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new Exception();
			}
		}

		/*
		 * pass input values to the text box(enter the values).
		 * 
		 */
		public void inputValuesToTheWebelement(WebDriver driver, String element, String values) throws Exception {
			try {

				WebElement webElement = Utility.getbjectLocator(driver, element);
				webElement.sendKeys(values);
				Utility.logInfo("value passed");

			} catch (Exception e) {
				// TODO Auto-generated catch block
				Utility.logInfo("value not passed");
			}
		}

		/*
		 * Handling mouse event(Clicks at the current mouse location). Click button or
		 * whatever
		 */
		public void clickOnWebElement(WebDriver driver, String element) throws Exception {
			try {
				WebElement webElement = Utility.getbjectLocator(driver, element);
				webElement.click();
				Utility.logInfo("element is clicked");

			} catch (Exception e) {
				// TODO Auto-generated catch block
				Utility.logInfo("element not clickable");

			}
		}

		/*
		 * Close the current browse
		 * 
		 */
		public  void driverClose(WebDriver driver) throws Exception {
			try {
				driver.close();
				Utility.logInfo("current browser closed");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				Utility.logInfo("Browser not closed");
			}
		}

		

		/*
		 * Easy to choose or select an option given under any drop
		 * downs.(value,index,visibility)
		 */
		public  void selectDropDown(WebDriver driver, String element, String value, String option) throws Exception {
			try {
				// waitForElementVisibility(element);
				WebElement webElement = Utility.getbjectLocator(driver, element);
				System.out.println(element);
				Select sc = new Select(webElement);
				if (option.equalsIgnoreCase("value")) {
					sc.selectByValue(value);
				} else if (option.equalsIgnoreCase("visibletext")) {
					sc.deselectByVisibleText(value);
				} else if (option.equalsIgnoreCase("index")) {
					sc.selectByIndex(Integer.parseInt(value));
				}
				Utility.logInfo("select drop down");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				Utility.logInfo("drop down not selected");
			}
		}

		/*
		 * To move the window up and down.( Scroll by visible element)
		 */
		

		/*
		 * Scroll using pixcel.
		 */
		public  void scrollUsingPixels(WebDriver driver,int width, int height) throws Exception {
			try {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("windows.scrollBy(" + width + "," + height + ")");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new Exception();
			}
		}
		/*
		 * Scroll to bottom of the page
		 */

		public void scrollToBottomOfThePage(WebDriver driver) throws Exception {
			try {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("windows.scrollTo(0,document.body.scrollHeight)");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new Exception();
			}
		}
		
		/*
		 * Accept small message box which displays on-screen notification to give the
		 * user some kind of information or ask for permission to perform certain kind
		 * of operation.
		 * 
		 */

		public  void alertAccept(WebDriver driver) throws Exception {
			try {
				Alert alert = driver.switchTo().alert();
				alert.accept();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new Exception();
			}

		}

		/*
		 * Dismiss the on-screen notification
		 * 
		 */
		public  void alertDismiss(WebDriver driver) throws Exception {
			try {
				Alert alert = driver.switchTo().alert();
				alert.dismiss();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new Exception();
			}
		}

		/*
		 * Enter the values in pop up notification.
		 * 
		 */
		public  void alertSendKeys(WebDriver driver,String value) throws Exception {
			try {
				Alert alert = driver.switchTo().alert();
				alert.sendKeys(value);
				alert.accept();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new Exception();
			}
		}

		/*
		 * Switch to default content
		 * 
		 */
		public  void switchToDefaultContent(WebDriver driver) throws Exception {
			try {
				driver.switchTo().defaultContent();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new Exception();
			}
		}
		/*
		 * switch one frame to another frame.
		 */

		public  void switchToFrame(WebDriver driver,WebElement element) throws Exception {
			try {
				driver.switchTo().frame(element);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new Exception();
			}
		}

		/*
		 * Desirable for bug analysis. Take screenshots during execution.
		 */

		public  void ScreenShotOnTheWebPage(WebDriver driver,String filename) throws Exception {
			try {
				File des = new File("");
				TakesScreenshot ts = (TakesScreenshot) driver;
				File screenshotAs = ts.getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(screenshotAs, des);
			} catch (WebDriverException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new Exception();
			}
		}

		/*
		 * In order to shift focus from Parent Window to any child window
		 * 
		 */
		public  void switchToWindowHandle(WebDriver driver,String windowTitle) throws Exception {
			try {
				String fId = driver.getWindowHandle();
				Set<String> pId = driver.getWindowHandles();
				for (String x : pId) {
					driver.switchTo().window(x);
					String title = driver.getTitle();
					if (title.equals(windowTitle)) {
						driver.switchTo().window(x);

					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new Exception();
			}
		}

		/*
		 * Close all browser opened in webdriver.
		 * 
		 */
		public  void driverQuit(WebDriver driver) throws Exception {
			try {
				driver.quit();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new Exception();
			}
		}

		/*
		 * To print title of the page
		 * 
		 */
		public  String getTitle(WebDriver driver) throws Exception {
			try {
				String title = driver.getTitle();
				return title;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new Exception();
			}
		}
		/*
		 * Check whether particular page is open or not
		 * 
		 */

		public  String getCurrentUrl(WebDriver driver) throws Exception {
			try {
				String currentUrl = driver.getCurrentUrl();
				return currentUrl;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new Exception();
			}
		}

		/*
		 * Navigate one url to another url
		 * 
		 */

		public  void navigateToUrl(WebDriver driver,String url) throws Exception {
			try {
				driver.navigate().to(url);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new Exception();
			}
		}

		/*
		 * Move forward,backward and refresh
		 * 
		 */
		public void navigateCommands(WebDriver driver,String option) throws Exception {
			try {
				if (option.equalsIgnoreCase("refresh")) {
					driver.navigate().refresh();
				} else if (option.equalsIgnoreCase("backward")) {
					driver.navigate().back();
				} else if (option.equalsIgnoreCase("forward")) {
					driver.navigate().forward();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new Exception();
			}
		}

		/*
		 * Fetch dynamic content from web table
		 * 
		 */
		public  List<String> fetchDynamicContentFromWebTable(WebDriver driver,String header) throws Exception {
			try {
				List<String> li = new ArrayList<>();
				WebElement table = driver.findElement(By.tagName("//table"));
				List<WebElement> trow = table.findElements(By.tagName("tr"));
				for (int i = 0; i < trow.size(); i++) {
					List<WebElement> thead = trow.get(i).findElements(By.tagName("th"));
					for (int j = 0; j < thead.size(); j++) {
						String text = thead.get(j).getText();
						if (text.equals(header)) {
							List<WebElement> tdata = trow.get(i).findElements(By.tagName("td"));
							for (int k = 0; k < tdata.size(); k++) {
								String tdataContent = tdata.get(k).getText();
								li.add(tdataContent);
							}
						}
					}
				}
				return li;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new Exception();
			}
		}
		/*
		 * Retrieving the specified elements Text(Get Text). 
		 */

		public static String getText(WebDriver driver, String element) throws IOException {
			WebElement webElement=Utility.getbjectLocator(driver, element);
			System.out.println("Actual text :"+webElement);
			return webElement.getText();
		
			

		}

	}


