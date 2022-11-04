package web_api.pages;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selenide.*;

public class SauceDemoPage {
    ElementsCollection topMenuElements = $$("#top-menu .dropdown-item"),
                    catalogElements = $$("#products .product-miniature");


    public SauceDemoPage openMinContent() {
        open("/img/demo-magazin-logo-1590349645.jpg");
        return this;
    }

    public SauceDemoPage navigationMenu(String value) {
        topMenuElements.find(text(value)).click();
        return this;
    }
    public SauceDemoPage catalogSize(Integer value) {
        catalogElements.shouldHave(size(value));
        return this;
    }

    public SauceDemoPage openDetailCard(String value) {
        catalogElements.find(text(value)).click();;
        return this;
    }

    public void addToCardProduct() {
        $(".add-to-cart").click();
        $(".modal-dialog .close").click();
        $("#header .cart-products-count").shouldHave(text("1"));
    }

    public void checkAuthorizationUser() {
        open("/index.php?controller=my-account");
        $(".link-item").shouldHave(text("Информация")).click();
        $("[name=firstname]").shouldHave(value("Иван"));
    }
}
