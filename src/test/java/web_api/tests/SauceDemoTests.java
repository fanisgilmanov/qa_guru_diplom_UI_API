package web_api.tests;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import web_api.models.ReqresRequestModels;


import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static web_api.specs.Specifications.*;

public class SauceDemoTests extends TestBase {

    @Test
    @DisplayName("Проверка title страницы UI")
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
    @DisplayName("Проверка перехода по элементам  в навигационной панели UI")
    void checkMenuElementsTest() {

        step("Открываем главную страницу сайта", ()
                -> open(baseUrl));

        step("Проверяем, переход по элементу 'Одежда'", () -> {
            sauceDemoPage.navigationMenu(testData.menuClothes);
        });

        step("Проверяем, переход по элементу 'Сопутствующие товары'", () -> {
            sauceDemoPage.navigationMenu(testData.menuRelatedProd);
        });

        step("Проверяем, переход по элементу 'Картины'", () -> {
            sauceDemoPage.navigationMenu(testData.menuPaintings);
        });

    }

    @Test
    @DisplayName("Проверка количества товаров UI")
    void checkRelatedProductsElementsTest() {
        step("Открываем главную страницу сайта", ()
                -> open(baseUrl));

        step("Переход по элементу 'Сопутствующие товары'", () -> {
            sauceDemoPage.navigationMenu(testData.menuRelatedProd);
        });

        step("Проверка кол-во элементов в каталоге 'Сопутствующие товары'", () -> {
            sauceDemoPage.catalogSize(testData.countElements);
            assertThat(testData.countElements).isEqualTo(testData.RELATEDELEMENTS);
        });

    }

    @Test
    @DisplayName("Проверка открытия карточка товара UI")
    void checkCardProductTest() {
        step("Открываем главную страницу сайта", ()
                -> open(baseUrl));

        step("Переход по элементу 'Сопутствующие товары'", () -> {
            sauceDemoPage.navigationMenu(testData.menuRelatedProd);
        });

        step("Проверка открытия карточка товара 'Сопутствующие товары'", () -> {
            sauceDemoPage.openDetailCard(testData.nameTitleCard);
            String expectedTitle = testData.nameTitleCard;
            String actualTitle = title();
            assertThat(actualTitle).isEqualTo(expectedTitle);
        });

    }

    @Test
    @DisplayName("Проверка добавление товара в корзину UI")
    void checkAddBasketTest() {
        step("Открываем главную страницу сайта", ()
                -> open(baseUrl));

        step("Переход по элементу 'Сопутствующие товары'", () -> {
            sauceDemoPage.navigationMenu(testData.menuRelatedProd);
        });

        step("Открытия карточки товара 'Сопутствующие товары'", () -> {
            sauceDemoPage.openDetailCard(testData.nameTitleCard);
        });

        step("Добавление товара в корзину", () -> {
            sauceDemoPage.addToCardProduct();
        });

    }
    /*
    @Test
    @DisplayName("Проверка авторизации пользователя API + UI ")
    void authorizationUserTest() {
        step("Открытие минимального контента", () ->
                sauceDemoPage.openMinContent());

        step("Авторизация через API", () -> {
            ReqresRequestModels formParam = new ReqresRequestModels();
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
            sauceDemoPage.checkAuthorizationUser();
        });
    }

    @Test
    @DisplayName("Проверка удачной регистрации пользователя API")
    void successRegistrationUserTest() {
        step("Открытие минимального контента", () ->
                sauceDemoPage.openMinContent());


        step("Регистрация через API", () -> {
            ReqresRequestModels formParam = new ReqresRequestModels();
            formParam.setId_gender(testData.genderId);
            formParam.setFirstname(testData.firstName);
            formParam.setLastname(testData.lastName);
            formParam.setEmail(testData.createEmail);
            formParam.setPassword(testData.passwordCreate);
            formParam.setBirthday(testData.birthDay);
            formParam.setPsgdpr(testData.psgdpr);
            formParam.setSubmitCreate(testData.submitCreate);

            given()
                    .spec(requestSpec)
                    .formParam("id_gender", formParam.getId_gender())
                    .formParam("firstname", formParam.getFirstname())
                    .formParam("lastname", formParam.getLastname())
                    .formParam("email", formParam.getEmail())
                    .formParam("password", formParam.getPassword())
                    .formParam("birthday", formParam.getBirthday())
                    .formParam("psgdpr", formParam.getPsgdpr())
                    .formParam("submitCreate", formParam.getSubmitCreate())
                    .when()
                    .post("/index.php?controller=authentication&create_account=1")
                    .then()
                    .spec(responseSpec302);


        });

    }
    @Test
    @DisplayName("Проверка не удачной регистрации пользователя API")
    void failRegistrationUserTest() {
        step("Открытие минимального контента", () ->
                sauceDemoPage.openMinContent());

        step("Регистрация через API", () -> {
            ReqresRequestModels formParam = new ReqresRequestModels();
            formParam.setFirstname(testData.firstName);
            formParam.setLastname(testData.lastName);
            formParam.setPassword(testData.passwordCreate);
            given()
                    .spec(requestSpec)
                    .formParam("firstname", formParam.getFirstname())
                    .formParam("lastname", formParam.getLastname())
                    .formParam("password", formParam.getPassword())
                    .when()
                    .post("/index.php?controller=authentication&create_account=1")
                    .then()
                    .spec(responseSpecFail200);

        });

    }
*/
}
