package com.company.system_zarzadzania_dla_agencji_pracy.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Table(name = "pracownik")
@PrimaryKeyJoinColumn(name = "idUzytkownika")
@OnDelete(action = OnDeleteAction.CASCADE)
public class Employee extends User{

    @Column(name = "imie")
    private String name;

    @Column(name = "nazwisko")
    private String surname;

    @Column(name = "nrTelefonu")
    private String phoneNumber;

    @Column(name = "adresZamieszkania")
    private String address;

    @Column(name = "pesel")
    private String pesel;

    @Column(name = "statusStudenta")
    private boolean studentStatus;

    @Column(name = "dataUrodzenia")
    private Date dateOfBirth;



    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idAdministratora")
    private Administrator administrator;
//
//    @OneToOne(mappedBy = "pracownik")
//    private Salary wynagrodzenie;

    @OneToMany(mappedBy = "employee",cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Document> documents;
//
//    @ManyToMany(mappedBy = "pracownicy")
//    List<Order> zlecenia;

}
