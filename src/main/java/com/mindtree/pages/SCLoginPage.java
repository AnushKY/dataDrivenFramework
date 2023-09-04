package com.mindtree.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mindtree.utility.BaseTest;
import com.relevantcodes.extentreports.LogStatus;

public class SCLoginPage extends BaseTest {

	WebDriver driver ;
	
	public SCLoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath = ".//*[@id='main_user_login']")
	WebElement txt_emailID;
	
	@FindBy(xpath = ".//*[@id='login_via_otp']")
	WebElement btn_login;
	
	@FindBy(xpath = ".//*[@class='error_text']/span")
	WebElement lbl_errMsg;
	
	public void enterMailOrPhoneNumberAndClickOnLogin(String inputText) {
		txt_emailID.sendKeys(inputText);
		report.log(LogStatus.PASS, "Filled in the email or phone number textbox with "+inputText);
		btn_login.click();
		report.log(LogStatus.PASS, "Clicked on the login button");
	}
	
	public void verifyErrorMessage() {
		
		if(lbl_errMsg.isDisplayed()) {
			report.log(LogStatus.PASS, "Error message "+lbl_errMsg.getText()+" is displayed");
		}else {
			report.log(LogStatus.FAIL, "Error message "+lbl_errMsg.getText()+" is not displayed");
			assertion.assertTrue(false);
		}
	}
	
}
