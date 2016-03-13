CREATE TABLE users (
  id       VARCHAR(255) PRIMARY KEY,
  name     VARCHAR(255) NOT NULL,
  lastname VARCHAR(255) NOT NULL
);

CREATE TABLE advert (
  id       UUID PRIMARY KEY,
  title    VARCHAR(255) NOT NULL,
  body     TEXT         NULL,
  location POINT        NULL,
  zip_code CHAR(5),
  category INT          NULL
);

-- Fixture data...
INSERT INTO users ("id", "name", "lastname") VALUES (1, 'Odín', 'del Río');
INSERT INTO users ("id", "name", "lastname") VALUES (2, 'Mapache', 'del Bosque');
INSERT INTO users ("id", "name", "lastname") VALUES (3, 'Moucho', 'Malísimo');
