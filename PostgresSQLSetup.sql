-- Sequence: calculations_sequence

-- DROP SEQUENCE calculations_sequence;

CREATE SEQUENCE calculations_sequence
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 10
  CACHE 1;
ALTER TABLE calculations_sequence
  OWNER TO postgres;
  
-- Sequence: items_sequence

-- DROP SEQUENCE items_sequence;

CREATE SEQUENCE items_sequence
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 10
  CACHE 1;
ALTER TABLE items_sequence
  OWNER TO postgres;

-- Sequence: purchases_sequence

-- DROP SEQUENCE purchases_sequence;

CREATE SEQUENCE purchases_sequence
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 15
  CACHE 1;
ALTER TABLE purchases_sequence
  OWNER TO postgres;

-- Sequence: user_groups_sequence

-- DROP SEQUENCE user_groups_sequence;

CREATE SEQUENCE user_groups_sequence
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 2
  CACHE 1;
ALTER TABLE user_groups_sequence
  OWNER TO postgres;

-- Sequence: users_sequence

-- DROP SEQUENCE users_sequence;

CREATE SEQUENCE users_sequence
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 9
  CACHE 1;
ALTER TABLE users_sequence
  OWNER TO postgres;

-- Table: user_groups

-- DROP TABLE user_groups;

CREATE TABLE user_groups
(
  id integer NOT NULL DEFAULT nextval('user_groups_sequence'::regclass),
  name character varying(100),
  CONSTRAINT user_group_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE user_groups
  OWNER TO postgres;

-- Table: users

-- DROP TABLE users;

CREATE TABLE users
(
  id integer NOT NULL DEFAULT nextval('users_sequence'::regclass),
  login character varying(100),
  name character varying(200),
  email character varying(100),
  password character varying(20),
  active boolean DEFAULT true,
  deleted boolean DEFAULT false,
  usergroup integer,
  role character varying(10),
  CONSTRAINT user_pkey PRIMARY KEY (id),
  CONSTRAINT user_usergroup_fk FOREIGN KEY (usergroup)
      REFERENCES user_groups (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE users
  OWNER TO postgres;
  
-- Table: items

-- DROP TABLE items;

CREATE TABLE items
(
  id integer NOT NULL DEFAULT nextval('items_sequence'::regclass),
  name character varying(100),
  description character varying(500),
  usergroup integer,
  CONSTRAINT item_pkey PRIMARY KEY (id),
  CONSTRAINT item_usergroup_fk FOREIGN KEY (usergroup)
      REFERENCES user_groups (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE items
  OWNER TO postgres;

-- Table: purchases

-- DROP TABLE purchases;

CREATE TABLE purchases
(
  id integer NOT NULL DEFAULT nextval('purchases_sequence'::regclass),
  item integer,
  paid_by character varying(200),
  excluded_persons character varying(100),
  comment character varying(500),
  usergroup integer,
  purchase_date date,
  CONSTRAINT purchase_pkey PRIMARY KEY (id),
  CONSTRAINT purchase_item_fk FOREIGN KEY (item)
      REFERENCES items (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT purchase_usergroup_fk FOREIGN KEY (usergroup)
      REFERENCES user_groups (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE purchases
  OWNER TO postgres;

-- Table: calculations

-- DROP TABLE calculations;

CREATE TABLE calculations
(
  id integer NOT NULL DEFAULT nextval('calculations_sequence'::regclass),
  description character varying(500),
  calculation_date date,
  user_group integer,
  last_purchase_id integer,
  amount_paid character varying(200),
  amount_share character varying(200),
  CONSTRAINT calculation_pkey PRIMARY KEY (id),
  CONSTRAINT calculation_purchase_fk FOREIGN KEY (last_purchase_id)
      REFERENCES purchases (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT calculation_usergroup_fk FOREIGN KEY (user_group)
      REFERENCES user_groups (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE calculations
  OWNER TO postgres;