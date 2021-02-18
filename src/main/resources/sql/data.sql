use PROJECT_MANAGEMENT;

CREATE TABLE user (
    id_user BIGINT auto_increment NOT NULL,
    nm_user_first varchar(100) NULL,
    nm_user_last varchar(100) NULL,
    password varchar(100) NOT NULL,
    ds_username varchar(100) NOT NULL,
    ds_roles varchar(250) NOT NULL,
    dt_creation DATETIME DEFAULT now() NOT NULL,
    CONSTRAINT user_PK PRIMARY KEY (id_user)
);

CREATE TABLE project (
    id_project BIGINT auto_increment NOT NULL,
    ds_project varchar(100) NOT NULL,
    nm_client varchar(100) NOT NULL,
    dt_creation DATETIME DEFAULT now() NOT NULL,
    dt_start DATETIME NULL,
    dt_expected_completion DATETIME NOT NULL,
    dt_real_completion DATETIME NULL,
CONSTRAINT project_PK PRIMARY KEY (id_project)
);

CREATE TABLE project_user (
    fk_project BIGINT NOT NULL,
    fk_user BIGINT NOT NULL,
    CONSTRAINT user_project_FK FOREIGN KEY (fk_user) REFERENCES PROJECT_MANAGEMENT.user(id_user) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT project_user_FK FOREIGN KEY (fk_project) REFERENCES PROJECT_MANAGEMENT.project(id_project) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE activity (
    id_activity BIGINT auto_increment NOT NULL,
    ds_activity varchar(100) NOT NULL,
    ds_details varchar(250) NULL,
    dt_start DATETIME NOT NULL,
    dt_end DATETIME NOT NULL,
    fk_user BIGINT NOT NULL,
    fk_project BIGINT NOT NULL,
    CONSTRAINT activity_PK PRIMARY KEY (id_activity),
    CONSTRAINT user_activity_FK FOREIGN KEY (fk_project) REFERENCES PROJECT_MANAGEMENT.`user`(id_user),
    CONSTRAINT project_activity_FK FOREIGN KEY (fk_project) REFERENCES PROJECT_MANAGEMENT.project(id_project) ON DELETE CASCADE ON UPDATE CASCADE
);