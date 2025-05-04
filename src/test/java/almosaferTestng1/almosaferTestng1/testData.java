package almosaferTestng1.almosaferTestng1;

import java.time.LocalDate;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class testData {

	String[] languages = { "en", "ar" };
	String URL = "https://www.almosafer.com/en";
	String selectedLang;

	String expectedLang = "en";

	String expectedContactNumber = "+966554400000";

	String expectedCurrency = "SAR";

	String expectedHotelSearchValue= "false";
	LocalDate today = LocalDate.now();
	LocalDate todayPlusOne = today.plusDays(1);
	String expectedFlightDepartureDay = String.format("%02d", todayPlusOne.getDayOfMonth());

	LocalDate todayPlusTwo = today.plusDays(2);
	String expectedFlightReturnDay = String.format("%02d", todayPlusTwo.getDayOfMonth());

	String[] enLocations = { "Dubai", "Jeddah", "Riyadh" };
	String[] arLocations = { "دبي", "جدة", "رياض" };

}
