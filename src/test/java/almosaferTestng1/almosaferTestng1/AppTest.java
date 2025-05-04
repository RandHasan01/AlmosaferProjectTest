package almosaferTestng1.almosaferTestng1;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AppTest extends testData {

	WebDriver driver = new EdgeDriver();
	Random rand = new Random();

	@BeforeTest
	public void mySetup() {

		driver.get(URL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	}

	@Test(priority = 1)
	public void checkDefaultLangIsEn() {

		WebElement Popup = driver
				.findElement(By.cssSelector(".sc-jTzLTM.hQpNle.cta__button.cta__saudi.btn.btn-primary"));
		Popup.click();

		String actualLang = driver.findElement(By.tagName("html")).getDomAttribute("lang");
		Assert.assertEquals(actualLang, expectedLang);

	}

	@Test(priority = 2)
	public void checkDefaultCurrencyisSAR() {
		String actualCurrency = driver.findElement(By.cssSelector(".sc-hUfwpO.kAhsZG")).getText();
		Assert.assertEquals(actualCurrency, expectedCurrency);
	}

	@Test(priority = 3)
	public void checkContactNumber() {

		WebElement phoneNumberElement = driver.findElement(By.xpath("//a[@class='sc-cjHlYL gdvIKd']//strong"));
		String actualPhoneNumber = phoneNumberElement.getText().trim();

		Assert.assertEquals(actualPhoneNumber, expectedContactNumber);

	}

	@Test(priority = 4)
	public void checkQitafLogoIsDisplayed() throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,18000)");
		Thread.sleep(2000);

		WebElement Logo = driver.findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-lcpuFF.jipXfR"));

		Assert.assertTrue(Logo.isDisplayed());

		js.executeScript("window.scrollTo(18000,0)");

	}

	@Test(priority = 5)
	public void checkHotelSearchNotSelectedByDefault() {

		WebElement HotelSearchTabElement = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));

		String actualHotelSearchValue = HotelSearchTabElement.getDomAttribute("aria-selected");
		Assert.assertEquals(actualHotelSearchValue, expectedHotelSearchValue);

	}

	@Test(priority = 6)
	public void checkFlightDepartureDefaultDate() {

		String actualFlightDepartureDay = driver
				.findElement(By.xpath(
						"//div[@data-testid='FlightSearchBox__FromDateButton']//span[@class='sc-dXfzlN iPVuSG']"))
				.getText().trim();

		Assert.assertEquals(actualFlightDepartureDay, expectedFlightDepartureDay);

	}

	@Test(priority = 7)
	public void checkFlighReturnDefaultDate() {

		String actualFlightReturnDay = driver
				.findElement(By
						.xpath("//div[@data-testid='FlightSearchBox__ToDateButton']//span[@class='sc-dXfzlN iPVuSG']"))
				.getText();

		Assert.assertEquals(actualFlightReturnDay, expectedFlightReturnDay);

	}

	@Test(priority = 8)
	public void changeLanguage() {

		int randomIndexLang = rand.nextInt(languages.length);
		selectedLang = languages[randomIndexLang];
		URL = "https://www.almosafer.com/" + selectedLang;

		System.out.println(URL);
		driver.get(URL);

		String actualLangChoosen = driver.findElement(By.tagName("html")).getDomAttribute("lang");

		Assert.assertEquals(actualLangChoosen, selectedLang);

	}

	@Test(priority = 9)
	public void typeValueInhotelSearchInputBasedOnLang() throws InterruptedException {

		int randomIndexLocation = rand.nextInt(enLocations.length);

		WebElement stays = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		stays.click();

		WebElement hotelLocationField = driver.findElement(By.className("uQFRS"));

		if (selectedLang.equals("en")) {
			hotelLocationField.sendKeys(enLocations[randomIndexLocation]);
			Thread.sleep(2000);
			hotelLocationField.sendKeys(Keys.chord(Keys.ENTER));
		} else {
			hotelLocationField.sendKeys(arLocations[randomIndexLocation]);
			Thread.sleep(2000);
			hotelLocationField.sendKeys(Keys.chord(Keys.ENTER));
		}

	}

	@Test(priority = 10)
	public void chooseRandomRoom() {
		WebElement roomSelectTag = driver.findElement(By.cssSelector(".sc-tln3e3-1.gvrkTi"));
		Select select = new Select(roomSelectTag);
		List<WebElement> options = select.getOptions();
		int randomIndexOption = rand.nextInt(0, options.size() - 1);
		select.selectByIndex(randomIndexOption);

	}

	@Test(priority = 11)
	public void clickSearchButton() {

		WebElement searchButton = driver.findElement(By.xpath("//button[@data-testid='HotelSearchBox__SearchButton']"));
		searchButton.click();
	}

	@Test(priority = 12)
	public void checkThePageIsUploaded() {

//		WebElement resultsFound = driver.findElement(By.xpath("//span[@data-testid='srp_properties_found']"));
//		System.out.println(resultsFound.getText());
//		Assert.assertTrue(resultsFound.isDisplayed(), "Page did not fully load, element not displayed.");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		wait.until(
				driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));

		WebElement resultsFound = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-testid='srp_properties_found']")));

		System.out.println(resultsFound.getText());
		Assert.assertTrue(resultsFound.isDisplayed(), "Page did not fully load, element not displayed.");

	}

	@AfterTest
	public void closeTheWebsite() {
		driver.close();
	}

}
