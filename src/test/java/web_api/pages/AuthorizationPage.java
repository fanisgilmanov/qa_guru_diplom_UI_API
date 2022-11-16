package web_api.pages;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AuthorizationPage {
    public void checkAuthorizationUser() {
        open("/index.php?controller=my-account");
        $(".link-item").shouldHave(text("Информация")).click();
        $("[name=firstname]").shouldHave(value("Иван"));
    }
}
