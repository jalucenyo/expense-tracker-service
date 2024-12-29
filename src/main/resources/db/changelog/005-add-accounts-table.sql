CREATE TABLE accounts
(
    id              UUID NOT NULL,
    version         INTEGER,
    email           VARCHAR(255),
    password        VARCHAR(255),
    CONSTRAINT pk_users PRIMARY KEY (id)
);
