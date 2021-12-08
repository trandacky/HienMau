package com.k41.constants;

public enum QuyenConstants {

    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private String role;

    QuyenConstants(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
