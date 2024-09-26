package co.simplon.dtbusiness.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_filters")
public class Filter {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_filter")
	private long id;
	
	@Column(name="filter_name", nullable = false)
	private String name;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getFirstTopic() {
		return firstTopic;
	}

	public void setFirstTopic(String firstTopic) {
		this.firstTopic = firstTopic;
	}

	public String getSecondTopic() {
		return secondTopic;
	}

	public void setSecondTopic(String secondTopic) {
		this.secondTopic = secondTopic;
	}

	@Column(name="keyword")
	private String keyword;
	
	@Column(name="first_topic")
	private String firstTopic;
	
	@Column(name="second_topic")
	private String secondTopic;
	

}
