package pl.edu.pk.ztp.dekorator.ingredients;

import java.math.BigDecimal;

public class Bacon extends Ingredient {

    public Bacon() {
        super(BigDecimal.valueOf(3));
    }

    @Override
    public boolean hasMeat() {
        return true;
    }
}
