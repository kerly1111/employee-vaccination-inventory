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

import java.util.Date;

import static javax.persistence.GenerationType.SEQUENCE;

@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "\"vaccine\"")
public class Vaccine extends AuditFields {

    @Id
    @Column(name = "id_vaccine", nullable = false)
    @SequenceGenerator(name="\"vaccine_id_vaccine_seq\"", allocationSize=1)
    @GeneratedValue(strategy = SEQUENCE, generator = "\"vaccine_id_vaccine_seq\"")
    private Integer id;

    @Column(nullable = false)
    private String type;

    @Column(name="vaccination_date", nullable = false)
    private Date vaccinationDate;

    @Column(name="number_doses", nullable = false)
    private Integer numberDoses;

    @Column(name = "fk_employee", nullable = false)
    private Long employeId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_employee",
            referencedColumnName = "id_employee",
            insertable = false,
            updatable = false,
            nullable = false)
    private Employee employee;
}
