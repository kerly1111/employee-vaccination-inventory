package com.kruger.employee.vaccination.inventory.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.sql.Timestamp;

import static javax.persistence.GenerationType.SEQUENCE;

@SuperBuilder
@NoArgsConstructor
@Data
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "\"user\"")
public class User {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_user", nullable = false)
    @SequenceGenerator(name="\"user_id_user_seq\"", allocationSize=1)
    @GeneratedValue(strategy = SEQUENCE, generator = "\"user_id_user_seq\"")
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(name = "fk_role", nullable = false)
    private Long roleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_role",
            referencedColumnName = "id_role",
            insertable = false,
            updatable = false,
            nullable = false)
    private Role role;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @Column(name = "registration_date", nullable = false)
    private Timestamp registrationDate;

    @Column(name = "update_date")
    private Timestamp updateDate;

    @OneToOne(mappedBy = "user")
    private Employee employee;
}
