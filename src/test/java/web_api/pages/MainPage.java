package web_api.pages;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {
    ElementsCollection topMenuElements = $$("#top-menu .dropdown-item"),
            catalogElements = $$("#products .product-miniature");

    public MainPage openMinContent() {
        open("/img/demo-magazin-logo-1590349645.jpg");
        return this;
    }

    public MainPage navigationMenu(String value) {
        topMenuElements.find(text(value)).click();
        return this;
    }
}
