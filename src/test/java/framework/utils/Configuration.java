package framework.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

/**
 * Created by TestUnity
 * Date: 2024-10-30
 * Project Name: AccuMExS
 */

public interface Configuration {

    Properties configProp = new Properties();
    String configurationPath = "Resources/configurations/config.properties";

    String SEPARATOR = "<br>";

    String PROJECT_DIR = getProjectDir();
    String DOWNLOAD_FOLDER = PROJECT_DIR + File.separator + "downloads";
    String CSV_FILES = PROJECT_DIR + File.separator + "Resources" + File.separator + "csv";

    String BROWSER = getProperty("browser");
    String URL = getProperty("url");

    String SELENIUM_HUB = getProperty("selenium.hub");
    String SELENIUM_PORT = getProperty("selenium.port");

    String USERNAME = System.getProperty("username") == null ? getProperty("username") : System.getProperty("username");
    String PASSWORD = System.getProperty("password") == null ? getProperty("password") : System.getProperty("password");

    String VIDEO_RECORD = getProperty("record.video").isEmpty() ? "false" : getProperty("record.video");

    int IMPLICIT_WAIT = Integer.parseInt(getProperty("implicit.wait"));
    int EXPLICIT_WAIT = Integer.parseInt(getProperty("explicit.wait"));
    int STATIC_WAIT = Integer.parseInt(getProperty("static.wait"));

    String IS_HEADLESS = getProperty("is.headless");

    static URL getRemoteGridURL() {

        URL REMOTE_GRID_URL = null;

        try {
            REMOTE_GRID_URL = new URL("http://" + SELENIUM_HUB + ":" + SELENIUM_PORT);
        } catch (MalformedURLException ex) {
            TestLogger.log("Error occurred in Remote Grid URL.");
        }

        return REMOTE_GRID_URL;
    }

    /**
     * To get the current project directory path
     *
     * @return Project Directory path
     */
    static String getProjectDir() {
        return System.getProperty("user.dir");
    }

    /**
     * To get the Test Configuration Property values
     *
     * @param key Key
     * @return Value of Key
     */
    static String getProperty(String key) {
        InputStream input = null;
        try {
            input = new FileInputStream(configurationPath);
            configProp.load(input);
        } catch (Exception e) {
            TestLogger.log("Error occurred while reading the file.");
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return configProp.getProperty(key);
    }

}

