package co.simplon.dtbusiness.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "t_developers")
public class Developer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_developer")
    private Long id;

    @Column(name = "internal_number")
    private String internalNumber;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "description")
    private String description;

    @Column(name = "picture")
    private String picture;

    @Column(name = "linkedin")
    private String linledin;

    @OneToMany(targetEntity = Skill.class, mappedBy = "developer")
    private Set<Skill> skillProficiencies;



    public Developer() {
	// Default for ORM
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getInternalNumber() {
	return internalNumber;
    }

    public void setInternalNumber(String internalNumber) {
	this.internalNumber = internalNumber;
    }

    public String getFirstName() {
	return firstName;
    }

    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    public String getLastName() {
	return lastName;
    }

    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public String getPicture() {
	return picture;
    }

    public void setPicture(String picture) {
	this.picture = picture;
    }

    public String getLinledin() {
	return linledin;
    }

    public void setLinledin(String linledin) {
	this.linledin = linledin;
    }

    public Set<Skill> getSkillProficiencies() {
        return skillProficiencies;
    }

    public void setSkillProficiencies(Set<Skill> skillProficiencies) {
        this.skillProficiencies = skillProficiencies;
    }
}
