/*
Database: MySQL 5.1
*/

-- Drop relationships section -------------------------------------------------

-- Drop keys for tables section -------------------------------------------------

ALTER TABLE app_model DROP PRIMARY KEY
;
ALTER TABLE app_property DROP PRIMARY KEY
;

-- Drop indexes section -------------------------------------------------

DROP INDEX app_model_ix1 ON app_model
;
DROP INDEX app_property_ix1 ON app_property
;

-- Drop tables section ---------------------------------------------------

DROP TABLE app_model
;
DROP TABLE app_property
;
