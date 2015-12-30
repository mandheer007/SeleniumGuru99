package day01;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class TestScriptTestNG {
  @Test
  public void executeScript() {
	  
  	//Setup Firefox driver    	
  	WebDriver driver = new FirefoxDriver();
  	String baseUrl = "http://www.demo.guru99.com/V4/";
  	
  	// launch Firefox and direct it to the Base URL
  	driver.get(baseUrl);


	    // Enter username
	    driver.findElement(By.name("uid")).sendKeys("mngr26059");

	    // Enter Password
	    driver.findElement(By.name("password")).sendKeys("EzatygA");
 
	    // Click Login
	    driver.findElement(By.name("btnLogin")).click();

  }
}
