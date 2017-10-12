CREATE TABLE "room" (
    "id" bigint NOT NULL,
    "name" character varying(255) NOT NULL,
    constraint "room_pkey" primary key("id"),
    constraint "uidx_room_name" UNIQUE ("name")
);
