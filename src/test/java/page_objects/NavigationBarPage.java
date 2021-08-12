package page_objects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WebDriverActions;

public class NavigationBarPage extends WebDriverActions {
    private WebDriver driver;

    public NavigationBarPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    //using CSS selector
    @FindBy(css = "div#desktop-menu input[name='q']")
    private WebElement txtSearch;

    public void search(String keyword){
        inputTextBySendKeys(txtSearch,  keyword, "Search");
        txtSearch.sendKeys(Keys.ENTER);
    }
}
