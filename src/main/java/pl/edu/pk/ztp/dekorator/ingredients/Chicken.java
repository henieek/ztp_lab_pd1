package pl.edu.pk.ztp.dekorator.ingredients;

import java.math.BigDecimal;

public class Chicken extends Ingredient {

    public Chicken() {
        super(BigDecimal.valueOf(2));
    }

    @Override
    public boolean hasMeat() {
        return true;
    }
}
