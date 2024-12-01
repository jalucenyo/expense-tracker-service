CREATE TABLE expense
(
    id               UUID NOT NULL,
    version          INTEGER,
    description      VARCHAR(255),
    transaction_date TIMESTAMP WITHOUT TIME ZONE,
    payment_method   VARCHAR(50),
    vendor           VARCHAR(255),
    recurrence       VARCHAR(50),
    notes            VARCHAR(255),
    amount_value     BIGINT,
    amount_exponent  INTEGER,
    CONSTRAINT pk_expense PRIMARY KEY (id)
);

CREATE TABLE budget
(
    id              UUID NOT NULL,
    version         INTEGER,
    name            VARCHAR(255),
    start_date      TIMESTAMP WITHOUT TIME ZONE,
    end_date        TIMESTAMP WITHOUT TIME ZONE,
    amount_value    BIGINT,
    amount_exponent INTEGER,
    CONSTRAINT pk_budget PRIMARY KEY (id)
);
