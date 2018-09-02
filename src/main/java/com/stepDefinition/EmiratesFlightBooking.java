package com.stepDefination;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class EmiratesFlightBooking {

	public WebDriver driver;
	String baseURL = "https://www.emirates.com/ae/english/";

	@Before
	public void before() {
		System.setProperty("webdriver.gecko.driver", "Resource/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get(baseURL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Given("^that Bill has decided to check available flights$")
	public void that_Bill_has_decided_to_check_available_flights() {
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.emirates.com/ae/english/");

	}

	@When("^he looks at a return trip from (\\w+) to (\\w+) leaving one week from now$")
	public void he_looks_at_a_return_trip_from_DXB_to_LHR_leaving_one_week_from_now(String depart, String arrival) {
		WebElement departureTextBox = driver.findElement(By.name("Departure airport"));
		departureTextBox.clear();
		departureTextBox.sendKeys(depart);

		WebElement departureDrop = driver
				.findElement(By.xpath("//div[@class='dropdown']//li[@data-dropdown-id='" + depart + "']"));
		departureDrop.click();

		WebElement arrivalTextBox = driver.findElement(By.name("Arrival airport"));
		arrivalTextBox.clear();
		arrivalTextBox.sendKeys(arrival);

		WebElement arrivalDrop = driver
				.findElement(By.xpath("//div[@class='dropdown']//li[@data-dropdown-id='" + arrival + "']"));
		arrivalDrop.click();

		String startDate = datePicker(0);
		String endDate = datePicker(7);

		WebElement departDate = driver.findElement(By.xpath("//a[@aria-label='" + startDate + "']"));
		departDate.click();

		WebElement returnDate = driver.findElement(By.xpath("//a[@aria-label='" + endDate + "']"));
		returnDate.click();

		WebElement searchButton = driver.findElement(By.xpath("//button/span[text()='Search flights']"));
		searchButton.click();
	}

	@Then("^he should be shown the cheapest return ticket from (\\w+) to (\\w+)$")
	public void he_should_be_shown_the_cheapest_return_ticket_from_DXB_to_LHR(String depart, String arrival) {	
		scroll();
		
		WebElement currency = driver.findElement(By.xpath("//strong[@class='price']"));
		System.out.println("Cheapest Return Ticket : "+currency.getText());
	}

	@After
	public void after() {
		driver.quit();
	}

	public String datePicker(int days) {
		String pattern = "dd  MMM yy";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, days);

		return sdf.format(cal.getTime()).toString();

	}
	
	public void scroll()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
	}
}
