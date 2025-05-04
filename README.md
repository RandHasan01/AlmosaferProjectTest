# Almosafer Website Automation Tests

This project contains **automated tests** using **Selenium WebDriver** and **TestNG** to verify key functionalities on the [almosafer.com](https://www.almosafer.com/) website.

## 📋 Requirements

- Java 8 or higher
- Selenium WebDriver
- TestNG
- Microsoft Edge Browser + Edge WebDriver

## ⚙️ Environment Setup

1. Install Java and configure `JAVA_HOME`.
2. Download and install Microsoft Edge WebDriver (ensure the version matches your browser).
3. Add the following libraries to your project (via Maven or manually adding JAR files):
   - selenium-java
   - testng

## 📝 Test Scenario

This automation project validates the core functionalities of the Almosafer website.  
The automated flow covers the following steps:

1. **Navigate** to: [https://www.almosafer.com/en](https://www.almosafer.com/en)

2. **Initial Assertions**:
   - Default language is **English (EN)**.
   - Default currency is **Saudi Riyal (SAR)**.
   - Contact number is displayed correctly.
   - **Qitaf** logo is visible in the footer.
   - Hotels search tab is **NOT** selected by default.
   - Flight departure date = **today + 1 day**
   - Flight return date = **today + 2 days**

3. **Randomly Change Website Language**
   - Switch between **Arabic (AR)** and **English (EN)** randomly.
   - Verify language updates correctly.

4. **Switch to Hotel Search Tab**
   - In location field, type a random city:
     - If **EN** → `Dubai`, `Jeddah`, `Riyadh`
     - If **AR** → `دبي`, `جدة`, `رياض`
   - Select first result from suggestions.

5. **Randomly select room & guests configuration**
   - Randomly select:
     - `1 room, 2 adults, 0 children`
     - `1 room, 1 adult, 0 children`

6. **Click Search Hotels button**

7. **On the Search Results Page**
   - Wait for full page load.
   - Confirm search results are displayed.

## 🧪 Test Methods

The `AppTest` class contains the following tests:

| Test Method | Description |
|-------------|-------------|
| `checkDefaultLangIsEn` | Verifies default website language is English. |
| `checkDefaultCurrencyisSAR` | Verifies default currency is Saudi Riyal (SAR). |
| `checkContactNumber` | Validates displayed customer service number. |
| `checkQitafLogoIsDisplayed` | Ensures Qitaf logo is visible. |
| `checkHotelSearchNotSelectedByDefault` | Hotel tab is not selected by default. |
| `checkFlightDepartureDefaultDate` | Departure date = today +1. |
| `checkFlighReturnDefaultDate` | Return date = today +2. |
| `changeLanguage` | Randomly changes and verifies language. |
| `typeValueInhotelSearchInputBasedOnLang` | Types random city based on language. |
| `chooseRandomRoom` | Selects random room config. |
| `clickSearchButton` | Clicks search button. |
| `checkThePageIsUploaded` | Ensures results page loaded. |

## 🛠️ Execution Details

- **Browser Used:** Microsoft Edge  
- **Test Execution Framework:** TestNG  
- **Page Load Validation:** `WebDriverWait` + `JavascriptExecutor`

## ▶️ How to Run Tests

From your IDE (IntelliJ, Eclipse, VS Code) → Right-click `AppTest.java` → **Run**

## 📂 Test Data (testData.java)

Contains key variables used in tests:
- `URL`, `expectedLang`, `expectedCurrency`, `expectedContactNumber`
- Random values for language, location, room selection

## 👨‍💻 Author

This project was developed to automate tests for the Almosafer website using Java, Selenium, and TestNG.
