package pl.edu.pk.ztp.dekorator.pizzas;

import com.google.common.collect.ImmutableList;

import java.util.Collection;
import java.util.List;

import pl.edu.pk.ztp.dekorator.ingredients.Bacon;
import pl.edu.pk.ztp.dekorator.ingredients.Chicken;
import pl.edu.pk.ztp.dekorator.ingredients.Ingredient;

public class MeatPizza implements Pizza {

    private final List<Ingredient> ingredients = ImmutableList.of(new Bacon(), new Chicken());

    @Override
    public Collection<Ingredient> getIngredients() {
        return ingredients;
    }

    @Override
    public boolean hasMeat() {
        return true;
    }
}
