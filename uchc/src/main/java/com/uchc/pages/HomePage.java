package com.uchc.pages;

public class HomePage extends BasePage {

    NavBar navbar;

    public HomePage() {
        navbar = new NavBar();
    }

    public NavBar navbar() {
        return navbar;
    }
}
