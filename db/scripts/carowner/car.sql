create table Car
(
    id        serial primary key,
    name      text,
    engine_id int not null unique references engine(id)
)