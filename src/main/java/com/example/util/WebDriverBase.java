package com.example.util;

import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public abstract class WebDriverBase {
	
//    private static WebDriver mDriver                      = null;
//    private static boolean mAutoQuitDriver                = true;
//
//    private static final String CONFIG_FILE        = "../config.ini";
//    private static final String DRIVER_CHROME      = "chrome";
//    private static final String DRIVER_PHANTOMJS   = "phantomjs";
//
//    protected static Properties sConfig;
//    protected static DesiredCapabilities sCaps;
//
//    private static boolean isUrl(String urlString) {
//        try {
//            new URL(urlString);
//            return true;
//        } catch (MalformedURLException mue) {
//            return false;
//        }
//    }
//    
//    static{
//    	try {
//			configure();
//		} catch (IOException e) {
//			System.out.println("configure is error!!!live!!");
//			e.printStackTrace();
//		}
//    	
//    	try {
//			prepareDriver();
//		} catch (Exception e) {
//			System.out.println("prepareDriver is error!!!live!!");
//			e.printStackTrace();
//		}
//    }
//    
//    public static void configure() throws IOException {
//        // Read config file
//        sConfig = new Properties();
////        sConfig.load(new FileReader(CONFIG_FILE));
//
//        // Prepare capabilities
//        sCaps = new DesiredCapabilities();
//        sCaps.setJavascriptEnabled(true);
//        sCaps.setCapability("takesScreenshot", false);
//
////        String driver = sConfig.getProperty("driver", DRIVER_PHANTOMJS);
//        String driver = DRIVER_PHANTOMJS;
//
//        // Fetch PhantomJS-specific configuration parameters
//        if (driver.equals(DRIVER_PHANTOMJS)) {
//            // "phantomjs_exec_path"
//            if (sConfig.getProperty("phantomjs_exec_path") == null) {
//                sCaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "D:/phantomjs-2.1.1-windows/phantomjs-2.1.1-windows/bin/phantomjs.exe");
//            } else {
//                throw new IOException(String.format("Property '%s' not set!", PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY));
//            }
//            // "phantomjs_driver_path"
//            if (sConfig.getProperty("phantomjs_driver_path") != null) {
//                System.out.println("Test will use an external GhostDriver");
//                sCaps.setCapability(PhantomJSDriverService.PHANTOMJS_GHOSTDRIVER_PATH_PROPERTY, sConfig.getProperty("phantomjs_driver_path"));
//            } else {
//                System.out.println("Test will use PhantomJS internal GhostDriver");
//            }
//        }
//
//        // Disable "web-security", enable all possible "ssl-protocols" and "ignore-ssl-errors" for PhantomJSDriver
////        sCaps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, new String[] {
////            "--web-security=false",
////            "--ssl-protocol=any",
////            "--ignore-ssl-errors=true"
////        });
//        ArrayList<String> cliArgsCap = new ArrayList<String>();
//        cliArgsCap.add("--web-security=false");
//        cliArgsCap.add("--ssl-protocol=any");
//        cliArgsCap.add("--ignore-ssl-errors=true");
//        sCaps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, cliArgsCap);
//
//        // Control LogLevel for GhostDriver, via CLI arguments
//        sCaps.setCapability(PhantomJSDriverService.PHANTOMJS_GHOSTDRIVER_CLI_ARGS, new String[] {
//            "--logLevel=" + (sConfig.getProperty("phantomjs_driver_loglevel") != null ? "DEBUG" : "DEBUG")
//        });
//    }
//
//    public static void prepareDriver() throws Exception {
//        // Which driver to use? (default "phantomjs")
////        String driver = sConfig.getProperty("driver", DRIVER_CHROME);
//    	String driver = DRIVER_PHANTOMJS;
//
//        // Start appropriate Driver
//        if (isUrl(driver)) {
//            sCaps.setBrowserName("phantomjs");
//            mDriver = new RemoteWebDriver(new URL(driver), sCaps);
//        } else if (driver.equals(DRIVER_CHROME)) {
//            mDriver = new ChromeDriver(DesiredCapabilities.chrome());
//        } else if (driver.equals(DRIVER_PHANTOMJS)) {
//            mDriver = new PhantomJSDriver(sCaps);
//        }
//    }
//
//    protected static WebDriver getDriver() {
//        return mDriver;
//    }
//
//    protected void disableAutoQuitDriver() {
//        mAutoQuitDriver = false;
//    }
//
//    protected void enableAutoQuitDriver() {
//        mAutoQuitDriver = true;
//    }
//
//    protected boolean isAutoQuitDriverEnabled() {
//        return mAutoQuitDriver;
//    }
//
//    public  void quitDriver() {
//        if (mAutoQuitDriver && mDriver != null) {
//            mDriver.quit();
//            mDriver = null;
//        }
//    }
}
