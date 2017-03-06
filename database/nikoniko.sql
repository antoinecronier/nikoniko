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
        id               int (11) Auto_increment  NOT NULL ,
        login            Varchar (25) ,
        password         Varchar (25) ,
        sex              Char (25) NOT NULL ,
        firstname        Varchar (25) NOT NULL ,
        lastname         Varchar (25) NOT NULL ,
        registration_cgi Varchar (25) NOT NULL ,
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
        log_Date         Date NOT NULL ,
        change_Date      Date ,
        satisfaction     Int NOT NULL ,
        nikoniko_comment Text ,
        isanonymous     Bool ,
        id_Project       Int ,
        id_User          Int ,
        PRIMARY KEY (id )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: project
#------------------------------------------------------------

CREATE TABLE project(
        id         int (11) Auto_increment  NOT NULL ,
        name       Varchar (25) NOT NULL ,
        start_Date Date ,
        end_Date   Date ,
        PRIMARY KEY (id )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: user_team
#------------------------------------------------------------

CREATE TABLE user_team(
        id_User Int NOT NULL ,
        id_Team Int NOT NULL ,
        PRIMARY KEY (id_User ,id_Team )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: team_project
#------------------------------------------------------------

CREATE TABLE team_project(
        id_Team    Int NOT NULL ,
        id_Project Int NOT NULL ,
        PRIMARY KEY (id_Team ,id_Project )
)ENGINE=InnoDB;

ALTER TABLE nikoniko ADD CONSTRAINT FK_nikoniko_id_Project FOREIGN KEY (id_Project) REFERENCES project(id);
ALTER TABLE nikoniko ADD CONSTRAINT FK_nikoniko_id_User FOREIGN KEY (id_User) REFERENCES user(id);
ALTER TABLE user_team ADD CONSTRAINT FK_user_team_id_User FOREIGN KEY (id_User) REFERENCES user(id);
ALTER TABLE user_team ADD CONSTRAINT FK_user_team_id_Team FOREIGN KEY (id_Team) REFERENCES team(id);
ALTER TABLE team_project ADD CONSTRAINT FK_team_project_id_Team FOREIGN KEY (id_Team) REFERENCES team(id);
ALTER TABLE team_project ADD CONSTRAINT FK_team_project_id_Project FOREIGN KEY (id_Project) REFERENCES project(id);
