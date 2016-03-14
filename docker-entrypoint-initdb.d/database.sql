CREATE TABLE "user" (
  id       VARCHAR(255) PRIMARY KEY,
  name     VARCHAR(255) NOT NULL,
  lastname VARCHAR(255) NOT NULL
);

CREATE TABLE "advert" (
  id        VARCHAR(255)     NOT NULL PRIMARY KEY,
  title     VARCHAR(255)     NOT NULL,
  body      TEXT             NULL,
  latitude  DOUBLE PRECISION NOT NULL,
  longitude DOUBLE PRECISION NOT NULL,
  zip_code  CHAR(5)          NOT NULL
);

-- Fixture data...
INSERT INTO "user" ("id", "name", "lastname") VALUES (1, 'Odín', 'del Río');
INSERT INTO "user" ("id", "name", "lastname") VALUES (2, 'Mapache', 'del Bosque');
INSERT INTO "user" ("id", "name", "lastname") VALUES (3, 'Moucho', 'Malísimo');
