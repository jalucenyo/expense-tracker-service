CREATE TABLE budget
(
    id              UUID NOT NULL,
    name            VARCHAR(255),
    start_date      TIMESTAMP WITHOUT TIME ZONE,
    end_date        TIMESTAMP WITHOUT TIME ZONE,
    amount_value    BIGINT,
    amount_exponent INTEGER,
    CONSTRAINT pk_budget PRIMARY KEY (id)
);