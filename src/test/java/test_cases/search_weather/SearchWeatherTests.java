package test_cases.search_weather;

import org.testng.annotations.*;
import page_objects.DetailWeatherInCityPage;
import page_objects.NavigationBarPage;
import page_objects.SearchResultListPage;
import test_cases.TestBase;
import utils.ExcelUtils;
import utils.JavaUtils;
import utils.extent_report.ExtentTestManager;

import java.lang.reflect.Method;

public class SearchWeatherTests extends TestBase {


    @Test(dataProvider= "SearchProvider")
    public void TC001_TestPassed(String keyword, String cityName_vi, String cityName_en){
        ExtentTestManager.getTest().info("Launch the browser " + browserType.toUpperCase());

        ExtentTestManager.getTest().info("Navigate to the url " + baseURL);

//      Step 1. Searching for a city of your choice (E.g. Ha Noi)
        NavigationBarPage navigationBarPage = new NavigationBarPage(driver);
        navigationBarPage.search(keyword);
//        Step 2. Click on the link in result list
        SearchResultListPage searchResultListPage = new SearchResultListPage(driver);
        searchResultListPage.clickOnCityResultLink(cityName_vi);
/*
        Step 3. Verify the current date (E.g. Jun 9), city name and the weather display correct.
        (Note: Only validate the temperature display regardless its number)
 */
        DetailWeatherInCityPage detailWeatherInCityPage = new DetailWeatherInCityPage(driver);
        //Verify current Date e.g. Jun 9
        String currentDate = JavaUtils.getCurrentDate("MMM dd");
        detailWeatherInCityPage.verifyCurrentDate(currentDate);
        //Verify City Name
        detailWeatherInCityPage.verifyCityName(cityName_en);
        //Verify Weather
        detailWeatherInCityPage.verifyTemperature();
    }

    @Test(dataProvider= "SearchProvider")
    public void TC001_TestFailed(String keyword, String cityName_vi, String cityName_en){
        ExtentTestManager.getTest().info("Launch the browser " + browserType.toUpperCase());

        ExtentTestManager.getTest().info("Navigate to the url " + baseURL);

//      Step 1. Searching for a city of your choice (E.g. Ha Noi)
        NavigationBarPage navigationBarPage = new NavigationBarPage(driver);
        navigationBarPage.search(keyword);
//        Step 2. Click on the link in result list
        SearchResultListPage searchResultListPage = new SearchResultListPage(driver);
        searchResultListPage.clickOnCityResultLink(cityName_vi);
/*
        Step 3. Verify the current date (E.g. Jun 9), city name and the weather display correct.
        (Note: Only validate the temperature display regardless its number)
 */
        DetailWeatherInCityPage detailWeatherInCityPage = new DetailWeatherInCityPage(driver);
        //Verify current Date e.g. Jun 9
        String currentDate = JavaUtils.getCurrentDate("MMM dd");
        detailWeatherInCityPage.verifyCurrentDate(currentDate);
        //Verify City Name
        detailWeatherInCityPage.verifyCityName(cityName_en);
        //Verify Weather
        detailWeatherInCityPage.verifyTemperature();
    }


    @DataProvider(name="SearchProvider")
    public Object[][] getDataFromDataProvider(Method m) {
        if(m.getName().equalsIgnoreCase("TC001_TestPassed")){
            Object[][] testObjArray = ExcelUtils.getTableArray(testDataExcelPath,"TestPassed");
            return testObjArray;
        }
        else if(m.getName().equalsIgnoreCase("TC001_TestFailed")){
            Object[][] testObjArray = ExcelUtils.getTableArray(testDataExcelPath,"TestFailed");
            return testObjArray;
        }
        else{
            return new Object[][] {
                    { "" },
                    { "" },
            };
        }
    }
}
