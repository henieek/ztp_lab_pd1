package pl.edu.pk.ztp.dekorator.pizzas;

import com.google.common.collect.ImmutableList;

import java.util.Collection;
import java.util.List;

import pl.edu.pk.ztp.dekorator.ingredients.Broccoli;
import pl.edu.pk.ztp.dekorator.ingredients.Ingredient;
import pl.edu.pk.ztp.dekorator.ingredients.Spinach;

public class Vegetariana implements Pizza {

    private List<Ingredient> ingredients = ImmutableList.of(new Broccoli(), new Spinach());

    @Override
    public boolean hasMeat() {
        return false;
    }

    @Override
    public Collection<Ingredient> getIngredients() {
        return ingredients;
    }
}
