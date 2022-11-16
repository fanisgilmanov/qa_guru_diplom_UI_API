package web_api.pages;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProductPage {
    ElementsCollection catalogElements = $$("#products .product-miniature");

    public ProductPage catalogSize(Integer value) {
        catalogElements.shouldHave(size(value));
        return this;
    }

    public ProductPage openDetailCard(String value) {
        catalogElements.find(text(value)).click();
        return this;
    }

    public void addToCardProduct() {
        $(".add-to-cart").click();
        $(".modal-dialog .close").click();
        $("#header .cart-products-count").shouldHave(text("1"));
    }
}
