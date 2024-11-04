package framework.utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * Created by TestUnity
 * Date: 2024-10-30
 * Project Name: AccuMExS
 */

public class RetryListener implements IRetryAnalyzer {

    int counter = 0;
    int retryLimit = 2;

    @Override
    public boolean retry(ITestResult result) {
        if (counter < retryLimit) {
            counter++;
            System.err.println("************************************************");
            System.err.println("Test Name : " + result.getName());
            System.err.println("Retry Count : " + counter);
            System.err.println("************************************************");
            return true;
        }
        return false;
    }
}
