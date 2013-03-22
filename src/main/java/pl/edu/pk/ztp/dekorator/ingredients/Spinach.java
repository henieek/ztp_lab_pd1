package pl.edu.pk.ztp.dekorator.ingredients;

import java.math.BigDecimal;

public class Spinach extends Ingredient {

    public Spinach() {
        super(BigDecimal.valueOf(0.5));
    }

    @Override
    public boolean hasMeat() {
        return false;
    }

}
