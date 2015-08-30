# Users schema

# --- !Ups

create table "user"
( "ID" BIGSERIAL NOT NULL PRIMARY KEY,
  "NAME" VARCHAR NOT NULL,
  "FIRSTNAME" VARCHAR NOT NULL,
  "EMAIL" VARCHAR NOT NULL,
  "PASSWORD" VARCHAR NOT NULL,
  "BIRTHDAY" DATE NOT NULL,
  "SIGN_DATE" DATE NOT NULL
);

# --- !Downs

DROP TABLE user;