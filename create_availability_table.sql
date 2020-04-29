CREATE TABLE unavailabilities (
	user_id BIGINT NOT NULL,
	start_date INT NOT NULL,
	start_month INT NOT NULL,
	start_year INT NOT NULL,
	reason VARCHAR(20) NOT NULL,
	duration_days VARCHAR(10) NOT NULL
);