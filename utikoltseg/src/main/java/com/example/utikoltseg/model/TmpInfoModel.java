package com.example.utikoltseg.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "ideiglenes")
public class TmpInfoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "identifier")
    private Long identifier;

    @Column(name = "szemelynev")
    private String personName;

    @Column(name = "lakcim")
    private String address;

    @Column(name = "osszesen")
    private Integer sumMoney;

    @Column(name = "datum_ev")
    private String nowYearDate;

    @Column(name = "datum_ho")
    private String nowMonthDate;

    @Column(name = "datum_nap")
    private String nowDayDate;
}
