package com.example.stupek.user;

public enum Role {
    USER("USER"),

    ADMIN("ADMIN"),

    DEVELOPER("DEVELOPER");

    private final String nameRole;

    Role(String nameRole) {
        this.nameRole = nameRole;
    }

    public String getNameRole() {
        return nameRole;
    }
}
