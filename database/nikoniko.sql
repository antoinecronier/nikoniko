#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------

DROP DATABASE IF EXISTS nikoniko;

CREATE DATABASE nikoniko;

USE nikoniko;

#------------------------------------------------------------
# Table: user
#------------------------------------------------------------

CREATE TABLE user(
        id        int (11) Auto_increment  NOT NULL ,
        firstname Varchar (25) NOT NULL ,
        lastname  Varchar (25) NOT NULL ,
        login     Varchar (25) ,
        password  Varchar (25) ,
        sex       Char (25) NOT NULL ,
        PRIMARY KEY (id )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: team
#------------------------------------------------------------

CREATE TABLE team(
        id     int (11) Auto_increment  NOT NULL ,
        name   Varchar (25) ,
        serial Int ,
        PRIMARY KEY (id )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: nikoniko
#------------------------------------------------------------

CREATE TABLE nikoniko(
        id               int (11) Auto_increment  NOT NULL ,
        log_date         Date NOT NULL ,
        change_date      Date ,
        satisfaction     Int NOT NULL ,
        nikoniko_comment Text ,
        is_annonymous    Bool ,
        id_project       Int NOT NULL ,
        id_user          Int NOT NULL ,
        PRIMARY KEY (id )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: project
#------------------------------------------------------------

CREATE TABLE project(
        id         int (11) Auto_increment  NOT NULL ,
        name       Varchar (25) NOT NULL ,
        start_date Date ,
        end_date   Date ,
        PRIMARY KEY (id )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: user_team
#------------------------------------------------------------

CREATE TABLE user_team(
        id      Int NOT NULL ,
        id_team Int NOT NULL ,
        PRIMARY KEY (id ,id_team )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: team_project
#------------------------------------------------------------

CREATE TABLE team_project(
        id         Int NOT NULL ,
        id_project Int NOT NULL ,
        PRIMARY KEY (id ,id_project )
)ENGINE=InnoDB;

ALTER TABLE nikoniko ADD CONSTRAINT FK_nikoniko_id_project FOREIGN KEY (id_project) REFERENCES project(id);
ALTER TABLE nikoniko ADD CONSTRAINT FK_nikoniko_id_user FOREIGN KEY (id_user) REFERENCES user(id);
ALTER TABLE user_team ADD CONSTRAINT FK_user_team_id FOREIGN KEY (id) REFERENCES user(id);
ALTER TABLE user_team ADD CONSTRAINT FK_user_team_id_team FOREIGN KEY (id_team) REFERENCES team(id);
ALTER TABLE team_project ADD CONSTRAINT FK_team_project_id FOREIGN KEY (id) REFERENCES team(id);
ALTER TABLE team_project ADD CONSTRAINT FK_team_project_id_project FOREIGN KEY (id_project) REFERENCES project(id);
