create table if not exists transaction
(
    id uuid default random_uuid() primary key,
    username    varchar(255)   NOT NULL,
    amount      DECIMAL(12, 2) NOT NULL,
    `timestamp` timestamp      NOT NULL,
    reference   varchar(255),
    slogan      varchar(255)
);