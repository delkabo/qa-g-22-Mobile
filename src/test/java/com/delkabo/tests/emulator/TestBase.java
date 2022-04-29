package com.delkabo.tests.emulator;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.delkabo.config.ProjectConfig;
import com.delkabo.drivers.BrowserstackMobileDriver;
import com.delkabo.drivers.EmulatorMobileDriver;
import com.delkabo.helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static io.qameta.allure.Allure.step;

public class TestBase {


    static ProjectConfig config = ConfigFactory.create(ProjectConfig.class, System.getProperties());


    @BeforeAll
    public static void setup() {
        addListener("AllureSelenide", new AllureSelenide());




        if (config.deviceHost().equals("browserstack")) {
            Configuration.browser = BrowserstackMobileDriver.class.getName();
        } else if(config.deviceHost().equals("emulation")) {
            Configuration.browser = EmulatorMobileDriver.class.getName();
        } else if(config.deviceHost().equals("real")) {
            Configuration.browser = EmulatorMobileDriver.class.getName();
        } else {
            Configuration.browser = EmulatorMobileDriver.class.getName();
        }

        Configuration.browserSize = null;
    }

    @BeforeEach
    public void startDriver() {
        open();
    }

    @AfterEach
    public void afterEach() {
        if (config.deviceHost().equals("browserstack")) {
            String sessionId = Attach.sessionId();
            Attach.video(sessionId);
        }

        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();

        step("Close driver", Selenide::closeWebDriver);

    }
}