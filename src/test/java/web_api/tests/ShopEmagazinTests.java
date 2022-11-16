package web_api.tests;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import web_api.models.RequestModels;


import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static web_api.specs.Specifications.*;

public class ShopEmagazinTests extends TestBase {

    @Test
    @DisplayName("Проверка title главной страницы 'shop1.emagazin.info'")
    void checkTitleTest() {
        step("Открываем главную страницу сайта", ()
                -> open(baseUrl));

        step("Проверяем, что title название страницы содержит текст 'Демо-магазин'", () -> {
            String expectedTitle = "Демо-магазин";
            String actualTitle = title();
            assertThat(actualTitle).isEqualTo(expectedTitle);
        });
    }


    @Test
    @DisplayName("Проверка перехода по элементам  в навигационной панели")
    void checkMenuElementsTest() {
        step("Открываем главную страницу сайта", ()
                -> open(baseUrl));

        step("Проверяем, переход по элементу 'Одежда'", () -> {
            mainPage.navigationMenu(testData.menuClothes);
        });

        step("Проверяем, переход по элементу 'Сопутствующие товары'", () -> {
            mainPage.navigationMenu(testData.menuRelatedProd);
        });

        step("Проверяем, переход по элементу 'Картины'", () -> {
            mainPage.navigationMenu(testData.menuPaintings);
        });
    }

    @Test
    @DisplayName("Проверка количества товаров")
    void checkRelatedProductsElementsTest() {
        step("Открываем главную страницу сайта", ()
                -> open(baseUrl));

        step("Переход по элементу 'Сопутствующие товары'", () -> {
            mainPage.navigationMenu(testData.menuRelatedProd);
        });

        step("Проверка кол-во элементов в каталоге 'Сопутствующие товары'", () -> {
            productPage.catalogSize(testData.countElements);
            assertThat(testData.countElements).isEqualTo(testData.RELATEDELEMENTS);
        });
    }

    @Test
    @DisplayName("Проверка открытия карточка товара")
    void checkCardProductTest() {
        step("Открываем главную страницу сайта", ()
                -> open(baseUrl));

        step("Переход по элементу 'Сопутствующие товары'", () -> {
            mainPage.navigationMenu(testData.menuRelatedProd);
        });

        step("Проверка открытия карточка товара 'Сопутствующие товары'", () -> {
            productPage.openDetailCard(testData.nameTitleCard);
            String expectedTitle = testData.nameTitleCard;
            String actualTitle = title();
            assertThat(actualTitle).isEqualTo(expectedTitle);
        });
    }

    @Test
    @DisplayName("Проверка добавление товара в корзину")
    void checkAddBasketTest() {
        step("Открываем главную страницу сайта", ()
                -> open(baseUrl));

        step("Переход по элементу 'Сопутствующие товары'", () -> {
            mainPage.navigationMenu(testData.menuRelatedProd);
        });

        step("Открытия карточки товара 'Сопутствующие товары'", () -> {
            productPage.openDetailCard(testData.nameTitleCard);
        });

        step("Добавление товара в корзину", () -> {
            productPage.addToCardProduct();
        });
    }

    @Test
    @DisplayName("Проверка авторизации пользователя")
    void authorizationUserTest() {
        step("Открытие минимального контента", () ->
                mainPage.openMinContent());

        step("Авторизация через API", () -> {
            RequestModels formParam = new RequestModels();
            formParam.setBack(testData.back);
            formParam.setEmail(testData.email);
            formParam.setPassword(testData.password);
            formParam.setSubmitLogin(testData.submitLogin);

            testData.authCookieValue = given()
                    .spec(requestSpec)
                    .formParam("back", formParam.getBack())
                    .formParam("email", formParam.getEmail())
                    .formParam("password", formParam.getPassword())
                    .formParam("submitLogin", formParam.getSubmitLogin())
                    .when()
                    .post("/index.php?controller=authentication&back=my-account")
                    .then()
                    .spec(responseSpec302)
                    .extract()
                    .cookie(testData.authCookieName);
        });

        step("set cookie в браузере", () -> {
            open(baseUrl);
            Selenide.clearBrowserCookies();
            Cookie authCookie = new Cookie(testData.authCookieName, testData.authCookieValue);
            WebDriverRunner.getWebDriver().manage().addCookie(authCookie);
        });

        step("Проверка авторизации через UI", () -> {
            authorizationPage.checkAuthorizationUser();
        });
    }

    @Test
    @DisplayName("Проверка удачной регистрации пользователя")
    void successRegistrationUserTest() {
        step("Открытие минимального контента", () ->
                mainPage.openMinContent());

        step("Регистрация через API", () -> {
            RequestModels formParam = new RequestModels();
            formParam.setIdGender(testData.genderId);
            formParam.setFirstName(testData.firstName);
            formParam.setLastName(testData.lastName);
            formParam.setEmail(testData.createEmail);
            formParam.setPassword(testData.passwordCreate);
            formParam.setBirthDay(testData.birthDay);
            formParam.setPsgdpr(testData.psgdpr);
            formParam.setSubmitCreate(testData.submitCreate);

            given()
                    .spec(requestSpec)
                    .formParam("id_gender", formParam.getIdGender())
                    .formParam("firstname", formParam.getFirstName())
                    .formParam("lastname", formParam.getLastName())
                    .formParam("email", formParam.getEmail())
                    .formParam("password", formParam.getPassword())
                    .formParam("birthday", formParam.getBirthDay())
                    .formParam("psgdpr", formParam.getPsgdpr())
                    .formParam("submitCreate", formParam.getSubmitCreate())
                    .when()
                    .post("/index.php?controller=authentication&create_account=1")
                    .then()
                    .spec(responseSpec302);
        });
    }

    @Test
    @DisplayName("Проверка не удачной регистрации пользователя ")
    void failRegistrationUserTest() {
        step("Открытие минимального контента", () ->
                mainPage.openMinContent());

        step("Регистрация через API", () -> {
            RequestModels formParam = new RequestModels();
            formParam.setFirstName(testData.firstName);
            formParam.setLastName(testData.lastName);
            formParam.setPassword(testData.passwordCreate);
            given()
                    .spec(requestSpec)
                    .formParam("firstname", formParam.getFirstName())
                    .formParam("lastname", formParam.getLastName())
                    .formParam("password", formParam.getPassword())
                    .when()
                    .post("/index.php?controller=authentication&create_account=1")
                    .then()
                    .spec(responseSpecFail200);
        });
    }
}
