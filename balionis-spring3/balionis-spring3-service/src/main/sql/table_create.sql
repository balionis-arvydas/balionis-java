/*
Database: MySQL 5.1
*/

-- Table my_template

CREATE TABLE app_model
(
  model_pk INT NOT NULL,
  name VARCHAR(100),
  model_type CHAR(1),
  modify_at TIMESTAMP NOT NULL
)
;

ALTER TABLE app_model ADD PRIMARY KEY (model_pk)
;

CREATE INDEX app_model_ix1 ON app_model (name)
;

-- Table app_property

CREATE TABLE app_property
(
  property_cd CHAR(30) NOT NULL,
  data VARCHAR (100) NOT NULL,
  modify_at TIMESTAMP NOT NULL
)
;

ALTER TABLE app_property ADD PRIMARY KEY (property_cd)
;

