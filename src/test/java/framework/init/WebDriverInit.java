package framework.init;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import accumex.ui.pageobjects.HomepagePo;
import accumex.ui.pageobjects.MemberRegPo;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.zeroturnaround.zip.ZipUtil;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import accumex.ui.pageobjects.LoginPO;
import framework.utils.Configuration;
import framework.utils.ExtentInit;

/**
 * Created by TestUnity
 * Date: 2024-10-30
 * Project Name: AccuMExS
 */

public class WebDriverInit extends Generics implements Configuration {

    public WebDriver driver;

    protected LoginPO loginPO;
    protected HomepagePo homepagePo;
    protected MemberRegPo memberRegPo;

	@BeforeSuite(alwaysRun = true)
    public void initReports(ITestContext testContext) {
        ExtentInit.initializeReport(testContext.getCurrentXmlTest().getSuite().getName());
    }

    @BeforeClass(alwaysRun = true)
    public void initRecording(ITestContext testContext) throws Exception {
        startRecording(testContext.getCurrentXmlTest().getSuite().getName());
    }

    @BeforeMethod(alwaysRun = true)
    public void initDriver() throws MalformedURLException {

        DesiredCapabilities capabilities = switch (BROWSER.toLowerCase()) {
            case "firefox", "mozilla firefox" -> BrowserCaps.configureMozillaFirefox();
            case "edge", "ms edge", "microsoft edge" -> BrowserCaps.configureMicrosoftEdge();
            default -> BrowserCaps.configureGoogleChrome();
        };

        //driver = new RemoteWebDriver(Configuration.getRemoteGridURL(), capabilities);

        //DesiredCapabilities dc = new DesiredCapabilities();
        DesiredCapabilities dc = switch (BROWSER.toLowerCase()) {
            case "firefox", "mozilla firefox" -> BrowserCaps.configureMozillaFirefox();
            case "edge", "ms edge", "microsoft edge" -> BrowserCaps.configureMicrosoftEdge();
            default -> BrowserCaps.configureGoogleChrome();
        };
        //String gridHost = System.getenv("SELENIUM_HOST") != null ? System.getenv("SELENIUM_HOST") : "localhost";
        //URL url = new URL("http://" + gridHost + ":4444/wd/hub");

       URL url = new URL("http://localhost:4444/wd/hub");
        dc.setBrowserName("chrome");
       // dc.setVersion("latest");
        //driver = new RemoteWebDriver(url, dc);

        driver = new ChromeDriver();
        implicitWaitOf(driver, IMPLICIT_WAIT);
        maximizeWindow(driver);

        openURL(driver, URL);
        
        loginPO = new LoginPO(driver);
        homepagePo = new HomepagePo(driver);
        memberRegPo = new MemberRegPo(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void cleaningDriver(ITestResult testResult) {

        String testName;
        ITestContext ex = testResult.getTestContext();

        try {
            testName = testResult.getName();
            if (testResult.getStatus() == ITestResult.FAILURE) {
                logger.log(Status.FAIL, MarkupHelper.createLabel("TEST CASE FAILED : " + testName, ExtentColor.RED));
                logger.log(Status.FAIL,
                        "<strong><em><font color='#f00'>" +
                                getShortException(ex.getFailedTests()) +
                                "</font></strong></em>");
                logger.info(MediaEntityBuilder.createScreenCaptureFromPath(getExtentScreenShot(driver, testName)).build());
                Reporter.setCurrentTestResult(testResult);
            } else if ((testResult.getStatus() == ITestResult.SUCCESS)) {
                logger.log(Status.PASS, MarkupHelper.createLabel("TEST CASE PASSED : " + testName, ExtentColor.GREEN));
                logger.info(MediaEntityBuilder.createScreenCaptureFromPath(getExtentScreenShot(driver, testName)).build());
            } else if ((testResult.getStatus() == ITestResult.SKIP)) {
                logger.log(Status.SKIP, MarkupHelper.createLabel("TEST CASE SKIPPED : " + testName, ExtentColor.ORANGE));
                logger.info(MediaEntityBuilder.createScreenCaptureFromPath(getExtentScreenShot(driver, testName)).build());
            }
        } catch (Exception throwable) {
            throwable.printStackTrace();
            System.err.println("Exception ::\n" + throwable);
        }

        deleteCookies(driver);
        quit(driver);
        
    }

    @AfterClass(alwaysRun = true)
    public void stopHelpers() throws Exception {
        stopRecording();
        
    }

    @AfterSuite(alwaysRun = true)
    public void endReport(ITestContext testContext) {
        ExtentInit.flushReport();
        ZipUtil.pack(new File(REPORT_PATH), new File(FOLDER_PATH + File.separator + "report" + System.currentTimeMillis() + ".zip"));
        ZipUtil.pack(new File(REPORT_PATH), new File(PROJECT_DIR + File.separator + "report_" + testContext.getCurrentXmlTest().getSuite().getName() + ".zip"));
       
    }

}
