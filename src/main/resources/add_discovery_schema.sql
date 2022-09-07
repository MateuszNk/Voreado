USE voreado;

ALTER TABLE discovery
ADD user_id INT NOT NULL,
ADD FOREIGN KEY (user_id) REFERENCES user(id);

UPDATE discovery SET user_id = 1;

ALTER TABLE discovery
MODIFY COLUMN user_id INT NOT NULL;