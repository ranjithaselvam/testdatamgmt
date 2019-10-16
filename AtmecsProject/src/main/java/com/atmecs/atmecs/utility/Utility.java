package com.atmecs.atmecs.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.atmecs.atmecs.config.Constants;

public class Utility {
	/**
	 * Utility class(property reader,excel reader,locator hierarchy)
	 * 
	 * @author ranjitha.selvam
	 */

	public static WebDriver driver;

	public static FileInputStream stream;
	private static Properties propertyFile = new Properties();

	static Workbook book;
	static Sheet sheet;
	static Logger logger = null;

	/*
	 * Property file Reader(read the data from property file)
	 * 
	 */
	public static String propertyRead(String path, String elements) throws IOException {
		propertyFile = new Properties();
		FileInputStream reader = new FileInputStream(path);
		propertyFile.load(reader);
		String data = propertyFile.getProperty(elements);
		//System.out.println(data);
		return data;
	}

	/*
	 * locator hierarchy
	 * 
	 */
	public static  WebElement getbjectLocator(WebDriver driver,String locatorName) throws IOException {
		
		String[] locatorType = locatorName.split(":");
		WebElement locator=null;
        switch (locatorType[0]) {
		case "Id":
			locator = driver.findElement(By.id(locatorType[1]));
			break;
		case "Name":
			locator = driver.findElement(By.name(locatorType[1]));
			break;
		case "CssSelector":
			locator = driver.findElement(By.cssSelector(locatorType[1]));
			break;
		case "LinkText":
			locator = driver.findElement(By.linkText(locatorType[1]));
			break;
		case "PartialLinkText":
			locator = driver.findElement(By.partialLinkText(locatorType[1]));
			break;
		case "TagName":
			locator = driver.findElement(By.tagName(locatorType[1]));
			break;
		case "Xpath":
			locator = driver.findElement(By.xpath(locatorType[1]));
			break;
		}
		return locator;
	}

	/*
	 * Take the value from excel file and passing the value to website. Apache poi
	 * supports to read value from excel file.
	 */

	public static Object[][] getExcel(String path, String sheetName) throws InvalidFormatException {
		File file = new File(path);
		FileInputStream read = null;
		try {
			read = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for (int rowCount = 0; rowCount < sheet.getLastRowNum(); rowCount++) {
			for (int cellCount = 0; cellCount < sheet.getRow(0).getLastCellNum(); cellCount++) {
				data[rowCount][cellCount] = sheet.getRow(rowCount + 1).getCell(cellCount).toString();
			}
		}
		return data;

	}

	/*
	 * Which keeps track of the record when any event happens or any software run.
	 */
	public static void logInfo(String message) {
		PropertyConfigurator.configure(Constants.log_file);
		logger = Logger.getLogger(Utility.class.getName());
		logger.info(message);
	}

}
