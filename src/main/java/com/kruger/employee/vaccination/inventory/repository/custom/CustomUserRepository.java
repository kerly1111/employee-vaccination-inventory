package com.kruger.employee.vaccination.inventory.repository.custom;

import com.kruger.employee.vaccination.inventory.model.User;

import java.util.Optional;

public interface CustomUserRepository {

    Optional<User> findByUsername(String username);
}
