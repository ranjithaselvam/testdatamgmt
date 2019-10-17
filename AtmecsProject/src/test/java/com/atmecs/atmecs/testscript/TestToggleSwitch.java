package com.atmecs.atmecs.testscript;

import java.io.IOException;

import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.atmecs.atmecs.base.Base;
import com.atmecs.atmecs.config.Constants;
import com.atmecs.atmecs.utility.Utility;



public class TestToggleSwitch extends Base {
	Base base=new Base();
	

	@BeforeClass
	public void launchBrowser() throws Exception ,IOException{
		base.getBrowser();
		base.getUrl();
	}
	@Test
	public void validateToggleSwitch() throws IOException, Exception
	{
		
		WebElement chkFbPersist = driver.findElement(By.cssSelector("span.lever"));
		for(int i=0; i<2; i++)
		{
			
		chkFbPersist.click();
		System.out.println(chkFbPersist.isSelected());
	}
	
	
    
}

	@AfterClass
	public void closeBrwser() throws Exception {
		base.driverClose(driver);

	}


}
