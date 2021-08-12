package utils;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.extent_report.ExtentTestManager;

public class WebDriverActions {
    private WebDriver driver;
    private final int defaultWaitingTime = 30;

    public WebDriverActions(WebDriver driver) {
        this.driver=driver;
    }

    protected void inputTextBySendKeys(WebElement element, String strInput, String eleName) {
        try {
            WebDriverWait defaultWait = new WebDriverWait(driver, defaultWaitingTime);
            defaultWait.until(ExpectedConditions.visibilityOf(element));
            defaultWait.until(ExpectedConditions.elementToBeClickable(element));
            element.clear();
            element.sendKeys(strInput);
            ExtentTestManager.getTest().info(String.format("Enter value \"%s\" into the [%s] text field", strInput, eleName));
            System.out.println(String.format("Enter value \"%s\" into the [%s] text field", strInput, eleName));
        } catch (Exception ex) {
            ExtentTestManager.getTest().log(Status.FAIL, String.format("Failed to enter value \"%s\" into the [%s] text field. ERROR: %s", strInput, eleName, ex.getMessage()));
        }
    }

    protected void click(WebElement element, String eleName) {
        try {
            if(element != null) {
                WebDriverWait defaultWait = new WebDriverWait(driver, defaultWaitingTime);
                defaultWait.until(ExpectedConditions.visibilityOf(element));
                defaultWait.until(ExpectedConditions.elementToBeClickable(element));
                highlightElement(element);
                element.click();
                ExtentTestManager.getTest().info(String.format("Clicked on [%s] element successfully", eleName));
                System.out.println(String.format("Clicked on [%s] element successfully", eleName));
            }
        } catch (Exception ex) {
            ExtentTestManager.getTest().log(Status.FAIL, String.format("Failed to clicked on [%s] element successfully. ERROR: %s", eleName, ex.getMessage()));
        }
    }

    protected void isElementExisting(WebElement element, String eleName) {
        try {
            WebDriverWait driverWait = new WebDriverWait(driver, 120);
            driverWait.until(ExpectedConditions.visibilityOf(element));
            highlightElement(element);
            ExtentTestManager.getTest().pass(String.format("Verify that [%s] element is displaying as expected", eleName));
        } catch (Exception e) {
            ExtentTestManager.getTest().fail(String.format("The [%s] element is NOT displaying which is NOT as expected", eleName));
            Assert.fail();
        }
    }

    protected void isTextDisplayed(WebElement element, String expectedText, String elementName) {
        try {
            WebDriverWait driverWait = new WebDriverWait(driver, defaultWaitingTime);
            driverWait.until(ExpectedConditions.visibilityOf(element));
            highlightElement(element);
            String actualText = element.getText();
            if(expectedText.trim().equalsIgnoreCase(actualText.trim())){
                ExtentTestManager.getTest().pass(String.format("Verify that value of [%s] element is equal to \"%s\" as expected", elementName, expectedText));
            }
            else {
                ExtentTestManager.getTest().fail(String.format("The value of [%s] element is NOT equal to \"%s\" which is NOT as expected. Actual: %s", elementName, expectedText, actualText));
                Assert.fail();
            }
        } catch (Exception e) {
            ExtentTestManager.getTest().fail(String.format("The value of [%s] element is NOT equal to \"%s\" which is NOT as expected. ERROR: %s", elementName, expectedText, e.getMessage()));
            Assert.fail();
        }
    }

    protected void isTextContain(WebElement element, String containedText, String elementName) {
        try {
            WebDriverWait driverWait = new WebDriverWait(driver, defaultWaitingTime);
            driverWait.until(ExpectedConditions.visibilityOf(element));
            highlightElement(element);
            String actualText = element.getText();
            if(actualText.toUpperCase().contains(containedText.toUpperCase())){
                ExtentTestManager.getTest().pass(String.format("Verify that value of [%s] element contains the sub-string \"%s\" as expected", elementName, containedText));
            }
            else {
                ExtentTestManager.getTest().fail(String.format("The value of [%s] element does NOT contains the sub-string \"%s\" which is NOT as expected. Actual: %s", elementName, containedText, actualText));
                Assert.fail();
            }
        } catch (Exception e) {
            ExtentTestManager.getTest().fail(String.format("The value of [%s] element does NOT contains the sub-string \"%s\" which is NOT as expected. ERROR: %s", elementName, containedText, e.getMessage()));
            Assert.fail();
        }
    }

    protected void isCssValueDisplayed(WebElement element, String cssValue, String expectedText, String elementName) {
        try {
            WebDriverWait driverWait = new WebDriverWait(driver, defaultWaitingTime);
            driverWait.until(ExpectedConditions.visibilityOf(element));
            highlightElement(element);
            String actualText = element.getCssValue(cssValue);
            Assert.assertEquals(expectedText.trim().toUpperCase(), actualText.trim().toUpperCase());
        } catch (Exception e) {
            Assert.fail(String.format("Unable to identify the %s . ERROR: %s", elementName, e.getMessage()));
        }
    }

    protected String getText(WebElement element, String elementName) {
        try {
            WebDriverWait driverWait = new WebDriverWait(driver, defaultWaitingTime);
            driverWait.until(ExpectedConditions.visibilityOf(element));
            return element.getText();
        } catch (NoSuchElementException e) {
            Assert.fail(String.format("The element %s is not existing", elementName));
            return "";
        }
    }

    protected void selectDropdownValueWithVisibleText(WebElement element, String dropdownValue) {
        try {
            highlightElement(element);
            Select dropdown = new Select(element);
            dropdown.selectByVisibleText(dropdownValue);
            //  logger.info("Successfully select dropdown item using text : %s" + dropdownValue);

        } catch (Exception e) {
            Assert.fail(String.format("Failed to select dropdown item using text : %s", dropdownValue));
        }
    }

    protected void selectDropdownByIndex(WebElement element, int index) {
        try {
            Select dropdown = new Select(element);
            dropdown.selectByIndex(index);
            //   logger.info(String.format("Successfully select dropdown item using index : %s" + index));

        } catch (Exception e) {
            Assert.fail(String.format("Failed to select dropdown item using index : %s", index));
        }
    }

    protected WebElement findWebElement(By by) {
        WebElement webElement = null;
        try {
            webElement = driver.findElement(by);
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.FAIL, String.format("Could not find out the element. ERROR: %s", e.getMessage()));
            return null;
        }

        return webElement;
    }

    //Upload File using sendKeys method
    protected void uploadFile(WebElement element, String filePath, String eleName) {
        try {
            element.sendKeys(filePath);
        } catch
        (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
    protected void highlightElement(WebElement element) {
        for (int i = 0; i < 1; i++) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: red; border: 2px solid yellow;");
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
        }
    }

    protected void highlightElement(By by) {
        WebElement element = driver.findElement(by);
        for (int i = 0; i < 2; i++) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: red; border: 2px solid yellow;");
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
        }
    }

    protected void scrollToElement(WebElement element) {
        try {
            WebDriverWait defaultWait = new WebDriverWait(driver, defaultWaitingTime);
            defaultWait.until(ExpectedConditions.visibilityOf(element));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        } catch (Exception ex) {
            Assert.fail(ex.getMessage());
        }
    }

    protected void scrollToTopOfPage() {
        try {
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, -document.body.scrollHeight)");
        } catch (Exception ex) {
            Assert.fail(ex.getMessage());
        }
    }

    protected void scrollToBottomOfPage() {
        try {
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
        } catch (Exception ex) {
            Assert.fail(ex.getMessage());
        }
    }

    protected boolean waitFrameDisplayAndSwitchTo(WebElement iframe) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 60);
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframe));
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    protected void deSwitchIframe() {
        driver.switchTo().defaultContent();
    }


}
