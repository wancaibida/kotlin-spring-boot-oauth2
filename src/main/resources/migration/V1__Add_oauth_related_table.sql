CREATE SEQUENCE hibernate_sequence
  START 1
  INCREMENT 1;

CREATE TABLE oauth_access_token (
  token_id          TEXT,
  token             BYTEA,
  authentication_id TEXT PRIMARY KEY,
  user_name         TEXT,
  client_id         TEXT,
  authentication    BYTEA,
  refresh_token     TEXT
);

CREATE TABLE oauth_refresh_token (
  token_id       TEXT,
  token          BYTEA,
  authentication BYTEA
);

CREATE TABLE role (
  id        INT8 NOT NULL,
  authority TEXT,
  PRIMARY KEY (id)
);

CREATE TABLE users (
  id                      INT8 NOT NULL,
  account_non_expired     BOOLEAN,
  account_non_locked      BOOLEAN,
  credentials_non_expired BOOLEAN,
  enabled                 BOOLEAN,
  password                TEXT,
  username                TEXT,
  PRIMARY KEY (id)
);

CREATE TABLE users_roles (
  user_id  INT8 NOT NULL,
  role_id INT8 NOT NULL
);

ALTER TABLE IF EXISTS oauth_access_token
  ADD CONSTRAINT UK_o41dyuwl8inwoir9b817nki7i UNIQUE (authentication_id);
ALTER TABLE IF EXISTS users_roles
  ADD CONSTRAINT FK15d410tj6juko0sq9k4km60xq FOREIGN KEY (role_id) REFERENCES role;
ALTER TABLE IF EXISTS users_roles
  ADD CONSTRAINT FK2o0jvgh89lemvvo17cbqvdxaa FOREIGN KEY (user_id) REFERENCES users