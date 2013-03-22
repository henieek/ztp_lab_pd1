package pl.edu.pk.ztp.dekorator.pizzas;

import java.util.Collection;

import pl.edu.pk.ztp.dekorator.Food;
import pl.edu.pk.ztp.dekorator.ingredients.Ingredient;

public interface Pizza extends Food {

    Collection<Ingredient> getIngredients();

}
