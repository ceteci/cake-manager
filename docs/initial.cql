CREATE KEYSPACE waracleSpace
    WITH replication = {'class':'SimpleStrategy', 'replication_factor' : 3};

CREATE TABLE waracleSpace.cakes
(
    id          text PRIMARY KEY,
    title       text,
    description text,
    image       text
);
