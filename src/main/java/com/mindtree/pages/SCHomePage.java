package com.mindtree.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mindtree.utility.BaseTest;
import com.relevantcodes.extentreports.LogStatus;

public class SCHomePage extends BaseTest{

	WebDriver driver ;
	
	public SCHomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath = ".//li[@id='sign-in']")
	WebElement lnk_SignIN;
	
	public void clickOnSignInLink() {
		lnk_SignIN.click();
		report.log(LogStatus.PASS, "clicked on the SignInLink");
		if(driver.findElements(By.xpath(".//button[contains(@class,'moe-btn-close')]")).size()>0) {
			 try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			btn_locationClose.click();
		}
	}
	
	@FindBy(xpath = ".//input[@id='autocomplete']")
	WebElement txt_searchBox;
	
	@FindBy(xpath = ".//*[@class='srch_action btn orange']")
	WebElement btn_search;
	
	@FindBy(xpath = ".//button[contains(@class,'moe-btn-close')]")
	WebElement btn_locationClose;
	
	
	
	public void searchProductswithKeyword(String keyword) {
		txt_searchBox.clear();
		txt_searchBox.sendKeys(keyword);
		report.log(LogStatus.PASS, "Enterd the value "+keyword+" in the search box");
		if(driver.findElements(By.xpath(".//button[contains(@class,'moe-btn-close')]")).size()>0) {
			 try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			btn_locationClose.click();
		}
		btn_search.click();
		report.log(LogStatus.PASS, "clicked on the Search button");
		
	}
	
	
	public void searchProductswithKeywordUsingRobotClass(String keyword) {
		txt_searchBox.clear();
		txt_searchBox.sendKeys(keyword);
		report.log(LogStatus.PASS, "Enterd the value "+keyword+" in the search box");
		if(driver.findElements(By.xpath(".//button[contains(@class,'moe-btn-close')]")).size()>0) {
			 try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			btn_locationClose.click();
		}
		//btn_search.click();
		report.log(LogStatus.PASS, "clicked on the Search button");
		
		
		int xaxis = btn_search.getLocation().x;
		 
		int yaxis=btn_search.getLocation().y;
		 
		int width = btn_search.getSize().width;
		int height= btn_search.getSize().height;
		 
		Robot r;
		try {
			r = new Robot();
			r.mouseMove(xaxis+width/2, yaxis+height/2+110);
			r.mousePress(KeyEvent.BUTTON1_DOWN_MASK);//click function
			r.mouseRelease(KeyEvent.BUTTON1_DOWN_MASK);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
}
