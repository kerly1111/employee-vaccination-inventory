package com.kruger.employee.vaccination.inventory.repository.custom;

import com.kruger.employee.vaccination.inventory.model.Role;

import java.util.Optional;

public interface CustomRolRepository {

    Optional<Role> findByName(String name);
}
