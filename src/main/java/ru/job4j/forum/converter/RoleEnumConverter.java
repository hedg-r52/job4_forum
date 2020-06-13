package ru.job4j.forum.converter;

import org.springframework.core.convert.converter.Converter;
import ru.job4j.forum.model.Role;

public class RoleEnumConverter implements Converter<String, Role> {
    @Override
    public Role convert(String source) {
        return Role.getRoleByString(source);
    }
}
