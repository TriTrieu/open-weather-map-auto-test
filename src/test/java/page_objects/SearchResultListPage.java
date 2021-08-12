package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.WebDriverActions;

public class SearchResultListPage extends WebDriverActions {
    private WebDriver driver;

    public SearchResultListPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    //using findElement function
    private WebElement linkCityResult(String cityName){
        String xpath = String.format(".//*[@id='forecast_list_ul']//td//a[contains(text(),'%s')]", cityName);
        return findWebElement(By.xpath(xpath));
    }

    public void clickOnCityResultLink(String cityName){
        click(linkCityResult(cityName), cityName);
    }
//
}
