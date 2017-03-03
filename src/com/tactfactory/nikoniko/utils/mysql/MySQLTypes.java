package com.tactfactory.nikoniko.utils.mysql;

//enumeration de tous les types d'attributs contenus dans la BDD
//association étant un type spécifique au renseignement des enfants

public enum MySQLTypes {
	NONE,
	VARCHAR,
	INT,
	TINYINT,
	DATETIME,
	TEXT,
	DATABASE_ITEM,
	ASSOCIATION
}
