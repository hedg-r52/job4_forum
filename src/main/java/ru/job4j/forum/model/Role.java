package ru.job4j.forum.model;

public enum Role {
    ROLE_USER,
    ROLE_ADMIN;

    public static Role getRoleByString(String source) {
        return Role.valueOf(source);
    }
}
