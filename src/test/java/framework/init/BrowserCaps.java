package framework.init;

import framework.utils.Configuration;
import org.openqa.selenium.Platform;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by TestUnity
 * Date: 2024-10-30
 * Project Name: AccuMExS
 */


public class BrowserCaps implements Configuration {

    public static String osName = "";
    public static String browserVersion = "";
    public static String browserName = "";

    /**
     * To configure the Firefox browser capabilities
     *
     * @return Firefox Browser Capabilities
     */
    static DesiredCapabilities configureMozillaFirefox() {

        // System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("marionette", true);
        capabilities.setCapability(CapabilityType.PLATFORM_NAME, Platform.LINUX);
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "firefox");

        browserName = capabilities.getBrowserName();
        osName = System.getProperty("os.name");
        browserVersion = capabilities.getBrowserVersion();

        System.out.println("OS Name : " + osName);
        System.out.println("Browser Name : " + browserName);
        System.out.println("Browser Version : " + browserVersion);

        return capabilities;
    }

    /**
     * To configure the Chrome browser capabilities
     *
     * @return Chrome Browser Capabilities
     */
    static DesiredCapabilities configureGoogleChrome() {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PLATFORM_NAME, Platform.WINDOWS);
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "chrome");

//        if (IS_HEADLESS.equalsIgnoreCase("true")) {
//            capabilities.addArguments("--no-sandbox");
//            capabilities.addArguments("--disable-dev-shm-usage");
//            capabilities.addArguments("disable-infobars");
//            capabilities.addArguments("--enable-precise-memory-info");
//            option.addArguments("--disable-popup-blocking");
//            option.addArguments("--disable-default-apps");
//            option.addArguments("--start-maximized");
//            option.addArguments("--headless");
//            option.addArguments("--window-size=1920x1080");
//        }

        Map<String, Object> preferences = new HashMap<>();
        preferences.put("profile.default_content_settings.popups", 0);
        preferences.put("download.default_directory", DOWNLOAD_FOLDER);
//        option.addArguments("incognito");
//        option.addArguments("user-agent=Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.102 Safari/537.36");
//        option.addArguments("--remote-allow-origins=*");
//
//        option.setExperimentalOption("prefs", preferences);

        browserName = capabilities.getBrowserName();
        osName = System.getProperty("os.name");
        browserVersion = capabilities.getBrowserVersion();

        System.out.println("OS Name : " + osName);
        System.out.println("Browser Name : " + browserName);
        System.out.println("Browser Version : " + browserVersion);

        return capabilities;
    }

    /**
     * To configure the Edge browser capabilities
     *
     * @return Edge Browser Capabilities
     */
    static DesiredCapabilities configureMicrosoftEdge() {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        capabilities.setCapability("requireWindowFocustxtBuyRateFC.getAttribute(\"value\")", true);

        browserName = capabilities.getBrowserName();
        osName = System.getProperty("os.name");
        browserVersion = capabilities.getBrowserVersion();

        System.out.println("OS Name : " + osName);
        System.out.println("Browser Name : " + browserName);
        System.out.println("Browser Version : " + browserVersion);

        return capabilities;
    }

}

