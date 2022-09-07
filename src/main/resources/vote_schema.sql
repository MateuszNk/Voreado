USE voreado;

CREATE TABLE IF NOT EXISTS vote (
    user_id INT NOT NULL,
    discovery_id INT NOT NULL,
    type ENUM('UP', 'DOWN') NOT NULL,
    date_added DATETIME NOT NULL,
    PRIMARY KEY (user_id, discovery_id),
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (discovery_id) REFERENCES discovery(id)
);