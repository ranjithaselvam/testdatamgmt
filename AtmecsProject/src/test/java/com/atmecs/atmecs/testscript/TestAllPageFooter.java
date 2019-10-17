package com.atmecs.atmecs.testscript;

import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
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
		 @DataProvider
		 public Object[][] footerContent() throws Exception 
		 {
		 Object data[][] = Utility.getExcel(Constants.footer_data_file, "Sheet1");
		 return data;
	      }
		 
		@Test(dataProvider="footerContent")

		
		public void validateFooter(String footer1,String footer2,String footer3,String footer4) throws IOException, Exception
		{
			base.clickOnWebElement(driver, Utility.propertyRead(Constants.home_page_loc_file, "loc_home"));
			
			
			String text1 = Base.getText(driver, Utility.propertyRead(Constants.home_page_loc_file, "loc_footer1"));
			System.out.println(text1);
			String text2=Base.getText(driver, Utility.propertyRead(Constants.home_page_loc_file,"loc_footer2"));
			System.out.println(text2);
			String text3=Base.getText(driver, Utility.propertyRead(Constants.home_page_loc_file,"loc_footer3"));
			System.out.println(text3);
			String text4=Base.getText(driver, Utility.propertyRead(Constants.home_page_loc_file,"loc_footer4"));
			System.out.println(text4);
			Base.pageValidation(text1, footer1);
			Base.pageValidation(text2, footer2);
			Base.pageValidation(text3, footer3);
			Base.pageValidation(text4, footer4);
		}
		@AfterClass
		public void closeBrwser() throws Exception {
			base.driverClose(driver);

		}



}
