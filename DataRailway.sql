CREATE SCHEMA railway;
USE railway;

CREATE  TABLE railway.profil_utilisateur ( 
	id                   INT  NOT NULL PRIMARY KEY,
	nom                  VARCHAR(50)  NOT NULL     
 ) engine=InnoDB;

CREATE  TABLE railway.users ( 
	id                   INT  NOT NULL   AUTO_INCREMENT  PRIMARY KEY,
	idprofile            INT  NOT NULL     ,
	nom                  VARCHAR(50)  NOT NULL     ,
	mdp                  VARCHAR(50)  NOT NULL     ,
	CONSTRAINT fk_users_profil_utilisateur FOREIGN KEY ( idprofile ) REFERENCES railway.profil_utilisateur( id ) ON DELETE CASCADE ON UPDATE CASCADE
 ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE  TABLE railway.vehicule ( 
	id                   INT  NOT NULL   AUTO_INCREMENT  PRIMARY KEY,
	mtr                  VARCHAR(8)  NOT NULL     ,
	nom                  VARCHAR(50)  NOT NULL     
 ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE  TABLE railway.kilometrage ( 
	id                   INT  NOT NULL   AUTO_INCREMENT  PRIMARY KEY,
	idvehicule           INT  NOT NULL     ,
	date_                DATE  NOT NULL DEFAULT (curdate())    ,
	debut_km             DOUBLE  NOT NULL     ,
	fin_km               DOUBLE       ,
	CONSTRAINT fk_kilometrage_vehicule FOREIGN KEY ( idvehicule ) REFERENCES railway.vehicule( id ) ON DELETE CASCADE ON UPDATE CASCADE
 ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE INDEX fk_kilometrage_vehicule ON railway.kilometrage ( idvehicule );

CREATE  TABLE railway.token ( 
	id                   INT  NOT NULL   AUTO_INCREMENT  PRIMARY KEY,
	idusers              INT  NOT NULL     ,
	token                VARCHAR(255)  NOT NULL     ,
	dtexp                DATE  NOT NULL DEFAULT (curdate() + interval 1 month)    ,
	isvalidate           TINYINT  NOT NULL DEFAULT (1)    ,
	CONSTRAINT fk_token_users FOREIGN KEY ( idusers ) REFERENCES railway.users( id ) ON DELETE CASCADE ON UPDATE CASCADE
 ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE INDEX fk_token_users ON railway.token ( idusers );

INSERT INTO profil_utilisateur (id , nom) VALUES 
	(1 , "admin"),
	(2 , "user");

INSERT INTO users (idprofile , nom , mdp) VALUES 
	(1 , "johan", "jojo"),
	(2 , "logan", "lolo"),
	(2 , "manoa", "mama");

INSERT INTO railway.vehicule ( id, mtr, nom) VALUES 
	( DEFAULT, '1234 TBB', 'PORSCHE' ),
	( DEFAULT, '1234 TAR', 'MClaren' ),
	( DEFAULT, '1234 TR' , 'LAMBORGHINI' );

INSERT INTO railway.kilometrage (id ,idvehicule,date_ ,debut_km ,fin_km) VALUES  
	( DEFAULT, 1, '2023-11-09', 100, 200),
	( DEFAULT, 2, '2023-11-09', 300, 450),
	( DEFAULT, 1, '2023-11-09', 500, 600),
	( DEFAULT, 1, '2023-11-09', 700, 850);
