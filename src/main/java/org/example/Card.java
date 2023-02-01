package org.example;

public class Card {

    // ключ name стал полем типа String
    private String name;

    // ключ link стал полем типа String
    private String link;

    // конструктор со всеми параметрами
    public Card(String name, String link) {
        this.name = name;
        this.link = link;
    }

    // конструктор без параметров
    public Card() {
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLink(String link) {
        this.link = link;
    }

}
