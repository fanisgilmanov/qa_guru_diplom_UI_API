package web_api.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;
import web_api.config.CredentialsConfig;
import web_api.pages.SauceDemoPage;

import static java.lang.String.format;
import static web_api.helpers.Attach.*;

public class TestBase {
    static TestData testData = new TestData();
    SauceDemoPage sauceDemoPage = new SauceDemoPage();
    static CredentialsConfig config = ConfigFactory.create(CredentialsConfig.class);
    @BeforeAll
    static void beforeUrl() {
        RestAssured.baseURI = "https://shop1.emagazin.info";
        Configuration.baseUrl = "https://shop1.emagazin.info";
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserSize = System.getProperty("browserSize","1920x1080");
        Configuration.browserVersion = System.getProperty("browserVersion", "100");
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.remote = format("https://%s:%s@%s",
                config.loginSelenoid(), config.passwordSelenoid(), config.remoteUrlSelenoid());
    }



    @AfterEach
    public void afterEach() {
        screenshotAs("Last screenshot");
        pageSource();
        browserConsoleLogs();
        addVideo();
    }
}
