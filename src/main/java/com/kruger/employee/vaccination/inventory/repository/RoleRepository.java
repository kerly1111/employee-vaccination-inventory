package com.kruger.employee.vaccination.inventory.repository;

import com.kruger.employee.vaccination.inventory.model.Role;
import com.kruger.employee.vaccination.inventory.repository.custom.CustomRolRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long>, CustomRolRepository {
}
