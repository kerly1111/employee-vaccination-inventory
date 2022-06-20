package com.kruger.employee.vaccination.inventory.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@SuperBuilder
@MappedSuperclass
public class AuditFields implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @Column(name = "registration_date", nullable = false)
    private Timestamp registrationDate;

    @Column(name = "fk_registration_user", nullable = true)
    private Long registrationUserId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_registration_user",
            referencedColumnName = "id_user",
            insertable = false,
            updatable = false)
    @JsonIgnore
    private User registrationUser;

    @Column(name = "update_date")
    private Timestamp updateDate;

    @Column(name = "fk_update_user")
    private Long updateUserId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_update_user",
            referencedColumnName = "id_user",
            insertable = false,
            updatable = false)
    @JsonIgnore
    private User updateUser;
}
