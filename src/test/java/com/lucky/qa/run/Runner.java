package com.lucky.qa.run;

import com.lucky.qa.connectors.Hook;
import cucumber.api.junit.Cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"com.lucky.qa.steps", "com.lucky.qa.connectors"},
        plugin = {"pretty", "html:target/site/cucumber-pretty"},
        tags = "@filterOnlineCashback",
        monochrome = true,
        publish = true
)

public class Runner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {

        return super.scenarios();
    }
}