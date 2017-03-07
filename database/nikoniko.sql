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
        login            Varchar (25) NOT NULL ,
        password         Varchar (25) NOT NULL ,
        sex              Varchar (1)  NOT NULL ,
        lastname         Varchar (25) NOT NULL ,
        firstname        Varchar (25) NOT NULL ,
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
<<<<<<< HEAD
        isanonymous      Bool NOT NULL ,
        id_user          Int ,
        id_project       Int ,
=======
        isanonymous     Bool ,
        id_Project       Int ,
        id_User          Int ,
>>>>>>> d0d524f638207240624c593e3d4365e1d2617dd9
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

<<<<<<< HEAD
ALTER TABLE nikoniko ADD CONSTRAINT FK_nikoniko_id_user FOREIGN KEY (id_user) REFERENCES user(id);
ALTER TABLE nikoniko ADD CONSTRAINT FK_nikoniko_id_project FOREIGN KEY (id_project) REFERENCES project(id);
ALTER TABLE user_team ADD CONSTRAINT FK_user_team_id FOREIGN KEY (id_user) REFERENCES user(id);
ALTER TABLE user_team ADD CONSTRAINT FK_user_team_id_team FOREIGN KEY (id_team) REFERENCES team(id);
ALTER TABLE team_project ADD CONSTRAINT FK_team_project_id FOREIGN KEY (id_team) REFERENCES team(id);
ALTER TABLE team_project ADD CONSTRAINT FK_team_project_id_project FOREIGN KEY (id_project) REFERENCES project(id);
=======
ALTER TABLE nikoniko ADD CONSTRAINT FK_nikoniko_id_Project FOREIGN KEY (id_Project) REFERENCES project(id);
ALTER TABLE nikoniko ADD CONSTRAINT FK_nikoniko_id_User FOREIGN KEY (id_User) REFERENCES user(id);
ALTER TABLE user_team ADD CONSTRAINT FK_user_team_id_User FOREIGN KEY (id_User) REFERENCES user(id);
ALTER TABLE user_team ADD CONSTRAINT FK_user_team_id_Team FOREIGN KEY (id_Team) REFERENCES team(id);
ALTER TABLE team_project ADD CONSTRAINT FK_team_project_id_Team FOREIGN KEY (id_Team) REFERENCES team(id);
ALTER TABLE team_project ADD CONSTRAINT FK_team_project_id_Project FOREIGN KEY (id_Project) REFERENCES project(id);
>>>>>>> d0d524f638207240624c593e3d4365e1d2617dd9
