package test_cases;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.*;
import utils.PropUtil;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class TestBase {
    protected WebDriver driver;
    protected String browserType;
    protected String baseURL;
    private final int defaultWaitingTime = 60;
    protected final String testDataExcelPath = "src/test/java/test_data/TestData.xlsx";


    public WebDriver getDriver() {
        return driver;
    }

    @BeforeSuite
    public void beforeSuite(){

    }

    @Parameters("browser")
    @BeforeMethod
    public void beforeTestMethod(String browser){
        //Launch Browser
        this.browserType = browser;
        driver = launchBrowser(browser);
        //Open the page https://openweathermap.org/
        driver.get(getBaseURL());
    }

    @AfterMethod
    public void afterTestMethod(){
        //Close Browser
        if(driver != null) {
            driver.quit();
        }
    }

    @AfterSuite
    public void afterSuite(){

    }

    private WebDriver launchBrowser(String browser){
        WebDriver driver = null;
        switch (browser) {
            case BrowserType.CHROME:
//                WebDriverManager.chromedriver().setup();
                //Configure Proxy
                WebDriverManager.chromedriver().proxyUser(System.getenv("HTTPS_PROXY_USER"));
                WebDriverManager.chromedriver().proxyPass(System.getenv("HTTPS_PROXY_PASS"));
                WebDriverManager.chromedriver().proxy(System.getenv("HTTPS_PROXY")).setup();

                System.out.println("Driver is downloaded in path " + System.getProperty("webdriver.chrome.driver"));

                HashMap<String, Object> chromePref = new HashMap<>();
                chromePref.put("profile.default_content_settings.popups", 0);
                chromePref.put("download.prompt_for_download", false);
                chromePref.put("download.directory_upgrade", true);
                chromePref.put("plugins.always_open_pdf_externally", true);
                chromePref.put("profile.default_content_setting_values.notifications", 2);
                chromePref.put("profile.default_content_setting_values.geolocation", 2);

                ChromeOptions options = new ChromeOptions();
                options.setExperimentalOption("prefs", chromePref);
//                Prevents Chrome from displaying the notification 'Chrome is being controlled by automated software'
                options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
//                Opens Chrome in incognito mode
                options.addArguments("incognito");
//                Opens Chrome in maximize mode
                options.addArguments("start-maximized");
//                Opens Chrome in headless mode
//                options.addArguments("headless");

                driver = new ChromeDriver(options);
                driver.manage().deleteAllCookies();
                driver.manage().timeouts().implicitlyWait(defaultWaitingTime, TimeUnit.SECONDS);
                driver.manage().timeouts().pageLoadTimeout(defaultWaitingTime, TimeUnit.SECONDS);
                System.out.println("Opening the browser: " + browser);
                return driver;
            case BrowserType.FIREFOX:
//                WebDriverManager.firefoxdriver().setup();
//                Configure Proxy
                WebDriverManager.firefoxdriver().proxyUser(System.getenv("HTTPS_PROXY_USER"));
                WebDriverManager.firefoxdriver().proxyPass(System.getenv("HTTPS_PROXY_PASS"));
                WebDriverManager.firefoxdriver().proxy(System.getenv("HTTPS_PROXY")).setup();

                System.out.println("Driver is downloaded in path " + System.getProperty("webdriver.gecko.driver"));
                FirefoxProfile ffProfile = new FirefoxProfile();
                ffProfile.setPreference("browser.download.folderList", 2);
                ffProfile.setPreference("browser.privatebrowsing.autostart", true);
                ffProfile.setPreference("browser.download.manager.showWhenStarting", false);
                ffProfile.setPreference("geo.enabled", false);
                ffProfile.setPreference("geo.provider.use_corelocation", false);
                ffProfile.setPreference("geo.prompt.testing", false);
                ffProfile.setPreference("geo.prompt.testing.allow", false);
                //set dowload dir on this preference
                ffProfile.setPreference("media.navigator.permission.disabled", true);

                FirefoxOptions ffOptions = new FirefoxOptions();
                ffOptions.addArguments("-private");
                ffOptions.setProfile(ffProfile);
                ffOptions.setLogLevel(FirefoxDriverLogLevel.INFO);

                driver = new FirefoxDriver(ffOptions);
                driver.manage().deleteAllCookies();
                driver.manage().timeouts().implicitlyWait(defaultWaitingTime, TimeUnit.SECONDS);
                driver.manage().timeouts().pageLoadTimeout(defaultWaitingTime, TimeUnit.SECONDS);
                driver.manage().window().maximize();
                System.out.println("Opening the browser: " + browser);
                return driver;
            default:
                System.out.println("Can not find browser match with " + browser);
                return driver;
        }

    }


    protected String getBaseURL(){
        String sut = PropUtil.loadConfigProb().getProperty("sut");
        String baseURL = "";
        switch (sut){
            case "prod":
                baseURL = PropUtil.loadConfigProb().getProperty("base_url_prod");
                break;
            case "uat":
                baseURL = PropUtil.loadConfigProb().getProperty("base_url_uat");
                break;
        }
        this.baseURL = baseURL;
        return baseURL;
    }

 }
