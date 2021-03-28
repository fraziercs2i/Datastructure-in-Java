package de.tukl.programmierpraktikum2020.mp1;

public class Element {
    private String key;
    private Integer value;
    public Element(String k, Integer v) {
        this.key = k;
        this.value = v;
    }
    public String getKey() {
        return this.key;
    }
    public Integer getValue() {
        return this.value;
    }
    public void setValue(Integer x) {
        this.value = x;
    }
}
