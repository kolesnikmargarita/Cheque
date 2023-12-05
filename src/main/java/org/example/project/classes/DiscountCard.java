package org.example.project.classes;

import java.io.Serializable;

public record DiscountCard(long cardNumber, float discount) implements Serializable{}
