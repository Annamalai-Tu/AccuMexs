package framework.utils;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by TestUnity
 * Date: 2024-10-30
 * Project Name: AccuMExS
 */

public class ExtentInit implements Configuration {

    public static String REPORT_PATH;
    public static String FOLDER_PATH;
    protected static ExtentReports extent;
    protected static ExtentTest logger;

    public static void initializeReport(String suiteName) {

        File directory = new File(PROJECT_DIR + File.separator + "ExtentReports");
        if (!directory.exists()) directory.mkdir();

        String dateDir = PROJECT_DIR + File.separator + "ExtentReports" + File.separator +
                LocalDate.now().format(DateTimeFormatter.ofPattern(DateTimeFormat.DATE_FORMAT_DASH_DD_MMM_YYYY));
        File dateDirectory = new File(dateDir);
        if (!dateDirectory.exists()) dateDirectory.mkdir();

        String timeDir = PROJECT_DIR + File.separator + "ExtentReports" + File.separator +
                LocalDate.now().format(DateTimeFormatter.ofPattern(DateTimeFormat.DATE_FORMAT_DASH_DD_MMM_YYYY)) +
                File.separator + LocalTime.now().format(DateTimeFormatter.ofPattern(DateTimeFormat.TIME_FORMAT_US_HH_MM_SS));
        File timeDirectory = new File(timeDir);
        if (!timeDirectory.exists()) timeDirectory.mkdir();

        REPORT_PATH = timeDir;
        FOLDER_PATH = dateDir;

        ExtentSparkReporter htmlReporter;
        htmlReporter = new ExtentSparkReporter(timeDir + File.separator + "Report_" + suiteName + ".html");

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("OS Architecture", System.getProperty("os.arch"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        extent.setSystemInfo("User Name", System.getProperty("user.name"));

        extent.setAnalysisStrategy(AnalysisStrategy.TEST);

        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setDocumentTitle("TestUnity Automation Test Report");
        htmlReporter.config().setReportName("TestUnity Automation Test Report");

        String externalScript =
                "let logo = document.querySelector('.logo')" +
                        "logo.style.backgroundImage=`url('https://testunity.com/wp-content/themes/testunity/assets/img/logo.svg')`";

        htmlReporter.config().setJs(externalScript);

    }

    protected static void flushReport() {
        extent.flush();
    }

}

