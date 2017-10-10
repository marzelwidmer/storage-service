-- --- This script is used by Flyway and is also the source for the scripts
-- --- generated for manual setup of the database.
--
-- --- Comments starting with triple dash (---) are not included in the
-- --- generated scripts.
--
-- --- Database specific statements can be prefixed with ${oracle} or ${h2}
-- --- e.g. see the grants which only work with oracle for specific permission.
-- CREATE SEQUENCE HIBERNATE_SEQUENCE;
-- ${oracle} GRANT SELECT, INSERT, UPDATE, ALTER ON HIBERNATE_SEQUENCE TO ${grant.user};
--
-- CREATE TABLE ADDRESS(
--   "ID" NUMBER(19,0),
-- 	"LOCALITY" VARCHAR2(255 CHAR),
-- 	"MUNICIPALITY" VARCHAR2(255 CHAR),
-- 	"MUNICIPALITY_NR" VARCHAR2(255 CHAR),
-- 	"POSTAL_CODE" VARCHAR2(255 CHAR),
-- 	"POSTAL_CODE_ADDITION" VARCHAR2(255 CHAR)
-- );
--
-- ${oracle} GRANT SELECT, INSERT, UPDATE, DELETE ON "SESSIONS" TO ${grant.user};
CREATE TABLE UPLOAD (
  "ID" VARCHAR (255 char),
  "NAME" VARCHAR(128) DEFAULT NULL,
  "DATA" BLOB
)