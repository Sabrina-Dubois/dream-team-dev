DROP TABLE IF EXISTS t_profile_skill_level;
DROP TABLE IF EXISTS t_profiles;
DROP TABLE IF EXISTS t_skills;
DROP TABLE IF EXISTS t_level_seniority;

CREATE TABLE t_profiles(
	id_profile INT GENERATED ALWAYS AS IDENTITY,
	internal_number VARCHAR(20) NOT NULL,
   	first_name VARCHAR(100) NOT NULL,
   	last_name VARCHAR(100) NOT NULL,
   	email VARCHAR(50) NOT NULL,
   	description VARCHAR(5000),
   	picture VARCHAR(41),
   	linkedin VARCHAR(200),
   	CONSTRAINT t_profiles_pkey PRIMARY KEY (id_profile),
   	CONSTRAINT t_profiles_ukey UNIQUE (internal_number)
);

CREATE TABLE t_skills(
	id_skill INT GENERATED ALWAYS AS IDENTITY,
   	skill_name VARCHAR(100) NOT NULL,
   	is_technical_skill BOOLEAN NOT NULL,
    CONSTRAINT t_skills_pkey PRIMARY KEY (id_skill),
   	CONSTRAINT t_skills_ukey UNIQUE (skill_name)
);

CREATE TABLE t_level_seniority(
	id_level INT GENERATED ALWAYS AS IDENTITY,
	level_name VARCHAR(50) NOT NULL,
   	CONSTRAINT t_level_pkey PRIMARY KEY(id_level),
   	CONSTRAINT t_level_ukey UNIQUE (level_name)
);

CREATE TABLE t_profile_skill_level(
	id_profile_skill_level INT GENERATED ALWAYS AS IDENTITY,
   	profile_id INT NOT NULL,
   	skill_id INT NOT NULL,
   	level_id INT NOT NULL,
   	CONSTRAINT t_profile_skill_level_pkey PRIMARY KEY (id_profile_skill_level),
   	
   	CONSTRAINT t_profile_skill_level_ukey UNIQUE (profile_id, skill_id),
   	
   	FOREIGN KEY(profile_id) REFERENCES t_profiles(id_profile),
   	FOREIGN KEY(skill_id) REFERENCES t_skills(id_skill),
   	FOREIGN KEY(level_id) REFERENCES t_level_seniority(id_level)
);

