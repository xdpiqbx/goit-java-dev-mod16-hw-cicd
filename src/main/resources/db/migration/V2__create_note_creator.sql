CREATE TABLE note_creator(
  id IDENTITY PRIMARY KEY,
  name VARCHAR (255),
  password VARCHAR (255),
  authorities VARCHAR (255)
);

INSERT INTO note_creator(name, password, authorities)
  VALUES
    ('user', '{noop}user', 'USER');