package co.simplon.dtbusiness.entities;

import jakarta.persistence.*;

@Entity
@Table(name="t_topics")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_skill")
    private Long id;

    @Column(name="skill_name")
    private String name;

    @Column(name="is_technical_skill")
    private String isTechnical;

}
