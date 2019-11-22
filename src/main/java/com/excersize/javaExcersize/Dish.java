package com.excersize.javaExcersize;

public class Dish {
    private String name;
    
    private boolean vegetarian;
    
    private int calories;
    
    private Type type;
    
    public Dish() {
    }
    
    public String getName() {
        return name;
    }
    
    public boolean isVegetarian() {
        return vegetarian;
    }
    
    public int getCalories() {
        return calories;
    }
    
    public Type getType() {
        return type;
    }
    
    @Override
    public String toString() {
        return name;
    }
    
    public enum Type { MEAT, FISH, OTHER }
}
