package pl.edu.pk.ztp.dekorator.pizzas;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.List;

import pl.edu.pk.ztp.dekorator.ingredients.Ingredient;
import pl.edu.pk.ztp.dekorator.StupidClientException;

public class CustomPizza implements Pizza {

    public static class Builder {

        public static Builder withBase(Pizza basePizza) {
            return new Builder(basePizza);
        }

        private final Pizza basePizza;

        private final List<Ingredient> additions = Lists.newArrayList();

        private final List<Ingredient> removals = Lists.newArrayList();

        private Builder(Pizza basePizza) {
            this.basePizza = basePizza;
        }

        public Builder with(Ingredient ingredient) {
            throwExceptionIfConflict(removals, ingredient);
            additions.add(ingredient);
            return this;
        }

        public Builder without(Ingredient ingredient) {
            throwExceptionIfConflict(additions, ingredient);
            removals.add(ingredient);
            return this;
        }

        private void throwExceptionIfConflict(Collection<Ingredient> collection,
                Ingredient newish) {
            if (collection.contains(newish)) {
                throw new StupidClientException();
            }
        }

        public CustomPizza build() {
            return new CustomPizza(basePizza, additions, removals);
        }
    }

    private final ImmutableList<Ingredient> ingredients;

    private CustomPizza(Pizza basePizza, Collection<Ingredient> additions,
            Collection<Ingredient> removals) {
        final List<Ingredient> base = Lists.newArrayList(basePizza.getIngredients());
        base.addAll(additions);
        base.removeAll(removals);
        ingredients = ImmutableList.copyOf(base);
    }

    @Override
    public Collection<Ingredient> getIngredients() {
        return ingredients;
    }

    @Override
    public boolean hasMeat() {
        return Iterables.any(ingredients, new Predicate<Ingredient>() {
            @Override
            public boolean apply(Ingredient ingredient) {
                return ingredient.hasMeat();
            }
        });
    }

}
