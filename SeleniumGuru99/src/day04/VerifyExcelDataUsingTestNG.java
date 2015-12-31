package day04;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import day04.Util;

public class VerifyExcelDataUsingTestNG 
{
	 
	  public String baseUrl;
	  public WebDriver driver; 
	  public String expected = null;
	  public String actual = null;
	  public String username, password;
	  public String actualTitle;
	  public String actualBoxtitle;
	  public int i = 0;
	  
  @Test(priority = 2)
  public void launchBrowser()
  {
	    File pathToBinary = new File(Util.FIREFOX_PATH);
		FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		driver = new FirefoxDriver(ffBinary, firefoxProfile);
		baseUrl = Util.BASE_URL;
		driver.manage().timeouts().implicitlyWait(Util.WAIT_TIME, TimeUnit.SECONDS);
		driver.get(baseUrl + "/V4/");
  }
  
  @Test(priority = 3)
  public void verifyHomwPageTittle()
  {
	    expected = Util.EXPECT_TITLE;
	    actual = driver.getTitle();
	    System.out.println(actual);
	    Assert.assertEquals(actual, expected);
  }

  @Test(priority = 1)
  public void launchApplication() throws Exception
  {
	     String[][] testData = 
			Util.getDataFromExcel(Util.FILE_PATH,Util.SHEET_NAME, Util.TABLE_NAME);
 
  	for (int i = 0; i < testData.length; i++) 
  	{
  	    username = testData[i][0]; 
  	    password = testData[i][1]; 
  	    
  	    launchBrowser();
  	    // Enter username
  	    driver.findElement(By.name("uid")).clear();
  	    driver.findElement(By.name("uid")).sendKeys(username);

  	    // Enter Password
  	    driver.findElement(By.name("password")).clear();
  	    driver.findElement(By.name("password")).sendKeys(password);

  	    // Click Login
  	    driver.findElement(By.name("btnLogin")).click();
         
  	  try
      { 
       	Alert alt = driver.switchTo().alert();
		actualBoxtitle = alt.getText();
		System.out.println(actualBoxtitle);
		//Thread.sleep(2000);
		alt.accept();
		
		if (actualBoxtitle.contains(Util.EXPECT_ERROR)) 
		    System.out.println("Test case SS[" + i + "]: Passed"); 
		else 
		    System.out.println("Test case SS[" + i + "]: Failed");
	   }    
       catch (NoAlertPresentException Ex)
       { 
    	actualTitle = driver.getTitle();
    	System.out.println(actualTitle);
		
		if (actualTitle.contains(Util.EXPECT_TITLE)) 
		    System.out.println("Test case SS[" + i + "]: Passed");
		else 
		    System.out.println("Test case SS[" + i + "]: Failed");
		
       }
  	}
  }
  
    @AfterTest
    public void terminateBrowser()
    {
    	driver.close();
    	driver.quit();
    }
    
}
