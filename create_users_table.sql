CREATE SEQUENCE IF NOT EXISTS users_id_seq;
CREATE OR REPLACE FUNCTION next_user_id(OUT result bigint) AS
$$
DECLARE
    our_epoch   bigint := 1587230000;
    seq_id      bigint;
    now_seconds bigint;
BEGIN
    SELECT nextval('users_id_seq') % 1024 INTO seq_id;
    SELECT FLOOR(EXTRACT(EPOCH FROM clock_timestamp())) INTO now_seconds;
    result := (now_seconds - our_epoch) << 10;
    result := result | (seq_id);
END
$$ LANGUAGE PLPGSQL;

CREATE TABLE users (
	user_id BIGINT NOT NULL DEFAULT next_user_id() PRIMARY KEY,
	resource_type VARCHAR(20) NOT NULL,
	resource_verified BOOLEAN NOT NULL,
	work_location VARCHAR(10) NOT NULL
);