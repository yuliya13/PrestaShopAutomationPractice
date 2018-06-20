package com.prestashop.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NegativeScenarios {
	
WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
	WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver();
	driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
	}
	
	@Test
	public void wrongCredentialsTest() {
	driver.findElement(By.id("email")).sendKeys("java@gmail.com");
	driver.findElement(By.id("passwd")).sendKeys("qwert"+Keys.ENTER);
	String message = driver.findElement(By.xpath("//div[@class='alert alert-danger']")).getText();
	Assert.assertTrue(message.contains("Authentication failed"));
			
	}

	@Test
	public void  invalidEmailTest() {
		driver.findElement(By.id("email")).sendKeys("javagmailcom");
		driver.findElement(By.id("passwd")).sendKeys("qwert"+Keys.ENTER);
		String message = driver.findElement(By.xpath("//div[@class='alert alert-danger']")).getText();
		Assert.assertTrue(message.contains("Invalid email address"));
	}
	
	@Test
	public void blankEmailTest() {
		driver.findElement(By.id("email")).sendKeys("");
		driver.findElement(By.id("passwd")).sendKeys("qwert"+Keys.ENTER);
		String message = driver.findElement(By.xpath("//div[@class='alert alert-danger']")).getText();
		Assert.assertTrue(message.contains("An email address required"));
		
	}
	@Test
	private void blankPasswordTest() {
		driver.findElement(By.id("email")).sendKeys("java@gmail.com");
		driver.findElement(By.id("passwd")).sendKeys(""+Keys.ENTER);
		String message = driver.findElement(By.xpath("//div[@class='alert alert-danger']")).getText();
		Assert.assertTrue(message.contains("Password is required"));
	}
	
	@AfterMethod
	public void tearDown() {
		driver.close();
	}
}

