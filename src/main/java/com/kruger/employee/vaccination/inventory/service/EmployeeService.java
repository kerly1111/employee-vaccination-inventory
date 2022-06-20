package com.kruger.employee.vaccination.inventory.service;

import com.kruger.employee.vaccination.inventory.common.exception.KrugerException;
import com.kruger.employee.vaccination.inventory.model.Employee;
import com.kruger.employee.vaccination.inventory.model.User;
import com.kruger.employee.vaccination.inventory.model.Vaccine;
import com.kruger.employee.vaccination.inventory.repository.EmployeeRepository;
import com.kruger.employee.vaccination.inventory.repository.RoleRepository;
import com.kruger.employee.vaccination.inventory.repository.UserRepository;
import com.kruger.employee.vaccination.inventory.repository.VaccineRepository;
import com.kruger.employee.vaccination.inventory.service.interfaces.IEmployeeService;
import com.kruger.employee.vaccination.inventory.vo.request.FilterEmployeeRequestVO;
import com.kruger.employee.vaccination.inventory.vo.request.UpdateEmployeeRequestVO;
import com.kruger.employee.vaccination.inventory.vo.response.CreateEmployeeResponseVO;
import com.kruger.employee.vaccination.inventory.vo.request.EmployeeRequestVO;
import com.kruger.employee.vaccination.inventory.vo.response.EmployeeResponseVO;
import com.kruger.employee.vaccination.inventory.vo.response.UserResponseVO;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class EmployeeService implements IEmployeeService {

    @Resource
    private EmployeeRepository employeeRepository;

    @Resource
    private UserRepository userRepository;

    @Resource
    private RoleRepository roleRepository;

    @Resource
    private VaccineRepository vaccineRepository;

    @Override
    @Transactional
    public CreateEmployeeResponseVO createEmployee(EmployeeRequestVO request) {
        try{
            if(!validateName(request.getName())){
                throw new KrugerException("El usuario tiene nombres invalido");
            }
            if(!validateName(request.getLastName())){
                throw new KrugerException("El usuario tiene apellidos invalido");
            }

            Long roleId = this.roleRepository.findByName("ROLE_EMPLOYEE")
                    .orElseThrow(() -> new UsernameNotFoundException("Rol no encontrado")).getId();

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

            User userNew = this.userRepository.save(User.builder()
                    .username(request.getIdentificationNumber())
                    .password(encoder.encode(request.getIdentificationNumber()))
                    .roleId(roleId)
                    .isActive(Boolean.TRUE)
                    .registrationDate(new Timestamp(System.currentTimeMillis()))
                    .build());

            this.employeeRepository.save(Employee.builder()
                            .identificationNumber(request.getIdentificationNumber())
                            .name(request.getName())
                            .lastName(request.getLastName())
                            .email(request.getEmail())
                            .userId(userNew.getId())
                            .isActive(Boolean.TRUE)
                            .registrationDate(new Timestamp(System.currentTimeMillis()))
                            .registrationUserId(
                                    userRepository.findByUsername(
                                            this.userLogged().getUsername())
                                            .orElseThrow(() -> new KrugerException("Usuario no encontrado"))
                                            .getId())
                            .build()).getId();

            CreateEmployeeResponseVO responseVO = CreateEmployeeResponseVO.builder()
                    .message("Empleado creado exitosamente. Acontinuación se visualizan las credenciales del nuevo empleado.")
                    .user(UserResponseVO.builder()
                            .username(userNew.getUsername())
                            .password(request.getIdentificationNumber())
                            .build())
                    .build();

            return responseVO;
        }catch (Exception e){
            throw new KrugerException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void editEmployee(EmployeeRequestVO request) {
        try{
            if(!validateName(request.getName())){
                throw new KrugerException("El usuario tiene nombres invalido");
            }
            if(!validateName(request.getLastName())){
                throw new KrugerException("El usuario tiene apellidos invalido");
            }

            request.setUpdateUser(userRepository.findByUsername(
                    this.userLogged().getUsername())
                    .orElseThrow(() -> new KrugerException("Usuario no encontrado"))
                    .getId()
            );

            this.employeeRepository.editEmployee(
                    this.employeeRepository.findByIdentification(request.getIdentificationNumber())
                            .orElseThrow(() -> new KrugerException("Usuario no encontrado")).getId(), request);

        }catch (Exception e){
            throw new KrugerException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void deleteEmployee(String identificationNumber) {
        try{
            Employee employee = this.employeeRepository.findByIdentification(identificationNumber)
                    .orElseThrow(() -> new UsernameNotFoundException("Empleado no encontrado"));
            this.employeeRepository.delete(employee);
        }catch (Exception e){
            throw new KrugerException(e.getMessage());
        }
    }

    @Override
    public List<EmployeeResponseVO> allEmployee() {
        try{
            List<EmployeeResponseVO> employees = new ArrayList<>();

            this.employeeRepository.allEmployee()
                    .forEach(employee -> employees.add(EmployeeResponseVO.builder()
                            .identificationNumber(employee.getIdentificationNumber())
                            .name(employee.getName())
                            .lastName(employee.getLastName())
                            .email(employee.getEmail())
                            .dateBirth(employee.getDateBirth())
                            .address(employee.getAddress())
                            .phone(employee.getPhone())
                            .vaccinationStatus(employee.getVaccinationStatus())
                            .vaccineType(employee.getVaccine().getType())
                            .vaccinationDate(employee.getVaccine().getVaccinationDate())
                            .numberDoses(employee.getVaccine().getNumberDoses())
                            .build()
                    ));

            return employees;
        }catch (Exception e){
            throw new KrugerException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void updateEmployee(UpdateEmployeeRequestVO request) {
        try{
            if(Objects.equals(request.getVaccinationStatus(), Boolean.TRUE) && (Objects.isNull(request.getVaccineType()) || Objects.isNull(request.getVaccinationDate()) ||
                    Objects.isNull(request.getNumberDoses()))){
                throw new KrugerException("Los datos de vacunación son obligatorios.");
            }

            Employee employee = this.employeeRepository.findByUsername(this.userLogged().getUsername())
                    .orElseThrow(() -> new UsernameNotFoundException("Empleado no encontrado"));

            request.setUpdateUser(employee.getUserId());

            this.employeeRepository.updateEmployee(employee.getId(), request);

            if(Objects.equals(request.getVaccinationStatus(), Boolean.TRUE)){
                if(Objects.isNull(employee.getVaccine().getId())){
                    this.vaccineRepository.save(Vaccine.builder()
                            .type(request.getVaccineType())
                            .vaccinationDate(request.getVaccinationDate())
                            .numberDoses(request.getNumberDoses())
                            .employeId(employee.getId())
                            .isActive(Boolean.TRUE)
                            .registrationDate(new Timestamp(System.currentTimeMillis()))
                            .registrationUserId(employee.getUserId())
                            .build());
                }else{
                    this.vaccineRepository.updateVaccine(Vaccine.builder()
                            .type(request.getVaccineType())
                            .vaccinationDate(request.getVaccinationDate())
                            .numberDoses(request.getNumberDoses())
                            .employeId(employee.getId())
                            .updateDate(new Timestamp(System.currentTimeMillis()))
                            .updateUserId(employee.getUserId())
                            .build()
                    );
                }
            }
        }catch (Exception e){
            throw new KrugerException(e.getMessage());
        }
    }

    @Override
    public EmployeeResponseVO viewEmployee() {
        try{
            Employee employee = this.employeeRepository.viewMyData(this.userLogged().getUsername())
                    .orElseThrow(() -> new UsernameNotFoundException("Empleado no encontrado"));

            return EmployeeResponseVO.builder()
                    .identificationNumber(employee.getIdentificationNumber())
                    .name(employee.getName())
                    .lastName(employee.getLastName())
                    .email(employee.getEmail())
                    .dateBirth(employee.getDateBirth())
                    .address(employee.getAddress())
                    .phone(employee.getPhone())
                    .vaccinationStatus(employee.getVaccinationStatus())
                    .vaccineType(employee.getVaccine().getType())
                    .vaccinationDate(employee.getVaccine().getVaccinationDate())
                    .numberDoses(employee.getVaccine().getNumberDoses())
                    .build();
        }catch (Exception e){
            throw new KrugerException(e.getMessage());
        }
    }

    @Override
    public List<EmployeeResponseVO> findEmployeeByFilter(FilterEmployeeRequestVO request) {
        try{
            if(Objects.isNull(request.getVaccinationStatus()) || Objects.isNull(request.getVaccineType()) ||
                    Objects.isNull(request.getVaccinationDateStart()) || Objects.isNull(request.getVaccinationDateEnd())){
                throw new KrugerException("Faltan criterios de busqueda");
            }
            List<EmployeeResponseVO> employees = new ArrayList<>();
            this.employeeRepository.findEmployeeByFilter(request)
                    .forEach(employee -> employees.add(EmployeeResponseVO.builder()
                            .identificationNumber(employee.getIdentificationNumber())
                            .name(employee.getName())
                            .lastName(employee.getLastName())
                            .email(employee.getEmail())
                            .dateBirth(employee.getDateBirth())
                            .address(employee.getAddress())
                            .phone(employee.getPhone())
                            .vaccinationStatus(employee.getVaccinationStatus())
                            .vaccineType(employee.getVaccine().getType())
                            .vaccinationDate(employee.getVaccine().getVaccinationDate())
                            .numberDoses(employee.getVaccine().getNumberDoses())
                            .build()
                    ));

            return employees;
        }catch (Exception e){
            throw new KrugerException(e.getMessage());
        }
    }

    public static boolean validateName(String cadena) {
        for (int x = 0; x < cadena.length(); x++) {
            char c = cadena.charAt(x);
            // Si no está entre a y z, ni entre A y Z, ni es un espacio
            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == ' ')) {
                return false;
            }
        }
        return true;
    }

    private UserDetails userLogged(){
        UserDetails userDetails = null;

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userDetails = (UserDetails) principal;
        }
        return userDetails;
    }
}
