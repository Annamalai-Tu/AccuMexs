package framework.utils;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.testng.Reporter;

/**
 * Created by TestUnity
 * Date: 2024-10-30
 * Project Name: AccuMExS
 */


public class TestLogger extends ExtentInit implements Configuration {

    /**
     * To log the Test Message in report
     *
     * @param log Log Message
     */
    public static void log(String log) {
        System.out.println(log.replaceAll("<[^>]*>", ""));
        Reporter.log("<br></br>" + log);
    }

    public static void debugLog(String message) {
        log("[DEBUG] : " + message);
    }

    /**
     * To log the Test Case Name in report
     *
     * @param log Log Message
     */
    public static void testCaseLog(String log) {
        logger = extent.createTest(log);
        logger.assignAuthor(System.getProperty("user.name"));
        log("<strong>" + log + "</strong>");
    }

    /**
     * To log the information passed during the test execution in report
     *
     * @param key   Key/Log Message
     * @param value Value/Entered Details
     */
    public static void testInfoLog(String key, String value ) {
        logger.info(key + " : " + value);
        log("<strong>[INFO] " + key + " : </strong><font color=#9400D3>" + value + "</font>");
    }

    /**
     * To log the test steps in the report.
     *
     * @param log Step Information
     */
    public static void testStepsLog(String log) {
        logger.info(log);
        log("[STEP] " + log);
    }

    /**
     * To log the test verification steps
     *
     * @param log Verification Message
     */
    public static void testVerifyLog(String log) {
        logger.info(MarkupHelper.createLabel(log, ExtentColor.ORANGE));
        log("[ASSERT] <font color=#000080>" + log + "</font>");
    }

    /**
     * To log the Validation Message comes during the test
     *
     * @param log Validation Message
     */
    public static void testValidationLog(String log) {
        logger.info(MarkupHelper.createLabel(log, ExtentColor.PINK));
        log("Validation Message : <Strong><font color=#ff0000>" + log + "</strong></font>");
    }

    /**
     * To log the Confirmation Message comes during the test
     *
     * @param log Confirmation Message
     */
    public static void testConfirmationLog(String log) {
        logger.info(MarkupHelper.createLabel(log, ExtentColor.TEAL));
        log("Confirmation Message : <Strong><font color=#008000>" + log + "</strong></font>");
    }

    /**
     * To log the Warning Message comes during the test
     *
     * @param log Warning Message
     */
    public static void testWarningLog(String log) {
        logger.warning(MarkupHelper.createLabel(log, ExtentColor.AMBER));
        log("Warning Message : <Strong><font color=#FF1870>" + log + "</strong></font>");
    }

    /**
     * To log the test verification step is passed successfully
     */
    public static void success() {
        logger.pass(MarkupHelper.createLabel("TEST STEP PASSED SUCCESSFULLY", ExtentColor.GREEN));
        System.out.println("<Strong><font color=#008000>Pass</font></strong>".replaceAll("<[^>]*>", ""));
        Reporter.log("<br></br><Strong><font color=#008000>Pass</font></strong>");
    }

    /**
     * To log test verification is failed
     */
    public static void failure() {
        logger.fail(MarkupHelper.createLabel("FAILURE DETECTED IN THE TEST STEP", ExtentColor.RED));
        System.out.println("<Strong><font color=#ff0000>Fail</font></strong>".replaceAll("<[^>]*>", ""));
        Reporter.log("<br></br><Strong><font color=#ff0000>Fail</font></strong>");
    }

    public static void testAPILog(String log) {
        logger.info("API CALL - " + log);
        log("<Strong><font color=#ff0000>" + log + "</strong></font>");
    }

    public static void testAPIFailLog(String log) {
        logger.info(MarkupHelper.createLabel(log, ExtentColor.ORANGE));
        log("<Strong><font color=#ff0000>" + log + "</strong></font>");
    }

}
