package org.example;

public class Profile {

    // опиши поля
    private String name;

    private String about;

    // добавь конструкторы — со всеми параметрами и без параметров
    public Profile(String name, String about) {
        this.name = name;
        this.about = about;
    }
    public Profile() {
    }

    // добавь геттеры и сеттеры для всех полей
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}