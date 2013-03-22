package pl.edu.pk.ztp.dekorator.tests;

import com.google.common.collect.Iterables;

import org.junit.Test;

import pl.edu.pk.ztp.dekorator.StupidClientException;
import pl.edu.pk.ztp.dekorator.ingredients.Bacon;
import pl.edu.pk.ztp.dekorator.ingredients.Broccoli;
import pl.edu.pk.ztp.dekorator.ingredients.Chicken;
import pl.edu.pk.ztp.dekorator.ingredients.Spinach;
import pl.edu.pk.ztp.dekorator.pizzas.CustomPizza;
import pl.edu.pk.ztp.dekorator.pizzas.Haarmann;
import pl.edu.pk.ztp.dekorator.pizzas.Pizza;
import pl.edu.pk.ztp.dekorator.pizzas.Vegetariana;

import static org.fest.assertions.Assertions.assertThat;

public class PizzaTest {

    @Test
    public void clientGetsRegularVegetariana() throws Exception {
        // given
        Pizza pizza = new Vegetariana();
        // then
        assertThat(pizza.getIngredients()).containsOnly(new Broccoli(), new Spinach());
        assertThat(pizza.hasMeat()).isFalse();
    }

    @Test
    public void clientGetsMeatPizza() throws Exception {
        // given
        Pizza pizza = new Haarmann();
        // then
        assertThat(pizza.getIngredients()).containsOnly(new Bacon(), new Chicken());
        assertThat(pizza.hasMeat()).isTrue();
    }

    @Test
    public void clientShouldBeAbleToBuildCustomPizza() throws Exception {
        // when
        final Pizza vegetarianaWithBacon = CustomPizza.Builder.withBase(new Vegetariana())
                .with(new Bacon()).build();
        // then
        assertThat(vegetarianaWithBacon.getIngredients()).contains(new Bacon());
        assertThat(vegetarianaWithBacon.hasMeat()).isTrue();
    }

    @Test
    public void clientShouldBeAbleToBuildCustomPizzaWithoutMeat() throws Exception {
        // when
        final CustomPizza broccoliPizza = CustomPizza.Builder.withBase(new Haarmann())
                .without(new Bacon()).without(new Chicken()).with(new Broccoli()).build();
        // when
        assertThat(broccoliPizza.getIngredients()).containsOnly(new Broccoli());
        assertThat(broccoliPizza.hasMeat()).isFalse();
    }

    @Test(expected = StupidClientException.class)
    public void clientShouldNotBeAbleToOrderPizzaWithAndWithoutSameThing() throws Exception {
        CustomPizza.Builder.withBase(new Haarmann()).with(new Broccoli()).without(new Broccoli());
    }

    @Test
    public void shouldPermitDoubleBacon() throws Exception {
        // when
        final CustomPizza doubleBacon = CustomPizza.Builder.withBase(new Haarmann())
                .with(new Bacon()).build();
        // then
        assertHasTwoBacons(doubleBacon);
    }

    @Test
    public void shouldPermitDoubleBaconInVegetariana() throws Exception {
        // when
        final CustomPizza doubleBacon = CustomPizza.Builder.withBase(new Vegetariana())
                .with(new Bacon()).with(new Bacon()).build();
        // then
        assertHasTwoBacons(doubleBacon);
    }

    private void assertHasTwoBacons(Pizza pizza) {
        final int frequency = Iterables.frequency(pizza.getIngredients(), new Bacon());
        assertThat(frequency).isEqualTo(2);
    }
}
