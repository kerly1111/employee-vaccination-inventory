package com.kruger.employee.vaccination.inventory.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import java.util.Date;

import static javax.persistence.GenerationType.SEQUENCE;

@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "\"employee\"")
public class Employee extends AuditFields {

    @Id
    @Column(name = "id_employee", nullable = false)
    @SequenceGenerator(name="\"employee_id_employee_seq\"", allocationSize=1)
    @GeneratedValue(strategy = SEQUENCE, generator = "\"employee_id_employee_seq\"")
    private Long id;

    @Column(name = "identification_number", nullable = false, length = 10, unique = true)
    @Size(min = 10, max = 10, message = "La cédula debe tener 10 digitos")
    private String identificationNumber;

    @Column(nullable = false)
    private String name;

    @Column(name="last_name", nullable = false)
    private String lastName;

    @Email
    @Column(nullable = false)
    private String email;

    @Column(name = "date_birth")
    private Date dateBirth;

    @Column
    private String address;

    @Column
    private String phone;

    @Column(name = "vaccination_status")
    private Boolean vaccinationStatus;

    @Column(name = "fk_user", nullable = false)
    private Long userId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user",
            referencedColumnName = "id_user",
            insertable = false,
            updatable = false,
            nullable = false)
    private User user;

    @OneToOne(mappedBy = "employee")
    private Vaccine vaccine;
}
