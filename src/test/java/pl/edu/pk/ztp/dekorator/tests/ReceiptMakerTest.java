package pl.edu.pk.ztp.dekorator.tests;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import pl.edu.pk.ztp.dekorator.ingredients.Bacon;
import pl.edu.pk.ztp.dekorator.ingredients.Broccoli;
import pl.edu.pk.ztp.dekorator.ingredients.Chicken;
import pl.edu.pk.ztp.dekorator.ingredients.Spinach;
import pl.edu.pk.ztp.dekorator.pizzas.CustomPizza;
import pl.edu.pk.ztp.dekorator.pizzas.Haarmann;
import pl.edu.pk.ztp.dekorator.pizzas.Vegetariana;
import pl.edu.pk.ztp.dekorator.receipts.Receipt;
import pl.edu.pk.ztp.dekorator.receipts.ReceiptMaker;

import static org.fest.assertions.Assertions.assertThat;

public class ReceiptMakerTest {

    private ReceiptMaker testSubject;

    @Before
    public void setUp() throws Exception {
        testSubject = new ReceiptMaker();
    }

    @Test
    public void forBasePizzaWeShouldOfferBasePrice() throws Exception {
        // given
        final CustomPizza plainPizza = CustomPizza.Builder.withBase(new Vegetariana())
                .without(new Broccoli()).without(new Spinach()).build();
        // when
        final Receipt receipt = testSubject.receiptFor(plainPizza);
        // then
        assertThat(receipt.getAmount()).isEqualByComparingTo(ReceiptMaker.WORTH_OF_BASE_PIZZA);
    }

    @Test
    public void shouldOfferRegularPriceForVegetariana() throws Exception {
        // given
        final Vegetariana vegetariana = new Vegetariana();
        // when
        final Receipt receipt = testSubject.receiptFor(vegetariana);
        // then
        final BigDecimal total = ReceiptMaker.WORTH_OF_BASE_PIZZA.add(new Broccoli().getPrice())
                .add(new Spinach().getPrice());
        assertThat(receipt.getAmount()).isEqualByComparingTo(total);
    }

    @Test
    public void customPizzaWithTheSameIngredientsAsVegetarianaShouldCostSame() throws Exception {
        // given
        final CustomPizza fakeVege = CustomPizza.Builder.withBase(new Haarmann())
                .without(new Bacon()).without(new Chicken()).with(new Broccoli())
                .with(new Spinach()).build();
        final Vegetariana realVege = new Vegetariana();
        // when
        final Receipt fakeVegeReceipt = testSubject.receiptFor(fakeVege);
        final Receipt realVegeReceipt = testSubject.receiptFor(realVege);
        // then
        assertThat(fakeVegeReceipt).isEqualTo(realVegeReceipt);
    }

    @Test
    public void shouldMakeAReceiptForTwoPizzas() throws Exception {
        // given
        final Vegetariana vegetariana = new Vegetariana();
        final Haarmann haarmann = new Haarmann();
        // when
        final Receipt receipt = testSubject.receiptFor(vegetariana, haarmann);
        final Receipt vegeOnly = testSubject.receiptFor(vegetariana);
        final Receipt meatOnly = testSubject.receiptFor(haarmann);
        // then
        assertThat(receipt.getAmount())
                .isEqualByComparingTo(vegeOnly.getAmount().add(meatOnly.getAmount()));
    }
}
