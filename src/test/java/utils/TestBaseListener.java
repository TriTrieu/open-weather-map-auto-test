package utils;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import test_cases.TestBase;
import utils.extent_report.ExtentManager;
import utils.extent_report.ExtentTestManager;


import java.io.IOException;

import java.util.Objects;

public class TestBaseListener extends TestBase implements ITestListener {


    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        System.out.println("*** Test Suite " + iTestContext.getName() + " started ***");
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println(("*** Test Suite " + iTestContext.getName() + " ending ***"));
        ExtentTestManager.endTest();
        ExtentManager.getInstance().flush();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println(("*** Running test method " + iTestResult.getMethod().getMethodName() + "..."));
        ExtentTestManager.startTest(iTestResult.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("*** Executed " + iTestResult.getMethod().getMethodName() + " test successfully...");
        ExtentTestManager.getTest().log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        try {
            System.out.println("*** Test execution " + iTestResult.getMethod().getMethodName() + " failed...");
            System.out.println(iTestResult.getMethod().getMethodName() + " failed!");

            //Get driver from BaseTest and assign to local webdriver variable.
            Object testClass = iTestResult.getInstance();
            WebDriver driver = ((TestBase) testClass).getDriver();
            //Take base64Screenshot screenshot for extent reports
            String base64Screenshot =
                    "data:image/png;base64," + ((TakesScreenshot) Objects.requireNonNull(driver)).getScreenshotAs(OutputType.BASE64);

            ExtentTestManager.getTest().log(Status.FAIL, "Test Failed", MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("*** Test " + iTestResult.getMethod().getMethodName() + " skipped...");
        ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("*** Test failed but within percentage % " + iTestResult.getMethod().getMethodName());
    }
}
