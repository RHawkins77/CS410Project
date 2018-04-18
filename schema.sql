Create SCHEMA projectTasks;

Create TABLE task(
  task_id INTEGER PRIMARY KEY AUTO_INCREMENT,
  task_label VARCHAR(255)
);

CREATE TABLE details(
  detail_id INTEGER PRIMARY KEY AUTO_INCREMENT,
  task_id INTEGER,
  description VARCHAR(255),

  FOREIGN KEY (task_id) REFERENCES task(task_id)
);


CREATE TABLE keywords (
  keyword_id INTEGER PRIMARY KEY AUTO_INCREMENT,
  task_id INTEGER,
  keyWords VARCHAR(255)

)

CREATE TABLE curr_task(
  curr_task_id INTEGER PRIMARY KEY AUTO_INCREMENT,
  due_date DATE,
  task_id INTEGER,
  status ENUM ('active', 'completed', 'overdue'),

  FOREIGN KEY (task_id) REFERENCES task(task_id)
)

