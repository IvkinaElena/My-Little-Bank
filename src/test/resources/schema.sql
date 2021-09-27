CREATE TABLE customers
(
    id bigserial,
    firstname text NOT NULL,
    lastname text NOT NULL,
    middlename text NOT NULL,
    CONSTRAINT "customers_pkey" PRIMARY KEY (id)
);

CREATE TABLE accounts
(
    id integer NOT NULL,
    number text,
    id_owner bigint,
    balance integer,
    CONSTRAINT accounts_pkey PRIMARY KEY (id),
    CONSTRAINT accounts_customers_fk FOREIGN KEY (id_owner)
        REFERENCES customers (id)
);

CREATE TABLE transactions
(
    id bigint NOT NULL,
    id_score integer,
    operation integer,
    created_at timestamp with time zone NOT NULL DEFAULT now(),
    CONSTRAINT transactions_pkey PRIMARY KEY (id),
    CONSTRAINT transactions_fk FOREIGN KEY (id_score)
        REFERENCES accounts (id)
)

