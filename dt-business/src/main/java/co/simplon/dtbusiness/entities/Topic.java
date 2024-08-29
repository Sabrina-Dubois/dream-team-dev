package co.simplon.dtbusiness.entities;

import jakarta.persistence.*;

@Entity
@Table(name="t_topics")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_topic")
    private Long id;

    @Column(name="skill_name")
    private String name;

    @Column(name="is_technical_skill")
    private boolean isTechnical;

    public boolean isTechnical() {
        return isTechnical;
    }

    public void setTechnical(boolean technical) {
        isTechnical = technical;
    }

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
