package pl.edu.pk.ztp.dekorator.receipts;

import java.math.BigDecimal;

import pl.edu.pk.ztp.dekorator.ingredients.Ingredient;
import pl.edu.pk.ztp.dekorator.pizzas.Pizza;

public class ReceiptMaker {

    public static final BigDecimal WORTH_OF_BASE_PIZZA = BigDecimal.valueOf(15);

    public Receipt receiptFor(Pizza... pizzas) {
        BigDecimal total = BigDecimal.ZERO;
        for (Pizza pizza : pizzas) {
            total = total.add(priceOfSinglePizza(pizza));
        }
        return Receipt.forAmount(total);
    }

    private BigDecimal priceOfSinglePizza(Pizza pizza) {
        BigDecimal price = WORTH_OF_BASE_PIZZA;
        for (Ingredient ingredient : pizza.getIngredients()) {
            price = price.add(ingredient.getPrice());
        }
        return price;
    }
}
