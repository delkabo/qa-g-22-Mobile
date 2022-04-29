package delkabo.com.tests.emulator;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.appium.java_client.*;

import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.qameta.allure.Allure.step;

public class AndroidSelenideTestsHW extends TestBase {

    @Test
    @Description("Check add article in Saved")
    void addInSavedTest() {

        step("Skip onboarding", Selenide::back);

        step("Type search", () -> {
            $(AppiumBy.accessibilityId("Search Wikipedia")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text")).setValue("Wikipedia");

        });
        step("Enter in article", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title")).click();
        });

        step("Add to Save", () -> {
            $(AppiumBy.xpath("//android.widget.TextView[@text='Save']")).click();
        });

        step("Return on main page", () -> {
            Selenide.back();
            Selenide.back();
            Selenide.back();
        });

        step("Click on saved", () -> {
            $(AppiumBy.xpath("//android.widget.FrameLayout[@content-desc='Saved']")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/item_title")).click();
        });

        step("Check Saved", () ->
                $$(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title")).shouldHave(sizeGreaterThan(0)));
    }


    @Test
    @Description("Delete article from Saved")
    void deleteFromSavedTest() {

        step("Previous Test: Check add article in Saved", () ->
                addInSavedTest());

        step("Skip onboarding", Selenide::back);

        step("Click on saved", () -> {
            $(AppiumBy.xpath("//android.widget.FrameLayout[@content-desc='Saved']")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/item_title")).click();
        });

        step("Click on saved", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title")).click();
        });

        step("Delete from Saved", () -> {
            $(AppiumBy.xpath("//android.widget.TextView[@text='Save']")).click();
            $(AppiumBy.xpath("//android.widget.TextView[@text='Remove from Saved']")).click();
        });

        step("Return to main page", () -> {
            Selenide.back();
            Selenide.back();
        });

        step("Click on saved", () -> {
            $(AppiumBy.xpath("//android.widget.FrameLayout[@content-desc='Saved']")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/item_title")).shouldNot(Condition.exist);
        });

    }


    @Test
    @Description("login")
    void loginTest() {
        step("Skip onboarding", Selenide::back);

        step("Click on More", () -> {
            $(AppiumBy.xpath("//android.widget.FrameLayout[@content-desc='More']")).click();
            $(AppiumBy.xpath("//android.widget.TextView[@text='LOG IN / JOIN WIKIPEDIA']")).click();
        });

        step("login and password", () -> {
//            android.widget.EditText
            $(AppiumBy.id("org.wikipedia.alpha:id/create_account_login_button")).click();
            $(AppiumBy.xpath("//android.widget.EditText[@text='Username']")).click();
            $(AppiumBy.xpath("//android.widget.EditText[@text='Username']")).setValue("Kandebober"); //todo спрятать
            $(AppiumBy.xpath("//android.widget.EditText[@text='Password']")).click();
            $(AppiumBy.xpath("//android.widget.EditText[@text='Password']")).setValue("Kam1naWiki");
            $(AppiumBy.id("org.wikipedia.alpha:id/login_button")).click();

            $(AppiumBy.xpath("//android.widget.Button[@text='NO THANKS']")).click();
        });

        step("Check login", () -> {
            $(AppiumBy.xpath("//android.widget.FrameLayout[@content-desc='More']")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/main_drawer_account_name")).shouldHave(Condition.text("Kandebober"));
        });


    }


    @Test
    @Description("logout")
    void logoutTest() {

        step("Skip onboarding", Selenide::back);

        step("Click button 'NO THANKS' in popup", () -> {
            if ($(AppiumBy.xpath("//android.widget.Button[@text='NO THANKS']")).exists()) {
                $(AppiumBy.xpath("//android.widget.Button[@text='NO THANKS']")).click();
            }
        });

        step("Enter in settings", () -> {
            $(AppiumBy.xpath("//android.widget.FrameLayout[@content-desc='More']")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/main_drawer_settings_container")).click();
        });

        step("Logout from profile", () -> {
            WebDriver driver = WebDriverRunner.getWebDriver();
            WebElement elementApp = (WebElement) driver.findElement(AppiumBy.androidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true))" +
                            ".scrollIntoView(new UiSelector().resourceIdMatches(\"org.wikipedia.alpha:id/logoutButton\"))"));
            elementApp.click();

            $(AppiumBy.id("android:id/button1")).click();
        });

        step("check logout", () -> {
            $(AppiumBy.xpath("//android.widget.FrameLayout[@content-desc='More']")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/main_drawer_login_button")).shouldHave(Condition.exist);
        });

    }
}


