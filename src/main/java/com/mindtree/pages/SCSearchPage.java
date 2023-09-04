package com.mindtree.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mindtree.utility.BaseTest;
import com.relevantcodes.extentreports.LogStatus;

public class SCSearchPage extends BaseTest{

	WebDriver driver ;
	
	public SCSearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath = ".//div[@id='product_list']//div[@class='row'][1]//div[contains(@class,'column')][1]//h2")
	WebElement lbl_firstProdName;
	
	@FindBy(xpath = ".//div[@id='product_list']//div[@class='row'][1]//div[contains(@class,'column')][1]//span[@class='p_price']")
	WebElement lbl_firstProdPrice;
	
	public void verifyThefirstProductInTheSearchResult() {
		if(lbl_firstProdName.isDisplayed() && lbl_firstProdPrice.isDisplayed()) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			report.log(LogStatus.PASS, "First product name "+lbl_firstProdName.getText()+" is displayed");
			report.log(LogStatus.PASS, "First product price "+lbl_firstProdPrice.getText()+" is displayed");
		}else {
			report.log(LogStatus.FAIL, "First product name "+lbl_firstProdName.getText()+" is not displayed");
			report.log(LogStatus.FAIL, "First product price "+lbl_firstProdPrice.getText()+" is not displayed");
			assertion.assertTrue(false);
		}
		
		lbl_firstProdName.click();
		report.log(LogStatus.PASS, "clicked on the first product in the search result");
	}
	
	
	@FindBy(xpath = ".//i[@id='instdcnt']")
	WebElement lbl_resultCount;
	
	public void verifyTheResultCount() {
		String resultCount = lbl_resultCount.getText();
		int resultCo = Integer.parseInt(resultCount);
		if(lbl_resultCount.isDisplayed() && resultCo>=0) {
			report.log(LogStatus.PASS, "result count is  "+resultCount+" and it is greater than count 0");
			
		}else {
			report.log(LogStatus.PASS, "result count is  "+resultCount+" and it is lesser than count 0");
			assertion.assertTrue(false);
		}
	}
	
	
	
}
