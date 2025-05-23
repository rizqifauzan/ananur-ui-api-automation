package ui.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

@CucumberOptions(
        features = "src/test/resources/ui/features",
        glue = {"ui.stepdefinitions", "ui.hooks"},
        plugin = {
                "pretty",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "html:target/index.html"
        },
        monochrome = true
)
@Test(groups = {"ui"})
public class TestRunner extends AbstractTestNGCucumberTests {
}