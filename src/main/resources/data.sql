use PROJECT_MANAGEMENT;

CREATE TABLE user (
    id_user BIGINT auto_increment NOT NULL,
    nm_user_first varchar(100) NULL,
    nm_user_last varchar(100) NULL,
    password varchar(100) NOT NULL,
    ds_username varchar(100) NOT NULL,
    ds_roles varchar(250) NOT NULL,
    dt_creation DATETIME DEFAULT now() NOT NULL,
    CONSTRAINT USER_PK PRIMARY KEY (id_user)
)