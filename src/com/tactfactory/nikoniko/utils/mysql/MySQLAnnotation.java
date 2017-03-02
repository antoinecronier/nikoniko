package com.tactfactory.nikoniko.utils.mysql;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MySQLAnnotation {
	String fieldName() default "";
	MySQLTypes mysqlType() default MySQLTypes.NONE ;
	boolean nullable() default false;
	String associationTable() default "";
}