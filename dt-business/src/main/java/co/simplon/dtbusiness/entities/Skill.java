package co.simplon.dtbusiness.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "t_skills")
public class Skill {
    @EmbeddedId
    private SkillId id;

    @ManyToOne
    @MapsId("developer")
    @JoinColumn(name = "dev_id")
    private Developer developer;

    @ManyToOne
    @MapsId("topic")
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @ManyToOne
    @JoinColumn(name = "level_id")
    private Level level;

    public SkillId getId() {
        return id;
    }

    public void setId(SkillId id) {
        this.id = id;
    }

    public Developer getDeveloper() {
        return developer;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
}
