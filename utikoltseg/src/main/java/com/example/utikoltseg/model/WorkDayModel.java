package com.example.utikoltseg.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "munkanap")
public class WorkDayModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "sorszam")
    private Integer count;

    @Column(name = "datum")
    private String date;

    @Column(name = "tavolsag")
    private String distance;

    @Column(name = "elszamolhato")
    private String pay;
}
