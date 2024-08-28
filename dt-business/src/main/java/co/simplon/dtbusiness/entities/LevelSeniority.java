package co.simplon.dtbusiness.entities;

import jakarta.persistence.*;

@Entity
@Table(name="t_level_seniority")
public class LevelSeniority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_level")
    private Long id;

    @Column(name="level_name")
    private String name;

}
