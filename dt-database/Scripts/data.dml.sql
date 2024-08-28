DELETE FROM t_profile_skill_level ;
DELETE FROM t_level_seniority ;
DELETE FROM t_profiles ;
DELETE FROM t_skills ;

INSERT INTO t_level_seniority (level_name) VALUES 
	('Junior'), ('Senior'), ('Experience');

INSERT INTO t_profiles (internal_number, first_name, last_name, email, description, picture, linkedin)
	VALUES
	('ABC1234', 'Joe', 'Doe', 'joe@sg.com', 'super dev', 'http://image.jpn', 'linkedin.com' )
	;

INSERT INTO t_skills (skill_name, is_technical_skill ) VALUES 
	('Java', true),
	('TeamWork', false);

INSERT INTO t_profile_skill_level(profile_id, skill_id, level_id ) VALUES 
	(1,1,2);

