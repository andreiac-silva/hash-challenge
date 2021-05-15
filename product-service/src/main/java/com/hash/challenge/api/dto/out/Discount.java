package com.hash.challenge.api.dto.out;

public class Discount {

    private final float percentage;
    private final Integer valueInCents;

    public Discount(float percentage, Integer valueInCents) {
        this.percentage = percentage;
        this.valueInCents = valueInCents;
    }

    public float getPercentage() {
        return percentage;
    }

    public Integer getValueInCents() {
        return valueInCents;
    }
}
