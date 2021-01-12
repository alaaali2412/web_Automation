package com.lucky.qa.run;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"com.lucky.qa.steps"},
        plugin = { "pretty","json:target/cucumber.json", "html:target/site/cucumber-pretty"},
        tags = "@facebookLogin",
        monochrome = true,
        publish = true,
        strict = true
)


public class Runner extends AbstractTestNGCucumberTests {

 @DataProvider(parallel = true)
  @Override  public Object[][] scenarios() {
        return super.scenarios();
    }








}