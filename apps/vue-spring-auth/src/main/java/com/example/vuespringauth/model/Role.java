package com.example.vuespringauth.model;

import java.util.Objects;

public enum Role {
    //    @JsonProperty("User")
    USER("User"),
    ADMIN("Admin");

    private final String role;

    Role(String role) {
        this.role = role;
    }

    public static Role getRole(String roleStr) {
        for (Role role : Role.values()) {
            if (Objects.equals(roleStr, role.getString())) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid Role: " + roleStr);
    }

    public String getString() {
        return role;
    }
}
