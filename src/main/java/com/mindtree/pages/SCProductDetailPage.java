package com.mindtree.pages;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mindtree.utility.BaseTest;
import com.relevantcodes.extentreports.LogStatus;

public class SCProductDetailPage extends BaseTest{

	WebDriver driver ;
	
	public SCProductDetailPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath = ".//h1[@itemprop='name']")
	WebElement lbl_productName;
	
	public void verifyTheproductNameInThePDP() {
		
		String parent = driver.getWindowHandle();
		Set<String> s = driver.getWindowHandles();
		
		Iterator<String> itr = s.iterator();
		
		while(itr.hasNext()) {
			
			String child = itr.next();
			if(!parent.equalsIgnoreCase(child)) {
				driver.switchTo().window(child);
				if(lbl_productName.isDisplayed()) {
					report.log(LogStatus.PASS, "Product name "+lbl_productName.getText()+" is displayed in PDP");
				}else {
					report.log(LogStatus.FAIL, "Product name "+lbl_productName.getText()+" is not displayed in PDP");
					assertion.assertTrue(false);
				}
				driver.close();
			}
		}
		driver.switchTo().window(parent);
	}
	
	
	
	
	
}
