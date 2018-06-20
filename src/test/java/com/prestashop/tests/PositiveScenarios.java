package com.prestashop.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PositiveScenarios {
	
WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
	WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver();
	driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
	}
	
	@Test
	public void loginTest() throws InterruptedException {
	Faker faker = new Faker();
	String firstName=faker.name().firstName();
	String lastName=faker.name().lastName();
	String Email=faker.name().firstName()+"@gmail.com";
	String password = faker.number().digits(5);
	
	String winHandleBefore = driver.getWindowHandle();
		 
	driver.findElement(By.id("email_create")).sendKeys(Email+Keys.ENTER);
	Thread.sleep(3000);
	for(String winHandle : driver.getWindowHandles()){
		   driver.switchTo().window(winHandle);
		}
	driver.findElement(By.id("customer_firstname")).sendKeys(firstName);
	driver.findElement(By.id("customer_lastname")).sendKeys(lastName);
	Thread.sleep(2000);
	
	driver.findElement(By.id("passwd")).sendKeys(password);
	Thread.sleep(2000);
	
	driver.findElement(By.id("firstname")).sendKeys(firstName);
	driver.findElement(By.id("lastname")).sendKeys(lastName);
	driver.findElement(By.id("address1")).sendKeys(faker.address().streetAddress());
	driver.findElement(By.id("city")).sendKeys(faker.address().city());
	Select dropdown = new Select(driver.findElement(By.id("id_state")));
	dropdown.selectByIndex(faker.number().numberBetween(0, 49));
	driver.findElement(By.id("postcode")).sendKeys(faker.number().digits(5));
	driver.findElement(By.id("phone_mobile")).sendKeys(faker.number().digits(10));
	driver.findElement(By.id("submitAccount")).click();
	
	String accountName = driver.findElement(By.className("account")).getText();
	driver.findElement(By.className("logout")).click();
	driver.findElement(By.id("email")).sendKeys(Email);
	driver.findElement(By.id("passwd")).sendKeys(password);
	driver.findElement(By.id("SubmitLogin")).click();
	Assert.assertTrue((firstName+" "+lastName).equals(accountName));
	
	}

	@AfterMethod
	public void tearDown() {
		driver.close();
	}
}
