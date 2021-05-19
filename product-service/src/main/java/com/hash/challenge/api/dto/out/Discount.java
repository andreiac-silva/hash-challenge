package com.hash.challenge.api.dto.out;

import io.quarkus.runtime.annotations.RegisterForReflection;
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
}
