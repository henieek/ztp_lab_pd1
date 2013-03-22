package pl.edu.pk.ztp.dekorator.receipts;

import java.math.BigDecimal;

public class Receipt {

    public static Receipt forAmount(BigDecimal amount) {
        return new Receipt(amount);
    }

    private final BigDecimal amount;

    private Receipt(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Receipt receipt = (Receipt) o;

        return amount.compareTo(receipt.getAmount()) == 0;
    }

    @Override
    public int hashCode() {
        return amount != null ? amount.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "amount=" + amount +
                '}';
    }
}
