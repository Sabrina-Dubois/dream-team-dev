DELETE FROM t_skills;
DELETE FROM t_developers ;
DELETE FROM t_topics ;
DELETE FROM t_levels ;

INSERT INTO t_levels (level_name) VALUES 
	('Junior'), ('Senior'), ('Expert');

INSERT INTO t_developers (internal_number, first_name, last_name, email, description, picture, linkedin)
	VALUES
	('abc1234', 'Joe', 'Doe', 'joe@sg.com', 'super dev', 'http://image.jpn', 'linkedin.com' ),
	('a000001', 'Abdallah', 'Dev', 'abdallah@sg.com', NULL, NULL, NULL),
	('a000002', 'Sabrina', 'Dubois', 'abdallah@sg.com', NULL, NULL, NULL),
	('a000003', 'Thao', 'NGUYEN', 'abdallah@sg.com', NULL, NULL, NULL)
	;

INSERT INTO t_topics (skill_name, is_technical_skill ) VALUES 
	('Java', true),
	('TeamWork', false);

INSERT INTO t_skills(dev_id, topic_id, level_id ) VALUES
	(1,1,2),
(1,2, null);

SELECT * FROM t_levels;
SELECT * FROM t_developers;
SELECT * FROM t_skills;
