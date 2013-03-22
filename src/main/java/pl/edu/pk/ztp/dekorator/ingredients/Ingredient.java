package pl.edu.pk.ztp.dekorator.ingredients;

import java.math.BigDecimal;

import pl.edu.pk.ztp.dekorator.Food;

public abstract class Ingredient implements Food {

    private final BigDecimal price;

    protected Ingredient(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        return o != null && getClass().equals(o.getClass());
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public BigDecimal getPrice() {
        return price;
    }
}
