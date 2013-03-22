package pl.edu.pk.ztp.dekorator.ingredients;

import java.math.BigDecimal;

public class Broccoli extends Ingredient {

    public Broccoli() {
        super(BigDecimal.valueOf(1));
    }

    @Override
    public boolean hasMeat() {
        return false;
    }

}
