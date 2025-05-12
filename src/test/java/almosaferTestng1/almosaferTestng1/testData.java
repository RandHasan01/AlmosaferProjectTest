package almosaferTestng1.almosaferTestng1;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Random;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class testData {

	WebDriver driver = new EdgeDriver();
	Random rand = new Random();

	String[] languages = { "en", "ar" };
	String URL = "https://www.almosafer.com/en";
	String selectedLang;

	String expectedLang = "en";

	String expectedContactNumber = "+966554400000";

	String expectedCurrency = "SAR";

	LocalDate today = LocalDate.now();
	LocalDate todayPlusOne = today.plusDays(1);
	String expectedFlightDepartureDay = String.format("%02d", todayPlusOne.getDayOfMonth());

	LocalDate todayPlusTwo = today.plusDays(2);
	String expectedFlightReturnDay = String.format("%02d", todayPlusTwo.getDayOfMonth());

	int randomIndexLang = rand.nextInt(languages.length);

	String[] enLocations = { "Dubai", "Jeddah", "Riyadh" };
	String[] arLocations = { "دبي", "جدة" };
	int randomIndexEnLocation = rand.nextInt(enLocations.length);
	int randomIndexArLocation = rand.nextInt(arLocations.length);

	int randomIndexOption = rand.nextInt(2);

	JavascriptExecutor js = (JavascriptExecutor) driver;

	public void Setup() {
		driver.get(URL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	}

}
