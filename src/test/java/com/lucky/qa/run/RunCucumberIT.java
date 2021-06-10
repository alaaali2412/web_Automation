package com.lucky.qa.run;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"com.lucky.qa.steps", "com.lucky.qa.connectors"},
        plugin = {"pretty", "html:target/site/cucumber-pretty"},
        tags = "  @SmokeTest",
        monochrome = true,
        publish = true
)

public class RunCucumberIT extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}