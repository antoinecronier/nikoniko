#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------

DROP DATABASE IF EXISTS nikoniko;

CREATE DATABASE nikoniko;

USE nikoniko;

#------------------------------------------------------------
# Table: User
#------------------------------------------------------------

CREATE TABLE user(
        id               int (11) Auto_increment  NOT NULL ,
        login            Varchar (25) NOT NULL ,
        password         Varchar (25) NOT NULL ,
<<<<<<< HEAD
<<<<<<< HEAD
	sex		 varchar (1) NOT NULL,
=======
        sex              Varchar (1)  NOT NULL ,
>>>>>>> master
=======
        sex				 Varchar (1)  NOT NULL ,
=======
        sex              Varchar (1)  NOT NULL ,
>>>>>>> dcdc0b264371b0615372b9e70218c44665820c21
>>>>>>> master
        lastname         Varchar (25) NOT NULL ,
        firstname        Varchar (25) NOT NULL ,
        registration_cgi Varchar (25) NOT NULL ,
        PRIMARY KEY (id )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Team
#------------------------------------------------------------

CREATE TABLE team(
        id     int (11) Auto_increment  NOT NULL ,
        name   Varchar (25) NOT NULL ,
        serial Varchar (25) ,
        PRIMARY KEY (id )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: NikoNiko
#------------------------------------------------------------

CREATE TABLE nikoniko(
        id               int (11) Auto_increment  NOT NULL ,
        log_Date     Datetime NOT NULL ,
        change_Date      Datetime ,
        satisfaction     Int NOT NULL ,
        nikoniko_comment Text ,
        isanonymous      Bool NOT NULL ,
        id_user          Int ,
        id_project       Int ,
        PRIMARY KEY (id )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Project
#------------------------------------------------------------

CREATE TABLE project(
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
        id_user Int NOT NULL ,
        id_team Int NOT NULL ,
        PRIMARY KEY (id_user ,id_team )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: team_project
#------------------------------------------------------------

CREATE TABLE team_project(
        id_team    Int NOT NULL ,
        id_project Int NOT NULL ,
        PRIMARY KEY (id_team ,id_project )
)ENGINE=InnoDB;

ALTER TABLE nikoniko ADD CONSTRAINT FK_nikoniko_id_user FOREIGN KEY (id_user) REFERENCES user(id);
ALTER TABLE nikoniko ADD CONSTRAINT FK_nikoniko_id_project FOREIGN KEY (id_project) REFERENCES project(id);
ALTER TABLE user_team ADD CONSTRAINT FK_user_team_id FOREIGN KEY (id_user) REFERENCES user(id);
ALTER TABLE user_team ADD CONSTRAINT FK_user_team_id_team FOREIGN KEY (id_team) REFERENCES team(id);
ALTER TABLE team_project ADD CONSTRAINT FK_team_project_id FOREIGN KEY (id_team) REFERENCES team(id);
ALTER TABLE team_project ADD CONSTRAINT FK_team_project_id_project FOREIGN KEY (id_project) REFERENCES project(id);
