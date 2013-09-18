package com.core;

import com.core.WebDriverSingleton;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.IOException;

public class MainHooks {

    @Before
    public void prepare() {
    }

    @After
    public void takeScreenShot(Scenario result) throws IOException {
        result.embed(getScreenShotBytes(), "data:image/png;base64");
    }

    private byte[] getScreenShotBytes() {
        if(WebDriverSingleton.getInstance() instanceof TakesScreenshot) {
            return ((TakesScreenshot) WebDriverSingleton.getInstance()).getScreenshotAs(OutputType.BYTES);
        }
        return new byte[]{};
    }
}
