DROP TABLE IF EXISTS t_skills;
DROP TABLE IF EXISTS t_developers;
DROP TABLE IF EXISTS t_topics;
DROP TABLE IF EXISTS t_levels;

CREATE TABLE t_developers(
	id_dev INT GENERATED ALWAYS AS IDENTITY,
	internal_number VARCHAR(20) NOT NULL,
   	first_name VARCHAR(100) NOT NULL,
   	last_name VARCHAR(100) NOT NULL,
   	email VARCHAR(50) NOT NULL,
   	description VARCHAR(5000),
   	picture VARCHAR(41),
   	linkedin VARCHAR(200),
   	CONSTRAINT t_developers_pkey PRIMARY KEY (id_dev),
   	CONSTRAINT t_developers_ukey UNIQUE (internal_number)
);

CREATE TABLE t_topics(
	id_topic INT GENERATED ALWAYS AS IDENTITY,
   	skill_name VARCHAR(100) NOT NULL,
   	is_technical_skill BOOLEAN NOT NULL,
    CONSTRAINT t_topics_pkey PRIMARY KEY (id_topic),
   	CONSTRAINT t_topics_ukey UNIQUE (skill_name)
);

CREATE TABLE t_levels(
	id_level INT GENERATED ALWAYS AS IDENTITY,
	level_name VARCHAR(50) NOT NULL,
   	CONSTRAINT t_levels_pkey PRIMARY KEY(id_level),
   	CONSTRAINT t_levels_ukey UNIQUE (level_name)
);

CREATE TABLE t_skills(
	id_skill INT GENERATED ALWAYS AS IDENTITY,
   	dev_id INT NOT NULL,
   	topic_id INT NOT NULL,
   	level_id INT,
   	CONSTRAINT t_skills_pkey PRIMARY KEY (id_skill),
   	CONSTRAINT t_skills_ukey UNIQUE (dev_id, topic_id),
   	CONSTRAINT t_skills_developers_fkey  FOREIGN KEY(dev_id) REFERENCES t_developers(id_dev),
   	CONSTRAINT t_skills_topics_fkey FOREIGN KEY(topic_id) REFERENCES t_topics(id_topic),
   	CONSTRAINT t_skills_levels_fkey FOREIGN KEY(level_id) REFERENCES t_levels(id_level)
);

