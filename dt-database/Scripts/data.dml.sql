DELETE FROM t_skills;
DELETE FROM t_developers ;
DELETE FROM t_topics ;
DELETE FROM t_levels ;



INSERT INTO t_levels (level_name) VALUES 
	('Junior'), ('Senior'), ('Expert');

INSERT INTO t_developers (internal_number, first_name, last_name, email, description, picture, linkedin)
	VALUES
	('abc1234', 'Joe', 'Doe', 'joe@sg.com', 'super dev', '1c3f837e-9263-4894-a46b-b0e99b9a216a.png', 'linkedin.com' ),
	('a000001', 'Abdallah', 'Dev', 'abdallah@sg.com', NULL, '1c3f837e-9263-4894-a46b-b0e99b9a216a.png', NULL),
	('a000002', 'Sabrina', 'Dubois', 'abdallah@sg.com', NULL, '1c3f837e-9263-4894-a46b-b0e99b9a216a.png', NULL),
	('a000003', 'Thao', 'NGUYEN', 'abdallah@sg.com', NULL, '1c3f837e-9263-4894-a46b-b0e99b9a216a.png', NULL)
	;

INSERT INTO t_topics (topic_name, is_technical_skill ) VALUES 
	('Java', true),
	('TeamWork', false);

INSERT INTO t_skills(dev_id, topic_id, level_id ) VALUES 
	(1,1,2),
  (1,2, null),
  (2,1,1),
  (3,1,3);

