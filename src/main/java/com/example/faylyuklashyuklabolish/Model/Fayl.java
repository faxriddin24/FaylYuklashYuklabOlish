package com.example.faylyuklashyuklabolish.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Fayl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String arginalfaylnomi;
    @Column(nullable = false)
    private long hajmi;
    @Column(nullable = false)
    private String newnomi;
    @Column(nullable = false)
    private String malumotTuri;
}
