CREATE TABLE "public"."users"
(
    "id"              varchar(255) NOT NULL,
    "created_at"      timestamptz,
    "email"           varchar(255),
    "last_name"       varchar(255),
    "name"            varchar(255),
    "organization_id" int8         NOT NULL,
    "phone"           varchar(255),
    "status"          varchar(255),
    "updated_at"      timestamptz,
    PRIMARY KEY ("id")
);

CREATE TABLE "public"."users_scopes"
(
    "users_id" varchar(255) NOT NULL,
    "scopes"   varchar(255),
    CONSTRAINT "fkmxf6h79yi5w2teahmjxrxp5in" FOREIGN KEY ("users_id") REFERENCES "public"."users" ("id")
);