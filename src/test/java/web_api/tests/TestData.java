package web_api.tests;

import com.github.javafaker.Faker;
import org.aeonbits.owner.ConfigFactory;
import web_api.config.CredentialsConfig;

import java.text.SimpleDateFormat;

public class TestData {

    String menuClothes = "Одежда";
    String menuRelatedProd = "Сопутствующие товары";
    String menuPaintings = "Картины";
    String nameTitleCard = "Mountain fox notebook";
    String email = "fanis.gilmanov.94@mail.ru";
    String password = "123456gG!";
    String back = "my-account";
    String submitLogin = "1";
    String authCookieName = "PrestaShop-5eb24e794c8e2bb6adc3500d9af027ad";
    String authCookieValue;

    Integer countElements = 11;
    final int RELATEDELEMENTS = 11;

    Faker faker = new Faker();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String firstName = faker.funnyName().name(),
            lastName = faker.name().lastName(),
            createEmail = faker.internet().safeEmailAddress(),
            passwordCreate = faker.internet().password();

    String birthDay = sdf.format(faker.date().birthday());

    String genderId = "1";
    String psgdpr = "1";
    String submitCreate = "1";
}
