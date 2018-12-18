-- Table: public.categories

DROP  TABLE public.categories CASCADE;
DROP SEQUENCE categories_id_seq ;
CREATE SEQUENCE categories_id_seq START WITH 1;

CREATE TABLE public.categories
(
    id bigint NOT NULL DEFAULT nextval('categories_id_seq'::regclass),
    name character varying(64) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT categories_pkey PRIMARY KEY (id),
    CONSTRAINT categories_name_key UNIQUE (name)

)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

--ALTER TABLE public.order_statuses
--   OWNER to bmmnonpsiqroee;

-- Table: public.order_types

DROP TABLE public.order_types  CASCADE;
DROP SEQUENCE ordertype_typeid_seq;
CREATE SEQUENCE ordertype_typeid_seq START WITH 1;

CREATE TABLE public.order_types
(
    id bigint NOT NULL DEFAULT nextval('ordertype_typeid_seq'::regclass),
    name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT ordertype_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

--ALTER TABLE public.order_statuses
--   OWNER to bmmnonpsiqroee;

-- Table: public.roles

DROP TABLE public.roles  CASCADE;
DROP SEQUENCE roles_id_seq;
CREATE SEQUENCE roles_id_seq START WITH 1;

CREATE TABLE public.roles
(
    id bigint NOT NULL DEFAULT nextval('roles_id_seq'::regclass),
    name character varying(16) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT roles_pkey PRIMARY KEY (id),
    CONSTRAINT roles_name_key UNIQUE (name)

)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

--ALTER TABLE public.roles
--    OWNER to bmmnonpsiqroee;
-- Table: public.users

DROP TABLE public.users  CASCADE;
DROP SEQUENCE users_id_seq;
CREATE SEQUENCE users_id_seq START WITH 1;

CREATE TABLE public.users
(
    id bigint NOT NULL DEFAULT nextval('users_id_seq'::regclass),
    email character varying(255) COLLATE pg_catalog."default" NOT NULL,
    password character varying(64) COLLATE pg_catalog."default" NOT NULL,
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    surname character varying(255) COLLATE pg_catalog."default" NOT NULL,
    phone character varying(15) COLLATE pg_catalog."default" NOT NULL,
    active boolean NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT users_email_key UNIQUE (email)

)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

-- ALTER TABLE public.users
--   OWNER to bmmnonpsiqroee;
-- Table: public.users_roles

DROP TABLE public.users_roles  CASCADE;

CREATE TABLE public.users_roles
(
    id_user bigint NOT NULL,
    id_role bigint NOT NULL,
    CONSTRAINT users_roles_pkey PRIMARY KEY (id_user, id_role),
    CONSTRAINT users_roles_id_role_fkey FOREIGN KEY (id_role)
        REFERENCES public.roles (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT users_roles_id_user_fkey FOREIGN KEY (id_user)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

-- ALTER TABLE public.users_roles
--    OWNER to bmmnonpsiqroee;
-- Table: public.products

DROP TABLE public.products  CASCADE;
DROP SEQUENCE products_id_seq;
CREATE SEQUENCE products_id_seq START WITH 1;
CREATE TABLE public.products
(
    id bigint NOT NULL DEFAULT nextval('products_id_seq'::regclass),
    name character varying(512) COLLATE pg_catalog."default" NOT NULL,
    amount integer NOT NULL,
    id_category bigint NOT NULL,
    active boolean NOT NULL,
    description character varying COLLATE pg_catalog."default",
    image character varying(1024) COLLATE pg_catalog."default",
    icon character varying(1024) COLLATE pg_catalog."default",
    reserved integer NOT NULL DEFAULT 0,
    CONSTRAINT products_pkey PRIMARY KEY (id),
    CONSTRAINT products_name_key UNIQUE (name)
,
    CONSTRAINT products_id_category_fkey FOREIGN KEY (id_category)
        REFERENCES public.categories (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

--ALTER TABLE public.products
--    OWNER to bmmnonpsiqroee;

-- Table: public.orders

DROP TABLE public.orders  CASCADE;
DROP SEQUENCE orders_orderid_seq;
DROP SEQUENCE orders_status_seq;
DROP SEQUENCE orders_orderstype_seq;
DROP SEQUENCE orders_createdby_seq;
DROP SEQUENCE orders_assignedto_seq;

CREATE SEQUENCE orders_orderid_seq START WITH 1;
CREATE SEQUENCE orders_status_seq START WITH 1;
CREATE SEQUENCE orders_orderstype_seq START WITH 1;
CREATE SEQUENCE orders_createdby_seq START WITH 1;
CREATE SEQUENCE orders_assignedto_seq START WITH 1;

CREATE TABLE public.orders
(
    id bigint NOT NULL DEFAULT nextval('orders_orderid_seq'::regclass),
    id_parent bigint,
    id_order_type bigint NOT NULL DEFAULT nextval('orders_orderstype_seq'::regclass),
    created timestamp without time zone NOT NULL,
    changed timestamp without time zone NOT NULL,
    annotation character varying(2000) COLLATE pg_catalog."default",
    archived boolean NOT NULL,
    id_user bigint NOT NULL DEFAULT nextval('orders_createdby_seq'::regclass),
    id_keeper bigint,
    order_statuses character varying COLLATE pg_catalog."default",
    CONSTRAINT orders_pkey PRIMARY KEY (id),
    CONSTRAINT constraint1 FOREIGN KEY (id_parent)
        REFERENCES public.orders (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT orders_createdby_fkey FOREIGN KEY (id_user)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT orders_assignedto_fkey FOREIGN KEY (id_keeper)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT orders_orderstype_fkey FOREIGN KEY (id_order_type)
        REFERENCES public.order_types (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

-- ALTER TABLE public.orders
--    OWNER to bmmnonpsiqroee;

-- Table: public.orders_products

DROP TABLE public.orders_products  CASCADE;

CREATE TABLE public.orders_products
(
    id_order bigint NOT NULL,
    id_product bigint NOT NULL,
    amount integer NOT NULL,
    amount_returned integer,
    CONSTRAINT orders_products_pkey PRIMARY KEY (id_order, id_product),
    CONSTRAINT orders_products_id_order_fkey FOREIGN KEY (id_order)
        REFERENCES public.orders (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT orders_products_id_product_fkey FOREIGN KEY (id_product)
        REFERENCES public.products (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

-- ALTER TABLE public.orders_products
--    OWNER to bmmnonpsiqroee;


INSERT INTO categories (name) VALUES ('Saws and Tile Cutters');
INSERT INTO categories (name) VALUES ('Generators');
INSERT INTO categories (name) VALUES ('Jacks');
INSERT INTO categories (name) VALUES ('Compressors');
INSERT INTO categories (name) VALUES ('Milling Cutters');
INSERT INTO categories (name) VALUES ('Engines');

INSERT INTO order_types (name) values ('clientorder');
INSERT INTO order_types (name) values ('stockorder');
INSERT INTO order_types (name) values ('returnorder');

INSERT INTO roles (name) values ('USER');
INSERT INTO roles (name) values ('KEEPER');
INSERT INTO roles (name) values ('ADMIN');

INSERT INTO users (email, password, name, surname, phone, active) values ('test1@gmail.com', 'password1',  'name1',  'surname1', '1234567', true );
INSERT INTO users (email, password, name, surname, phone, active) values ('test2@gmail.com', 'password2',  'name2',  'surname2', '1234567', true );
INSERT INTO users (email, password, name, surname, phone, active) values ('test3@gmail.com', 'password3',  'name3',  'surname3', '1234567', true );
INSERT INTO users (email, password, name, surname, phone, active) values ('test4@gmail.com', 'password4',  'name4',  'surname4', '1234567', true );
INSERT INTO users (email, password, name, surname, phone, active) values ('whilerun@moto.sport', '$2a$10$US44s.jOYHvE9RbjBgWUEe2.WnvWBypc2loLndbg3pF31JKloADFq',  'Caly',  'Referer', '+1010101010101', true );
INSERT INTO users (email, password, name, surname, phone, active) values ('whilerun2@moto.sport', '$$2a$10$hZfTR4Lb793goNULHq90/ufKJNgSEi3uU.KEXQ3fui98kXkRiBwGy',  'Caly',  'Referer', '+1010101010101', true );
INSERT INTO users (email, password, name, surname, phone, active) values ('valera@gmail.com', '$2a$10$zHU2Bcnx5BOnlnhS/xe.4uoDbM88WMnluTyOvlLLsmPMiwcydyGqW',  'vasa',  'vasa', '380984211057', true );
INSERT INTO users (email, password, name, surname, phone, active) values ('valera@gmial.ocm', '$2a$10$0Ld6uIwIhKZ/BOxNwOCiOuVfFZjomxEdOanFBFz0OFZe3GilJPnXm',  'vvv',  'vvvv', '111', true );
INSERT INTO users (email, password, name, surname, phone, active) values ('1valera@gmail.com', '$2a$10$NWtPXSCfvJ.XOhh7YNaAHOTH4SWp6WVjcz9dvMDnRFQNupUdr1Afu',  'vvv',  'vvvv', '111', true );
INSERT INTO users (email, password, name, surname, phone, active) values ('sdasdsa@dsdsffdsds.com', '$2a$10$npRJxmKo998XqyzR99EbsevF7Q.WmGfCOR43OYE9.2efyr0lWoEs2',  'й',  'й', '2222222', true );
INSERT INTO users (email, password, name, surname, phone, active) values ('www@www.com', '$2a$10$yT4/HY/EFRWEm57FIsOb7.pTLu0pMQnBTbIUdbnFRmvhpqV5tRHMi',  'w',  'w', '222222', true );
INSERT INTO users (email, password, name, surname, phone, active) values ('zzzz@zzzz.zz', '$2a$10$m2Z0y7i9Wpm80x/5V1jhMexPzmvdnjids4wTe8YWbx.6E5nt8WfEC',  'zzzz',  'zzzz', '380631234567', true );
INSERT INTO users (email, password, name, surname, phone, active) values ('sdsada@sdsdsadsdsd.cdd', '$2a$10$IGvKSPOJrgxB3tl2ln/Q8OZol/iAjxylM7cmk.98ik4MHun15V1pG',  '2www',  'wwww', '22222222', true );
INSERT INTO users (email, password, name, surname, phone, active) values ('qqq@www.www', '$2a$10$eoD.pBdECKbyCGMhx1/iFOANa/V5N7bMDDW6K3Y.osK/iQ4us9qai',  'ww',  'ww', '2222', true );
INSERT INTO users (email, password, name, surname, phone, active) values ('REStFULl@REStFULl.REStFULl', '$2a$10$ayk0RUJEei.nxGSdnP5INecUfZzbfE4/1BSXXGAPWR3wTmqO3e09e',  'REStFULl',  'REStFULl', '222222', true );
INSERT INTO users (email, password, name, surname, phone, active) values ('REStFULl2@REStFULl.REStFULl', '$2a$10$/5Ag1nDKhfQ1.KJVsz3t/OsUWR2mclWVdaENAbngHwdCtfRCVlE5e',  'REStFULlREStFULl',  'REStFULlREStFULl', '2', true );
INSERT INTO users (email, password, name, surname, phone, active) values ('yarodskf@gmaodfijas.com', '$2a$10$09vtv09Ud6rw.mUjjf1IaOChX1ANR.6tfMdapE5Fw7v5mlqWFFr92',  'zzz1',  'zzz1', '12389404', true );
INSERT INTO users (email, password, name, surname, phone, active) values ('pop@gmail.com', '$2a$10$U3W6rva5EbAd89PpjFEvn.8wBmVcdoi4ZwudViovyuSV4RVrFqExW',  'qwerqew',  'qwerqwqwrqw', '3456543221', true );
INSERT INTO users (email, password, name, surname, phone, active) values ('vitalii@nc.com', '$2a$10$GzmQ.dlJPjs2v4qqjvG3F.tfC.eH1a0pz1nbQeZEolskkyo86f0eC',  'VItalii',  'Test', '380975330171', true );
INSERT INTO users (email, password, name, surname, phone, active) values ('whileqrun2@moto.sport', '$2a$10$Q7YY.vbsG4dtEKJ52.XcZuZ2XgUxbszQvT6KD86TR3dXMFLAKk7PW',  'Caly',  'Referer', '+1010101010101', true );
INSERT INTO users (email, password, name, surname, phone, active) values ('whilerqrun2@moto.sport', '$2a$10$J3BGocXUWYRxlo4knZVREeWsAGOzcplS1hEn/aq3YREnmmExEj1nG',  'Caly',  'Referer', '+1010101010101', true );
INSERT INTO users (email, password, name, surname, phone, active) values ('675kostya@gmail.com', '$2a$10$lnrcIW..VN39vu2XVRA4lOUKHSZmYrTflkns91uxAzK6Thotvt3yW',  '675kostya',  '675kostya', '+1010101010101', true );
INSERT INTO users (email, password, name, surname, phone, active) values ('asada@asada.asada', '$2a$10$8WFn59V8NclrFElXg2b1l.L4cX2WvgTR9.sDGyqmdMbyzT/RV.xXC',  'фівфів',  'asada', '222', true );
INSERT INTO users (email, password, name, surname, phone, active) values ('3valera@gmail.com', '$2a$10$dD6i/SStJtpJaJx7JsGPfuKxKr1Dqy7jJhGTYQi1paUgp7CjeTe6m',  'vvvv',  'vvvv', '111', true );
INSERT INTO users (email, password, name, surname, phone, active) values ('4valera@gmail.com', '$2a$10$4//ST2nMIiPRbdfjUPG2ye44joS/9pw/YZpgUEXYlFdgIUgY8c5km',  'vvv',  'vvvv', '111', true );

INSERT INTO users_roles (id_user, id_role) values (1,1);
INSERT INTO users_roles (id_user, id_role) values (2,2);
INSERT INTO users_roles (id_user, id_role) values (3,3);
INSERT INTO users_roles (id_user, id_role) values (4,1);
INSERT INTO users_roles (id_user, id_role) values (5,1);
INSERT INTO users_roles (id_user, id_role) values (6,1);
INSERT INTO users_roles (id_user, id_role) values (7,1);
INSERT INTO users_roles (id_user, id_role) values (8,1);
INSERT INTO users_roles (id_user, id_role) values (9,1);
INSERT INTO users_roles (id_user, id_role) values (10,1);
INSERT INTO users_roles (id_user, id_role) values (11,1);
INSERT INTO users_roles (id_user, id_role) values (12,1);
INSERT INTO users_roles (id_user, id_role) values (13,1);
INSERT INTO users_roles (id_user, id_role) values (14,1);
INSERT INTO users_roles (id_user, id_role) values (15,1);
INSERT INTO users_roles (id_user, id_role) values (16,1);
INSERT INTO users_roles (id_user, id_role) values (17,1);
INSERT INTO users_roles (id_user, id_role) values (18,1);
INSERT INTO users_roles (id_user, id_role) values (19,1);
INSERT INTO users_roles (id_user, id_role) values (20,1);
INSERT INTO users_roles (id_user, id_role) values (21,1);
INSERT INTO users_roles (id_user, id_role) values (22,1);
INSERT INTO users_roles (id_user, id_role) values (23,1);
INSERT INTO users_roles (id_user, id_role) values (24,1);
INSERT INTO users_roles (id_user, id_role) values (25,1);

INSERT INTO products (name, amount, id_category, active) values ('Saw circular Daewoo DAS 1500-190', 2, 1, true);
INSERT INTO products (name, amount, id_category, active) values ('Saw miter Metabo KS 216M', 3, 1, true);
INSERT INTO products (name, amount, id_category, active) values ('Saw universal Black + Decker KS880EC', 1, 1, true);
INSERT INTO products (name, amount, id_category, active) values ('Generator petrol Daewoo GDA 3500', 2, 2, true);
INSERT INTO products (name, amount, id_category, active) values ('Gasoline Gas Generator Forte FG LPG 6500E', 3, 2, true);
INSERT INTO products (name, amount, id_category, active) values ('Saw circular Bosch Professional GKS 190', 5, 1, true);
INSERT INTO products (name, amount, id_category, active) values ('Saw miter Metabo KGS 254 M', 8, 1, true);
INSERT INTO products (name, amount, id_category, active) values ('Reciprocating saw Metabo SSE 1100', 0, 1, true);
INSERT INTO products (name, amount, id_category, active) values ('Diesel generator Iron Angel EGD 5000 CLE', 11, 2, true);
INSERT INTO products (name, amount, id_category, active) values ('Jack Hi-Lift Jack X-Treme 1.2 m', 5, 3, true);
INSERT INTO products (name, amount, id_category, active) values ('Compressor Forte ZA 65-100', 7, 4, true);
INSERT INTO products (name, amount, id_category, active) values ('Milling cutter Makita RP2300FCX', 9, 5, true);
INSERT INTO products (name, amount, id_category, active) values ('Jack Torin 10t', 0, 3, false);
INSERT INTO products (name, amount, id_category, active) values ('Fraser Einhell - TC-BJ 900 Classic', 2, 5, false);
INSERT INTO products (name, description, amount, id_category, active) values ('Dell', 'Powerful', 1, 3, true );

INSERT INTO orders (id_parent, order_statuses, id_order_type, created, changed, annotation, archived, id_user, id_keeper ) values (1, 1, 'READY', '2018-02-11 08:35:00.139', '2018-02-11 08:35:00.139', 'nothing', false, 2, 3);
INSERT INTO orders (id_parent, order_statuses, id_order_type, created, changed, annotation, archived, id_user, id_keeper ) values (1, 2, 'OPENED', '2018-02-11 08:36:00.139', '2018-02-11 08:36:00.139', 'nothing', false, 2, 3);
INSERT INTO orders (id_parent, order_statuses, id_order_type, created, changed, annotation, archived, id_user, id_keeper ) values (3, 1, 'OPENED', '2018-02-11 10:59:00.139', '2018-02-11 08:35:00.139', 'something', false, 1, 1);
INSERT INTO orders (id_parent, order_statuses, id_order_type, created, changed, annotation, archived, id_user, id_keeper ) values (1, 2, 'OPENED', '2018-11-11 18:24:17.119', '2018-11-11 18:24:17.119', 'yea', false, 2, 1);
INSERT INTO orders (id_parent, order_statuses, id_order_type, created, changed, annotation, archived, id_user, id_keeper ) values (1, 3, 'OPENED', '2018-11-11 19:36:51.3', '2018-11-11 19:36:51.3', 'Hel Yea', false, 1, 1);
INSERT INTO orders (id_parent, order_statuses, id_order_type, created, changed, annotation, archived, id_user, id_keeper ) values (1, 1, 'PROCESSING', '2018-11-11 19:45:08.632', '2018-11-11 19:45:08.632', 'for user 1', false, 2, 1);
INSERT INTO orders (id_parent,order_statuses,  id_order_type, created, changed, annotation, archived, id_user, id_keeper ) values (2, 1, 'CLOSED', '2018-11-12 18:42:35.541', '2018-11-12 18:42:35.541', 'for user 1', false, 2, 1);

INSERT INTO orders_products ( id_order, id_product, amount) values (1, 2, 1);

