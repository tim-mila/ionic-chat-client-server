CREATE TABLE "room" (
    "id" bigint NOT NULL,
    "name"character varying(255) NOT NULL,
    constraint "room_pkey" primary key("id")
);

CREATE INDEX idx_room_name on "room" ("name");