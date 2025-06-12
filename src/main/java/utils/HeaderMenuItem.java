package utils;

import lombok.Getter;

@Getter
public enum HeaderMenuItem {

    SEARCH("SEARCH", "//a[text()=' Search ']"),
    LET_CAR_WORK("LET_CAR_WORK", "//a[text()=' Let the car work ']"),
    TERMS("TERMS", "//a[text()=' Terms of use ']"),
    SIGN_UP("SIGN_UP", "//a[text()=' Sign up ']"),
    LOGIN("LOGIN", "//a[text()=' Log in ']"),
    LOGOUT("LOGOUT", "//a[normalize-space()='Logout']"),
    DELETE_ACCOUNT("DELETE_ACCOUNT", "//a[text()='Delete account']");

    private final String name;
    private final String locator;

    HeaderMenuItem(String key, String locator) {
        this.name = key;
        this.locator = locator;
    }

    @Override
    public String toString() {
        return name;
    }
}

