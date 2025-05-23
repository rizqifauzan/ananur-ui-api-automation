package ui.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import ui.utils.DriverFactory;

public class Hooks {
    @Before
    public void setUp() {
        DriverFactory.getDriver().manage().window().maximize();
    }

    @After
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}