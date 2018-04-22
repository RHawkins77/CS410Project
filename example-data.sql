INSERT INTO task (task_id, task_label)
  VALUES (2,"Clean Bedroom");

INSERT INTO task (task_id, task_label)
  VALUES (3,"Clean Bathroom");

INSERT INTO task (task_id, task_label)
  VALUES (4,"mow Lawn");

INSERT INTO task (task_id, task_label)
  VALUES (5,"School Homework");

INSERT INTO task (task_id, task_label)
  VALUES (6,"find the elves");

INSERT INTO task (task_id, task_label)
  VALUES (7,"Go grocery shopping");


INSERT INTO curr_task (curr_task_id, date_created, due_date, task_id, status)
  VALUES (001,'1985-01-01', '1985-01-04', 1, 'completed');

INSERT INTO curr_task (curr_task_id, date_created, due_date, task_id, status)
  VALUES (003, '1985-01-04', '1985-04-04', 3, 'completed');

INSERT INTO curr_task (curr_task_id, date_created, due_date, task_id, status)
  VALUES (004, '1954-03-20', '1954-06-06', 6,'overdue');

INSERT INTO curr_task (curr_task_id, date_created, due_date, task_id, status)
  VALUES (005, '1988-06-06', '1989-01-04', 1, 'completed');

INSERT INTO curr_task (date_created, due_date, task_id, status)
  VALUES ('1954-06-06', '1957-04-03', 7,'overdue');

INSERT INTO curr_task (date_created, due_date, task_id, status)
  VALUES ('2018-06-04', '2018-07-04', 2, 'active');

INSERT INTO curr_task (date_created, due_date, task_id, status)
  VALUES ('2018-07-04', '2018-09-04', 5, 'active');


INSERT INTO details (task_id, description)
  VALUES (2, "Make bed");

INSERT INTO details (task_id, description)
  VALUES (3, "Wash Toilet");

INSERT INTO details (task_id, description)
  VALUES (4, "service lawn mower");

INSERT INTO details (task_id, description)
  VALUES (5, "read book");


INSERT INTO details (task_id, description)
  VALUES (6, "sprinkle fairy dust");

INSERT INTO details (task_id, description)
  VALUES (7, "pick up tomatoes");

INSERT INTO details (task_id, description)
  VALUES (1, "clear off counter of debris");

INSERT INTO details (task_id, description)
  VALUES (2, "put dirty clothes in hamper");

INSERT INTO details (task_id, description)
  VALUES (3, "Wash counter and clean mirror");

INSERT INTO details (task_id, description)
  VALUES (4, "Mow front and back yard");

INSERT INTO details (task_id, description)
  VALUES (5, "do math assignment 6");


INSERT INTO details (task_id, description)
  VALUES (6, "hide in the bushes and wait");

INSERT INTO details (task_id, description)
  VALUES (7, "pick up toilet paper");

INSERT INTO details (task_id, description)
  VALUES (1, "wash microwave inside");

INSERT INTO keywords (task_id, keyWords)
    VALUES (5, "Science");

INSERT INTO keywords (task_id, keyWords)
    VALUES (5, "Math");

INSERT INTO keywords (task_id, keyWords)
    VALUES (6, "Lindon");

INSERT INTO keywords (task_id, keyWords)
    VALUES (6, "Lothlorien");

INSERT INTO keywords (task_id, keyWords)
    VALUES (1, "Mom's house");

INSERT INTO keywords (task_id, keyWords)
    VALUES (1, "Dad's house");

INSERT INTO keywords (task_id, keyWords)
    VALUES (2, "Girl's room");

INSERT INTO keywords (task_id, keyWords)
    VALUES (2, "Boy's Room");

INSERT INTO keywords (task_id, keyWords)
    VALUES (3, "Master Bathroom");

INSERT INTO keywords (task_id, keyWords)
    VALUES (3, "Guest Bathroom");

INSERT INTO keywords (task_id, keyWords)
    VALUES (4, "Jared's House");

INSERT INTO keywords (task_id, keyWords)
    VALUES (4, "Mom's House");

INSERT INTO keywords (task_id, keyWords)
    VALUES (7, "Walmart");

INSERT INTO keywords (task_id, keyWords)
    VALUES (7, "Albertson");
