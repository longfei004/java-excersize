package com.excersize.javaExcersize;

public class AppleGreenPredicate implements ApplePredicate {
    public boolean test(Apple apple) {
        return "green".equals(apple.getColor());
    }
}
