package com.example.vuespringauth.model;

import java.util.Objects;

public enum Role {
    //    @JsonProperty("User")
    USER("User"),
    //    @JsonProperty("Admin")
    ADMIN("Admin");

    private final String role;

    Role(String role) {
        this.role = role;
    }

    public String getString() {
        return role;
    }

    public static Role getRole(String roleStr) {
        for (Role role : Role.values()) {
            if (Objects.equals(roleStr, role.getString())) {
                System.out.println(role);
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid Role: " + roleStr);
    }
}
