DROP TABLE IF EXISTS test;

CREATE TABLE test (
  id BIGINT NOT NULL AUTO_INCREMENT,
  description VARCHAR(255) NOT NULL,
  create_time DATETIME NOT NULL,
  update_time DATETIME NOT NULL,
  PRIMARY KEY (id)
);