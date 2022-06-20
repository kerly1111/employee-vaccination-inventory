package com.kruger.employee.vaccination.inventory.repository;

import com.kruger.employee.vaccination.inventory.model.User;
import com.kruger.employee.vaccination.inventory.repository.custom.CustomUserRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long>, CustomUserRepository {

}
