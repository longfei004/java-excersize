package com.excersize.javaExcersize;

public class Apple {
    
    private String color;
    
    private Integer weight;
    
    public String getColor() {
        return color;
    }
    
    public void setColor(String color) {
        this.color = color;
    }
    
    public Integer getWeight() {
        return weight;
    }
    
    public void setWeight(int weight) {
        this.weight = weight;
    }
    
    public static boolean isGreen(Apple apple) {
        return "green".equals(apple.getColor());
    }
    
    public static boolean isHeavy(Apple apple) {
        return apple.getWeight() > 100;
    }
}
