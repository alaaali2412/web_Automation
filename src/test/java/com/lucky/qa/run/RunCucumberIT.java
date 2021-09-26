package com.lucky.qa.run;


import com.lucky.qa.connectors.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"com.lucky.qa.steps", "com.lucky.qa.connectors"},
        plugin = {"pretty", "html:target/site/cucumber-pretty"},
        tags = "@EgyptSmokeTest",
        publish = true
)

public class RunCucumberIT extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}