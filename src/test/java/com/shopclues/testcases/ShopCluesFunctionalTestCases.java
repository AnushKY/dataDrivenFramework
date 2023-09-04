package com.shopclues.testcases;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mindtree.pages.SCHomePage;
import com.mindtree.pages.SCLoginPage;
import com.mindtree.pages.SCProductDetailPage;
import com.mindtree.pages.SCSearchPage;
import com.mindtree.utility.BaseTest;
import com.mindtree.utility.ExcelReader;
import com.relevantcodes.extentreports.LogStatus;

public class ShopCluesFunctionalTestCases extends BaseTest{

	public static SCHomePage hp;
	public static SCLoginPage lp;
	public static SCProductDetailPage pdp;
	public static SCSearchPage sp;
	public String testName;
	public String classname;
	ExcelReader excel;
	
	
	@BeforeMethod
	public void testInitiation(Method method) {
		
		//clearing assertion errors
		assertion.clearErrorLog();
		
		//test initialization
		testName = method.getName();
		System.out.println(testName);
		
		
		//report initialization
		report = extent.startTest(testName+" Test");
		
		excel = new ExcelReader();
		
	}
	
	@Test
	@Parameters({"Browser"})
	public void verifyInvalidLoginErrors(String browser) throws IOException {
		
		String url = excel.getCellValue(testName,2);
		
		navigateToUrl(browser,url);
		
		hp = new SCHomePage(driver);
		lp = new SCLoginPage(driver);
		
		hp.clickOnSignInLink();
		
		String inputText = excel.getCellValue(testName,3);
		
		lp.enterMailOrPhoneNumberAndClickOnLogin(inputText);
		
		lp.verifyErrorMessage();
		
		assertion.assertAll();
		assertion.clearErrorLog();
	}
	
	
	@Test
	@Parameters({"Browser"})
	public void verifyProductSearchFunctionality(String browser) throws IOException {
		
		String url = excel.getCellValue(testName,2);
		
		navigateToUrl(browser,url);
		
		hp = new SCHomePage(driver);
		lp = new SCLoginPage(driver);
		pdp = new SCProductDetailPage(driver);
		sp = new SCSearchPage(driver);
		
		for(int i=4; i<=6;i++) {
			hp.searchProductswithKeyword(excel.getCellValue(testName,i));
			sp.verifyThefirstProductInTheSearchResult();
			pdp.verifyTheproductNameInThePDP();
		}
		assertion.assertAll();
		assertion.clearErrorLog();
	}
	
	@Test
	@Parameters({"Browser"})
	public void verifyProductSearchResultCount(String browser) throws IOException {
		
		String url = excel.getCellValue(testName,2);
		
		navigateToUrl(browser,url);
		
		hp = new SCHomePage(driver);
		lp = new SCLoginPage(driver);
		pdp = new SCProductDetailPage(driver);
		sp = new SCSearchPage(driver);
		
		 hp.searchProductswithKeywordUsingRobotClass("Head phone");
		 try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 sp.verifyTheResultCount();
		
		assertion.assertAll();
		assertion.clearErrorLog();
	}
	
	@AfterMethod
	public void getResult(ITestResult result) {
	
		try {
			System.out.println(result.getStatus());
			
			  if(result.getStatus() == ITestResult.FAILURE) {
			  excel.WriteResult(testName,false);
			  report.log(LogStatus.FAIL,"Failed Testcase is :"+result.getName()); 
			  }else if(result.getStatus() == ITestResult.SUCCESS) {
			  excel.WriteResult(testName,true);
			  report.log(LogStatus.PASS,"Passed Testcase is :"+result.getName()); 
			  }else if(result.getStatus() == ITestResult.SKIP) {
			  report.log(LogStatus.SKIP,"Skipped Testcase is :"+result.getName()); 
			  }
			driver.quit();
			report.log(LogStatus.PASS, "Browser closed successfully");
			extent.endTest(report);
			extent.flush();
		}catch(Exception e) {
			e.printStackTrace();
		}
	
	}
	
}
