package co.simplon.dtbusiness.entities;

import jakarta.persistence.*;

@Entity
@Table(name="t_skills")
public class Skill {
    @EmbeddedId
    private SkillId id;

    @ManyToOne
    @MapsId("developer")
    @JoinColumn(name = "developer_id")
    private Developer developer;

    @ManyToOne
    @MapsId("topic")
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @ManyToOne
    @JoinColumn(name = "level_id")
    private Level level;

}
