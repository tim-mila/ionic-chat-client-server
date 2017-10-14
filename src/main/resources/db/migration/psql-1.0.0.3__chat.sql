create table "chat" (
    "id" bigint not null,
    "at" timestamp without time zone not null,
    "room_id" bigint references "room"("id"),
    "username" character varying(255),
    "text" text,
    constraint "chat_pkey" primary key("id")
);
