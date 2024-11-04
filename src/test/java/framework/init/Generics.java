package framework.init;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.IResultMap;
import org.testng.ITestResult;
import org.testng.internal.Utils;

import com.github.javafaker.Faker;

import framework.utils.DateTimeFormat;
import framework.utils.Record;
import framework.utils.TestLogger;

/**
 * Created by TestUnity
 * Date: 2024-10-30
 * Project Name: AccuMExS
 */

public class Generics extends TestLogger implements DateTimeFormat {

    private static final Faker faker = new Faker(new Locale("en-US"));
    public static String screenshotPath = "";
    protected static WebDriverWait wait;

    /**
     * Pause for passed seconds
     *
     * @param secs Time in Seconds
     */
    public static void pause(int... secs) {
        try {
            Thread.sleep((secs.length != 0 ? secs[0] : STATIC_WAIT) * 1000L);
        } catch (InterruptedException interruptedException) {
            System.err.println("Failure in Pause.");
        }
    }

    /**
     * Pause for passed milliseconds
     *
     * @param ms Time in millis
     */
    public static void pause(long... ms) {
        try {
            Thread.sleep((ms.length != 0 ? ms[0] : STATIC_WAIT));
        } catch (InterruptedException interruptedException) {
            System.err.println("Failure in Pause.");
        }
    }

    /**
     * Send value to text field
     *
     * @param driver        WebDriver
     * @param webElement    WebElement
     * @param value         String Value
     * @param isBeforeClick click before entering text
     */
    public static void type(WebDriver driver, WebElement webElement, String value, Boolean isBeforeClick) {
        if (isBeforeClick) clickOn(driver, webElement);
        pause(1);
        sendKeys(webElement, value);
        pause(500L);
    }

    /**
     * Send value to text field by characters
     *
     * @param webElement WebElement
     * @param value      String Value
     */
    public static void typeSlow(WebElement webElement, String value) {
        clear(webElement);
        for (int index = 0; index < value.length(); index++) {
            if (index == 0) sendKeys(webElement, "X");
            sendKeys(webElement, String.valueOf(value.charAt(index)));
            pause(1);
        }
    }

    /**
     * To clear the value from the text field
     *
     * @param webElement WebElement
     */
    public static void clear(WebElement webElement) {
        webElement.clear();
    }
    
    public void clearFieldByBackspace(WebElement webElement) {
        webElement.sendKeys(Keys.CONTROL + "a");
        webElement.sendKeys(Keys.BACK_SPACE);
    }


    /**
     * Send value to text field
     *
     * @param webElement WebElement
     * @param value      String Value
     */
    public static void sendKeys(WebElement webElement, String value ) {
        webElement.sendKeys(value);
    }

    /**
     * To click on particular WebElement
     *
     * @param driver  WebDriver
     * @param element WebElement
     */
    public static void clickOn(WebDriver driver, WebElement element) {
        try {
            element.click();
        } catch (ElementClickInterceptedException ex) {
            clickOnJS(driver, element);
        }
    }

    /**
     * To double click on particular WebElement
     *
     * @param driver  WebDriver
     * @param element WebElement
     */
    public static void doubleClickOn(WebDriver driver, WebElement element) {
        try {
            Actions actions = new Actions(driver);
            actions.doubleClick(element).build().perform();
        } catch (ElementClickInterceptedException ignore) {

        }
    }

    /**
     * To click on element via JS
     *
     * @param driver  WebDriver
     * @param element WebElement
     */
    public static void clickOnJS(WebDriver driver, WebElement element) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    /**
     * To get text from the element
     *
     * @param element WebElement
     * @return Text from the WebElement
     */
    public static String getText(WebElement element) {
        return element.getText().trim();
    }

    /**
     * To get text from the element
     *
     * @param element WebElement
     * @return Text from the WebElement
     */
    public static String getValue(WebElement element) {
        return element.getAttribute("value").trim();
    }

    /**
     * To get text from the element
     *
     * @param element WebElement
     * @return Text from the WebElement
     */
    public static String getInnerText(WebElement element) {
        return element.getAttribute("innerText").trim();
    }

    /**
     * To get text from the element via JS
     *
     * @param element WebElement
     * @return Text from the WebElement
     */
    public static String getTextJS(WebDriver driver, WebElement element) {
        return ((JavascriptExecutor) driver).executeScript("return $(arguments[0]).text();", element).toString();
    }

    /**
     * To check if element is available in page or not
     *
     * @param element WebElement
     * @return if web element display or not
     */
    public static boolean isElementDisplay(WebElement element) {
        return element.isDisplayed();
    }

    /**
     * To check if element is available in page or not
     *
     * @param element WebElement
     * @return if web element display or not
     */
    public static boolean isElementPresent(WebElement element) {
        debugLog("isElementPresent::" + element.getText());
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException nse) {
            return false;
        }
    }

    /**
     * To check if element is available in page or not
     *
     * @param driver  WebDriver instance
     * @param locator xpath for the element
     * @return if web element display or not
     */
    public static boolean isElementPresent(WebDriver driver, String locator) {
        try {
            return driver.findElement(By.xpath(locator)).isDisplayed();
        } catch (NoSuchElementException nse) {
            return false;
        }
    }

    /**
     * To scroll to the element in page
     *
     * @param driver  WebDriver
     * @param element WebElement
     */
    public static void scrollToElement(WebDriver driver, WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
        pause(500L);
    }
    
    public static void scrollToElementJS(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }


    /**
     * To scroll to the element in page
     *
     * @param element WebElement
     */
    public static void scrollElement(WebElement element) {
        Coordinates cor = ((Locatable) element).getCoordinates();
        cor.inViewPort();
        pause(2);
    }

    /**
     * To scroll to the bottom of the page
     *
     * @param driver WebDriver
     */
    public static void scrollToBottom(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    /**
     * To highlight the selected element
     *
     * @param driver  WebDriver
     * @param element WebElement
     */
    public static void highlightElement(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border = '3px solid yellow'", element);
        pause(2);
    }
    /**
     * To switch to a child window
     *
     * @param driver WebDriver instance
     */
    public static void switchToChildWindow(WebDriver driver) {
        // Get the parent window handle
        String parentWindow = driver.getWindowHandle();
        
        // Get all window handles
        Set<String> windowHandles = driver.getWindowHandles();
        
        // Iterate through window handles
        for (String windowHandle : windowHandles) {
            // If the window handle is not the parent window, switch to the child window
            if (!windowHandle.equals(parentWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

    /**
     * To switch back to the parent window
     *
     * @param driver WebDriver instance
     */
    public static void switchToParentWindow(WebDriver driver) {
        // Get the parent window handle
        String parentWindow = driver.getWindowHandle();
        
        // Switch back to the parent window
        driver.switchTo().window(parentWindow);
    }

    public static void closeChildWindow(WebDriver driver) {
       // Close the current window (child window)
    	driver.close();
    }

    /**
     * To open the URL in browser window
     *
     * @param driver WebDriver
     * @param url    URL String
     */
    public static void openURL(WebDriver driver, String url) {
        driver.get(url);
    }

    /**
     * To get the title of the page
     *
     * @param driver WebDriver
     */
    public static String getTitle(WebDriver driver) {
        return driver.getTitle();
    }

    /**
     * To close the driver instance
     *
     * @param driver WebDriver
     */
    public static void close(WebDriver driver) {
        driver.close();
    }

    /**
     * To refresh the web page
     *
     * @param driver WebDriver
     */
    public static void refresh(WebDriver driver) {
        driver.navigate().refresh();
    }

    /**
     * To get string with the random characters for with the passed characters length limit
     *
     * @param length String length
     * @return Random string
     */
    public static String getRandomCharacters(int length) {
        return RandomStringUtils.randomAlphabetic(length);
    }

    /**
     * To get the invalid email address
     *
     * @return Invalid Email Address
     */
    public static String getInvalidEmail() {
        return getRandomFirstName().toLowerCase() + "." + getRandomLastName().toLowerCase() + "testmail.com";
    }
    


   /*/ * Get the page load time in milliseconds.
    *
    * @param driver WebDriver instance
    * @return Page load time in milliseconds
    */
   public static long getPageLoadTime(WebDriver driver) {
       JavascriptExecutor js = (JavascriptExecutor) driver;

       // Execute JavaScript to get performance timing data
       Long navigationStart = (Long) js.executeScript("return window.performance.timing.navigationStart;");
       Long loadEventEnd = (Long) js.executeScript("return window.performance.timing.loadEventEnd;");

       // Calculate the load time
       return loadEventEnd - navigationStart;
   }

   
    /**
     * To get the valid email address
     *
     * @return Valid Email Address
     */
    public static String getRegistrationEmail() {
        return "auto_test_" + System.currentTimeMillis() + "@mailinator.com";
    }

    /**
     * To get the random first name
     *
     * @return First name
     */
    public static String getRandomFirstName() {
        return faker.name().firstName().replaceAll("'", "");
    }

    /**
     * To get the random last name
     *
     * @return last name
     */
    public static String getRandomLastName() {
        return faker.name().lastName().replaceAll("'", "");
    }

    /**
     * To get the random gender
     *
     * @return Gender
     */
    public static String getRandomGender() {
        String[] gender = {"Male", "Female", "Unknown", "X"};
        return gender[getRandomNumberBetween(0, gender.length - 1)];
    }

    /**
     * To get the full name
     *
     * @return Full name
     */
    public static String getFullName() {
        return getRandomFirstName() + " " + getRandomLastName();
    }

    /**
     * To get the random street name
     *
     * @return Street name
     */
    public static String getRandomStreetName() {
        return faker.address().streetName();
    }

    /**
     * To get the random boolean value
     *
     * @return true | false
     */
    public static boolean getRandomBoolean() {
        return Math.random() < 0.5;
    }

    /**
     * To get the random password
     *
     * @return Random Password
     */
    public static String getRandomPassword() {
        return getRandomCharacters(3).toLowerCase() + getRandomCharacters(2).toUpperCase() + "@" + getRandomNumber();
    }

    /**
     * To get the random number from 10000 to 99999
     *
     * @return Random Number
     */
    public static int getRandomNumber() {
        return faker.number().numberBetween(10000, 99999);
    }

    /**
     * To get the random mobile number
     *
     * @return Mobile number
     */
    public static  long getRandomMobileNumber(int... number) {
        return number.length == 0 ?
                Long.parseLong(getRandomNumber() + "" + getRandomNumber()) :
                Long.parseLong(getRandomNumber() + "" + getRandomNumber() + getRandomNumberBetween(0, 9));
    }

    /**
     * To get the random number from min to max value
     *
     * @return Random Number
     */
    public static int getRandomNumberBetween(int min, int max) {
        return faker.number().numberBetween(min, max);
    }

    /**
     * To get the random float number
     *
     * @return Random Number
     */
    public static double getRandomNumberDouble(int min, int max) {
        return faker.number().randomDouble(5, min, max);
    }

    /**
     * To check if the list is empty or not
     *
     * @param list List
     * @return true if list is empty
     */
    public static boolean isListEmpty(List<?> list) {
        return list.isEmpty();
    }

    /**
     * To get the size of list
     *
     * @param list List
     * @return list size
     */
    public static int sizeOf(List<?> list) {
        return list.size();
    }

    /**
     * To get the last index of list
     *
     * @param list List
     * @return last index
     */
    public static int lastIndexOf(List<?> list) {
        return sizeOf(list) - 1;
    }

    /**
     * To get the double from string
     *
     * @param str String
     * @return double value
     */
    public static double getDoubleFromString(String str) {
        return Double.parseDouble(str.replaceAll("[^0-9.-]+", ""));
    }

    /**
     * To get the failure exception in single line
     *
     * @param tests Test Result
     */
    public static String getShortException(IResultMap tests) {
        String exceptions = "";
        ITestResult result = tests.getAllResults().stream().reduce((one, two) -> two).get();
        Throwable exception = result.getThrowable();
        boolean hasThrowable = exception != null;
        if (hasThrowable) {
            String str = Utils.shortStackTrace(exception, true);
            System.out.println(str);
            Scanner scanner = new Scanner(str);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.trim().startsWith("at ")) break;
                exceptions = exceptions.concat(line + SEPARATOR);
            }
        }
        return exceptions;
    }

    /**
     * To get the screenshot for Extent Report
     *
     * @param driver         WebDriver instance
     * @param screenshotName name of the screenshot
     * @return screenshot URL
     */
    public static String getExtentScreenShot(WebDriver driver, String screenshotName) {
        String destination = "";
        screenshotPath = "Screenshots" + File.separator + screenshotName + "_" + getCurrentTimeStampString() + ".png";
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            destination = REPORT_PATH + File.separator + screenshotPath;
            File finalDestination = new File(destination);
            FileUtils.copyFile(source, finalDestination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return screenshotPath;
    }

    /**
     * To get the current timestamp
     *
     * @return current timestamp
     */
    public static String getCurrentTimeStampString() {
        Date date = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("MMddHHmmssSS");
        TimeZone timeZone = TimeZone.getDefault();
        Calendar cal = Calendar.getInstance(new SimpleTimeZone(timeZone.getOffset(date.getTime()), "GMT"));
        sd.setCalendar(cal);
        return sd.format(date);
    }

    /**
     * To scroll to the top of the page
     *
     * @param driver WebDriver instance
     */
    public static void scrollToTop(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0);");
    }

    /**
     * To start the recording for the test cases
     *
     * @param suiteName name of the suite
     */
    public static void startRecording(String suiteName) throws Exception {
        if (Boolean.parseBoolean(VIDEO_RECORD))
            Record.startRecord(suiteName);
    }

    /**
     * To stop the recording for the test cases
     */
    public static void stopRecording() throws Exception {
        if (Boolean.parseBoolean(VIDEO_RECORD)) Record.stopRecord();
    }

    /**
     * To delete the cookies from the instance
     *
     * @param driver WenDriver instance
     */
    public void deleteCookies(WebDriver driver) {
        driver.manage().deleteAllCookies();
    }

    /**
     * To quit the browser instance
     *
     * @param driver WenDriver instance
     */
    public void quit(WebDriver driver) {
        driver.quit();
    }

    /**
     * To maximize the browser window
     *
     * @param driver WenDriver instance
     */
    public void maximizeWindow(WebDriver driver) {
        driver.manage().window().maximize();
    }
    
    /**
     * To hover over and click a WebElement
     *
     * @param driver WebDriver instance
     * @param element WebElement to be hovered over and clicked
     */
    public static void hoverAndClick(WebDriver driver, WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();
    }
    
    public static void clickByCoordinates(WebDriver driver,  int xOffset, int yOffset) {
    	  Actions actions = new Actions(driver);
    	  actions.moveByOffset(xOffset, yOffset).click().perform();
	}

    
    public static void moveToElement(WebDriver driver, WebElement element, int x, int y) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element, x, y).click().perform();
    }
    /**
     * To wait for the element implicitly
     *
     * @param driver  WenDriver instance
     * @param seconds time in seconds
     */
    public  void implicitWaitOf(WebDriver driver, int... seconds) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds.length != 0 ? seconds[0] : IMPLICIT_WAIT));
    }
    
    public static void scrollByOffset(WebDriver driver, int xOffset, int yOffset) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(arguments[0], arguments[1]);", xOffset, yOffset);
    }

    public void sendKeyJS(WebDriver driver ,WebElement element ,  String value ){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value='" + value + "';", element);
    }

    public boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed(); // Returns true if the element is visible
        } catch (NoSuchElementException e) {
            return false; // Element is not found, so it's not displayed
        }
    }
    
    

}