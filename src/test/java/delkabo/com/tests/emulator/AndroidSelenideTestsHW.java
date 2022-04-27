package delkabo.com.tests.emulator;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.conditions.Text;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.qameta.allure.Allure.step;

public class AndroidSelenideTestsHW extends TestBase {

//    @Test
//    void searchTest() {

//        step("Skip onboarding", Selenide::back);
//
//        step("Type search", () -> {
//            $(AppiumBy.accessibilityId("Search Wikipedia")).click();
//            $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text")).setValue("Appium");        //org.wikipedia.alpha
//        });
//
//        step("Verify content found", () ->
//                $$(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title")).shouldHave(sizeGreaterThan(0))); //	android.widget.FrameLayout
//
//    }

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
//        step("Skip onboarding", Selenide::back);
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

        step("Delete from Saved", () -> { //todo исправить
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
}


