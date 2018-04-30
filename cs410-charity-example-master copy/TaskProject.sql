CREATE DATABASE IF NOT EXISTS project;

CREATE TABLE IF NOT EXISTS task(
 task_id INTEGER PRIMARY KEY AUTO_INCREMENT,
 task_label VARCHAR(255),
 due_date DATE,
 date_created DATE,
 description VARCHAR(1000),
 status ENUM('active', 'completed', 'overdue', 'canceled')
);

CREATE TABLE IF NOT EXISTS keywords (
 keyword_id INTEGER PRIMARY KEY AUTO_INCREMENT,
 keyword_details VARCHAR(1000),
 task_id INTEGER,

 FOREIGN KEY (task_id) REFERENCES task (task_id)
);

INSERT INTO task (task_id, task_label, date_created, due_date,
                 description, status)
   VALUES (1, 'Project 1', '2018-01-08', '2018-01-25',
           'CS410 Project 1', 'completed');

INSERT INTO task (task_id, task_label, date_created, due_date,
                 description, status)
   VALUES (2, 'Assignment 1', '2018-02-06', '2018-02-21',
           'CS401 Assignment 1', 'completed');

INSERT INTO task (task_id, task_label, date_created, due_date,
                 description, status)
   VALUES (3, 'Homework 1', '2018-02-28', '2018-03-10',
           'CS471 Homework 1', 'completed');

INSERT INTO task (task_id, task_label, date_created, due_date,
                 description, status)
   VALUES (4, 'Paper 1', '2018-03-23', '2018-04-01',
           'CS441 Project 1', 'completed');

INSERT INTO task (task_id, task_label, date_created, due_date,
                 description, status)
   VALUES (5, 'Discussion 1', '2018-04-16', '2018-04-29',
           'UF100 Discussion 1', 'completed');

INSERT INTO task (task_id, task_label, date_created, due_date,
                 description, status)
   VALUES (6, 'Project 2', '2018-01-08', '2018-01-25',
           'CS410 Project 2', 'active');

INSERT INTO task (task_id, task_label, date_created, due_date,
                 description, status)
   VALUES (7, 'Assignment 2', '2018-02-06', '2018-02-21',
           'CS401 Assignment 2', 'active');

INSERT INTO task (task_id, task_label, date_created, due_date,
                 description, status)
   VALUES (8, 'Project 2', '2018-02-28', '2018-03-10',
           'CS410 Project 2', 'active');

INSERT INTO task (task_id, task_label, date_created, due_date,
                 description, status)
   VALUES (9, 'Paper 2', '2018-03-23', '2018-04-01',
           'CS441 Project 2', 'active');

INSERT INTO task (task_id, task_label, date_created, due_date,
                 description, status)
   VALUES (10, 'Discussion 2', '2018-04-16', '2018-04-29',
           'UF100 Discussion 2', 'active');

INSERT INTO task (task_id, task_label, date_created, due_date,
                 description, status)
   VALUES (11, 'Project 3', '2018-01-08', '2018-01-25',
           'CS410 Project 3', 'overdue');

INSERT INTO task (task_id, task_label, date_created, due_date,
                 description, status)
   VALUES (12, 'Assignment 3', '2018-02-06', '2018-02-21',
           'CS401 Assignment 3', 'overdue');

INSERT INTO task (task_id, task_label, date_created, due_date,
                 description, status)
   VALUES (13, 'Project 3', '2018-02-28', '2018-03-10',
           'CS410 Project 3', 'overdue');

INSERT INTO task (task_id, task_label, date_created, due_date,
                 description, status)
   VALUES (14, 'Paper 3', '2018-03-23', '2018-04-01',
           'CS441 Project 3', 'overdue');

INSERT INTO task (task_id, task_label, date_created, due_date,
                 description, status)
   VALUES (15, 'Discussion 3', '2018-04-16', '2018-04-29',
           'UF100 Discussion 3', 'overdue');

INSERT INTO task (task_id, task_label, date_created, due_date,
                 description, status)
   VALUES (16, 'Project 4', '2018-01-08', '2018-01-25',
           'CS410 Project 4', 'canceled');

INSERT INTO task (task_id, task_label, date_created, due_date,
                 description, status)
   VALUES (17, 'Assignment 4', '2018-02-06', '2018-02-21',
           'CS401 Assignment 4', 'canceled');

INSERT INTO task (task_id, task_label, date_created, due_date,
                 description, status)
   VALUES (18, 'Project 4', '02-28-2018-02-28', '2018-03-10',
           'CS410 Project 4', 'canceled');

INSERT INTO task (task_id, task_label, date_created, due_date,
                 description, status)
   VALUES (19, 'Paper 4', '2018-03-23', '2018-04-01',
           'CS441 Project 4', 'canceled');

INSERT INTO task (task_id, task_label, date_created, due_date,
                 description, status)
   VALUES (20, 'Discussion 4', '2018-04-16', '2018-04-29',
           'UF100 Discussion 4', 'canceled');



INSERT INTO keywords (keyword_id, keyword_details, task_id)
   VALUES(1, 'school homework', 1);

INSERT INTO keywords (keyword_id, keyword_details, task_id)
   VALUES(2, 'school homework', 2);

INSERT INTO keywords (keyword_id, keyword_details, task_id)
   VALUES(3, 'project', 3);

INSERT INTO keywords (keyword_id, keyword_details, task_id)
   VALUES(4, 'project', 4);

INSERT INTO keywords (keyword_id, keyword_details, task_id)
   VALUES(5, 'school homework', 5);

INSERT INTO keywords (keyword_id, keyword_details, task_id)
   VALUES(6, 'school homework', 6);

INSERT INTO keywords (keyword_id, keyword_details, task_id)
   VALUES(7, 'project', 7);

INSERT INTO keywords (keyword_id, keyword_details, task_id)
   VALUES(8, 'project', 8);

INSERT INTO keywords (keyword_id, keyword_details, task_id)
   VALUES(9, 'school', 9);

INSERT INTO keywords (keyword_id, keyword_details, task_id)
   VALUES(10, 'school', 10);

INSERT INTO keywords (keyword_id, keyword_details, task_id)
   VALUES(11, 'school', 11);

INSERT INTO keywords (keyword_id, keyword_details, task_id)
   VALUES(12, 'school', 12);

INSERT INTO keywords (keyword_id, keyword_details, task_id)
   VALUES(13, 'school', 13);

INSERT INTO keywords (keyword_id, keyword_details, task_id)
   VALUES(14, 'school', 14);

INSERT INTO keywords (keyword_id, keyword_details, task_id)
   VALUES(15, 'school', 15);

INSERT INTO keywords (keyword_id, keyword_details, task_id)
   VALUES(16, 'Resume', 16);

INSERT INTO keywords (keyword_id, keyword_details, task_id)
   VALUES(17, 'scholarship', 17);

INSERT INTO keywords (keyword_id, keyword_details, task_id)
   VALUES(18, 'college', 18);

INSERT INTO keywords (keyword_id, keyword_details, task_id)
   VALUES(19, 'paper', 19);

INSERT INTO keywords (keyword_id, keyword_details, task_id)
   VALUES(20, 'project', 20);
