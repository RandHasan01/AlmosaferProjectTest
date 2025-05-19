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

	@BeforeTest
	public void mySetup() {

		Setup();
	}

	@Test(priority = 1)
	public void checkDefaultLangIsEn() {

		WebElement Popup = driver.findElement(By.id("mui-2"));
		Popup.click();

		String actualLang = driver.findElement(By.tagName("html")).getDomAttribute("lang");
		Assert.assertEquals(actualLang, expectedLang);

	}

	@Test(priority = 2)
	public void checkDefaultCurrencyisSAR() {
		String actualCurrency = driver.findElement(By.xpath("//div[@data-testid='Header__CurrencySelector']"))
				.getText();
		Assert.assertEquals(actualCurrency, expectedCurrency);
	}

	@Test(priority = 3)
	public void checkContactNumber() {

		// WebElement phoneNumberElement =
		// driver.findElement(By.xpath("//a[@class='sc-cjHlYL gdvIKd']//strong"));
		// String actualPhoneNumber = phoneNumberElement.getText().trim();
		// or
		String ActualContactNumber = driver
				.findElement(By.cssSelector(".__ds__comp.undefined.MuiBox-root.alm-desktop-h0bow9")).getText();
		Assert.assertEquals(ActualContactNumber, expectedContactNumber);

	}

	@Test(priority = 4)
	public void checkQitafLogoIsDisplayed() throws InterruptedException {

		// JavascriptExecutor js = (JavascriptExecutor) driver;
		// js.executeScript("window.scrollTo(0,18000)");
		// Thread.sleep(2000);

		// WebElement Logo =
		// driver.findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-lcpuFF.jipXfR"));

		// Assert.assertTrue(Logo.isDisplayed());

		// js.executeScript("window.scrollTo(18000,0)");

		WebElement footer = driver.findElement(By.tagName("footer"));
		WebElement QitafLogo = footer.findElement(By.xpath("//img[@alt='qitaf']"));

		Assert.assertTrue(QitafLogo.isDisplayed());

	}

	@Test(priority = 5)
	public void checkHotelSearchNotSelectedByDefault() {

		WebElement HotelSearchTabElement = driver.findElement(By.id("tab-hotels"));

		String actualHotelSearchValue = HotelSearchTabElement.getDomAttribute("aria-selected");
		Assert.assertEquals(actualHotelSearchValue, "false");

	}

	@Test(priority = 6)
	public void checkFlightDepartureDefaultDate() {

		String actualFlightDepartureDay = driver.findElement(By.id("testIdPickerPrefix__DatePicker__DepartureDate"))
				.getDomAttribute("value");
		System.out.println("actualFlightDepartureDay: " + actualFlightDepartureDay);
		Assert.assertEquals(actualFlightDepartureDay, expectedFlightDepartureDay);

	}

	@Test(priority = 7)
	public void checkFlighReturnDefaultDate() {

		String actualFlightReturnDay = driver.findElement(By.id("testIdPickerPrefix__DatePicker__ArrivalDate"))
				.getDomAttribute("value");
		System.out.println("actualFlightReturnDay: " + actualFlightReturnDay);
		Assert.assertEquals(actualFlightReturnDay, expectedFlightReturnDay);

	}

	@Test(priority = 8)
	public void changeLanguage() {

		selectedLang = languages[randomIndexLang];
		URL = "https://www.almosafer.com/" + selectedLang;

		System.out.println(URL);
		driver.get(URL);

		String actualLangChoosen = driver.findElement(By.tagName("html")).getDomAttribute("lang");

		Assert.assertEquals(actualLangChoosen, selectedLang);

	}

	@Test(priority = 9)
	public void fillHotelSearchByLang() throws InterruptedException {

		WebElement stays = driver.findElement(By.id("tab-hotels"));
		stays.click();

		WebElement hotelLocationField = driver.findElement(By.id("DesktopSearchWidget_Destination_InputField_Test_Id"));

		if (selectedLang.equals("en")) {
			hotelLocationField.sendKeys(enLocations[randomIndexEnLocation]);
			Thread.sleep(2000);
			hotelLocationField.sendKeys(Keys.ENTER);
		} else {
			hotelLocationField.sendKeys(arLocations[randomIndexArLocation]);
			Thread.sleep(2000);
			hotelLocationField.sendKeys(Keys.ENTER);
		}

	}

	@Test(priority = 10)
	public void chooseRandomRoom() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement chooseRoomField = driver.findElement(By.id("mui-3"));
		chooseRoomField.click();
		List<WebElement> roomOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.cssSelector(".MuiTypography-root.MuiTypography-body1.__ds__comp.undefined.alm-desktop-1uls8co")));
		int randomIndexOption = rand.nextInt(roomOptions.size());
		roomOptions.get(randomIndexOption).click();

	}

	@Test(priority = 11)
	public void clickSearchButton() {

		WebElement searchButton = driver.findElement(By.id("mui-4"));
		searchButton.click();
	}

	@Test(priority = 12)
	public void checkThePageIsUploaded() {

//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		WebElement resultFound = wait.until(
//				ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-testid='srp_properties_found']")));
//
//		boolean actualResult = resultFound.getText().contains("stays") || resultFound.getText().contains("إقامة");
//		Assert.assertEquals(actualResult, true);

		String pageState = (String) js.executeScript("return document.readyState");
		System.out.println("Document readyState is: " + pageState);
		Assert.assertEquals(pageState, "complete");

	}

	@AfterTest
	public void closeTheWebsite() {
		driver.close();
	}

}
