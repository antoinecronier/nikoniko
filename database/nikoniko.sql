#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------

DROP DATABASE IF EXISTS nikoniko;

CREATE DATABASE nikoniko;

USE nikoniko;

#------------------------------------------------------------
# Table: User
#------------------------------------------------------------

CREATE TABLE User(
        id               int (11) Auto_increment  NOT NULL ,
        login            Varchar (25) NOT NULL ,
        password         Varchar (25) NOT NULL ,
        lastname         Varchar (25) NOT NULL ,
        firstname        Varchar (25) NOT NULL ,
        registration_cgi Varchar (25) NOT NULL ,
        PRIMARY KEY (id )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Team
#------------------------------------------------------------

CREATE TABLE Team(
        id     int (11) Auto_increment  NOT NULL ,
        name   Varchar (25) NOT NULL ,
        serial Varchar (25) ,
        PRIMARY KEY (id )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: NikoNiko
#------------------------------------------------------------

CREATE TABLE NikoNiko(
        id               int (11) Auto_increment  NOT NULL ,
        log_Date     Datetime NOT NULL ,
        change_Date      Datetime ,
        satisfaction     Int NOT NULL ,
        nikoniko_comment Text ,
        isanonymous      Bool NOT NULL ,
        id_User          Int ,
        id_Project       Int ,
        PRIMARY KEY (id )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Project
#------------------------------------------------------------

CREATE TABLE Project(
        id         int (11) Auto_increment  NOT NULL ,
        name       Varchar (25) NOT NULL ,
        start_Date Datetime ,
        end_Date   Datetime ,
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

ALTER TABLE NikoNiko ADD CONSTRAINT FK_NikoNiko_id_User FOREIGN KEY (id_User) REFERENCES User(id);
ALTER TABLE NikoNiko ADD CONSTRAINT FK_NikoNiko_id_Project FOREIGN KEY (id_Project) REFERENCES Project(id);
ALTER TABLE user_team ADD CONSTRAINT FK_user_team_id FOREIGN KEY (id_User) REFERENCES User(id);
ALTER TABLE user_team ADD CONSTRAINT FK_user_team_id_Team FOREIGN KEY (id_Team) REFERENCES Team(id);
ALTER TABLE team_project ADD CONSTRAINT FK_team_project_id FOREIGN KEY (id_Team) REFERENCES Team(id);
ALTER TABLE team_project ADD CONSTRAINT FK_team_project_id_Project FOREIGN KEY (id_Project) REFERENCES Project(id);
