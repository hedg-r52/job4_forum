package ru.job4j.forum.store;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.forum.model.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {
}
