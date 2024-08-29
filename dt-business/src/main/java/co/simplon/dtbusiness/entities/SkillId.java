package co.simplon.dtbusiness.entities;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class SkillId implements Serializable {
    private Long developer;
    private Long topic;

    public Long getTopic() {
        return topic;
    }

    public void setTopic(Long topic) {
        this.topic = topic;
    }

    public Long getDeveloper() {
        return developer;
    }

    public void setDeveloper(Long developer) {
        this.developer = developer;
    }
}
