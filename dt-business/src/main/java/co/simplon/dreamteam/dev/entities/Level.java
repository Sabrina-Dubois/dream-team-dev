package co.simplon.dreamteam.dev.entities;

import jakarta.persistence.*;

@Entity
@Table(name="t_levels")
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_level")
    private Long id;

    @Column(name="level_name")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
