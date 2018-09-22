--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.6
-- Dumped by pg_dump version 10.3

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: message; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.message (
    id bigint NOT NULL,
    message character varying(2044) NOT NULL
);


ALTER TABLE public.message OWNER TO postgres;

--
-- Name: message_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.message_seq
    START WITH 0
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 99999999
    CACHE 10;


ALTER TABLE public.message_seq OWNER TO postgres;

--
-- Name: person; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.person (
    id bigint NOT NULL,
    first_name character varying(32) NOT NULL,
    last_name character varying(32) NOT NULL
);


ALTER TABLE public.person OWNER TO postgres;

--
-- Name: person_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.person_id_seq
    START WITH 0
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 99999999
    CACHE 10;


ALTER TABLE public.person_id_seq OWNER TO postgres;

--
-- Name: skill_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.skill_id_seq
    START WITH 0
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 99999999
    CACHE 10;


ALTER TABLE public.skill_id_seq OWNER TO postgres;

--
-- Name: skills; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.skills (
    id bigint NOT NULL,
    name character varying(100) NOT NULL,
    level character varying(16) NOT NULL,
    person_id bigint NOT NULL
);


ALTER TABLE public.skills OWNER TO postgres;

--
-- Name: message message_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.message
    ADD CONSTRAINT message_pkey PRIMARY KEY (id);


--
-- Name: person person_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person
    ADD CONSTRAINT person_pkey PRIMARY KEY (id);


--
-- Name: skills skills_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.skills
    ADD CONSTRAINT skills_pkey PRIMARY KEY (id);


--
-- Name: skills skills_person_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.skills
    ADD CONSTRAINT skills_person_id_fkey FOREIGN KEY (person_id) REFERENCES public.person(id);


--
-- PostgreSQL database dump complete
--

