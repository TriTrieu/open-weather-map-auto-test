package page_objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.WebDriverActions;
import utils.extent_report.ExtentTestManager;

public class DetailWeatherInCityPage extends WebDriverActions {
    private WebDriver driver;

    public DetailWeatherInCityPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    //using css Selector
    @FindBy(css = "div.current-container.mobile-padding span.orange-text")
    private WebElement lbCurrentDate;

    //using Xpath
    @FindBy(xpath = ".//div[@class='current-container mobile-padding']//h2")
    private WebElement lbCityName;

    //using css Selector
    @FindBy(css = "div.current-container.mobile-padding span.heading")
    private WebElement lbTemperature;


    public void verifyCurrentDate(String currentDate){
        isTextContain(lbCurrentDate, currentDate, "Current Date");
    }

    public void verifyCityName(String cityName){
        isTextDisplayed(lbCityName, cityName, "City Name");
    }

    public void verifyTemperature(){
        String temperature = lbTemperature.getText();
        isElementExisting(lbTemperature, temperature);
        //Verify Temperature should be an integer
        try {
            int temp = Integer.parseInt(temperature.replace("°C", ""));
        }
        catch (Exception e){
            ExtentTestManager.getTest().fail("Expected: Temperature should be converted to an integer successfully. ERROR: " + e.getMessage());
            Assert.fail();
        }
    }
}
