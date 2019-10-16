package com.atmecs.atmecs.testscript;

import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.atmecs.atmecs.base.Base;
import com.atmecs.atmecs.config.Constants;
import com.atmecs.atmecs.utility.Utility;



public class TestAllPageFooter extends Base{
	 Base base=new Base();
		@BeforeClass
		public void launchBrowser() throws Exception ,IOException{
			base.getBrowser();
			base.getUrl();
		}
		@Test
		public void validateFooter() throws IOException, Exception
		{
			base.clickOnWebElement(driver, Utility.propertyRead(Constants.home_page_loc_file, "loc_home"));
			Base.getText(driver, Utility.propertyRead(Constants.home_page_loc_file, "loc_footer"));
			
		}
		@AfterClass
		public void closeBrwser() throws Exception {
			base.driverClose(driver);

		}



}
