package com.hash.challenge.api.dto.out;

import io.quarkus.runtime.annotations.RegisterForReflection;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.graalvm.collections.Pair;

@RegisterForReflection
public class Discount {

    private final float percentage;
    private final long valueInCents;

    public Discount(float percentage, long valueInCents) {
        this.percentage = percentage;
        this.valueInCents = valueInCents;
    }

    public static Discount fromValues(Pair<Float, Long> values) {
        var percentage = values.getLeft() == null ? 0 : values.getLeft();
        var valueInCents = values.getRight() == null ? 0 : values.getRight();
        return new Discount(percentage, valueInCents);
    }

    public float getPercentage() {
        return percentage;
    }

    public long getValueInCents() {
        return valueInCents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Discount discount = (Discount) o;

        return new EqualsBuilder().append(percentage, discount.percentage).append(valueInCents, discount.valueInCents).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(percentage).append(valueInCents).toHashCode();
    }
}
