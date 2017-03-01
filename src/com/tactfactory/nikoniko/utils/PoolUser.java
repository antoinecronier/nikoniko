package com.tactfactory.nikoniko.utils;

import com.tactfactory.nikoniko.models.*;
import java.util.ArrayList;
import java.lang.*;


public class PoolUser {
	ArrayList<User> list;

	
	public ArrayList<User> getList() {
		return list;
	}
	public void setList(ArrayList<User> list) {
		this.list = list;
	}

	public PoolUser(int size) {
		
		this.list = new ArrayList<User>();
		
		FromFile femaleName;
		femaleName = new FromFile("/home/jerry/work/poei-projet/generation/prenom_feminin.txt");
	
		FromFile maleName;
		maleName = new FromFile("/home/jerry/work/poei-projet/generation/prenom_masculin.txt");
	
		FromFile familyName;
		familyName = new FromFile("/home/jerry/work/poei-projet/generation/nom_de_famille1.txt");
	
		for(int i=0;i<size;i++) {
			String firstname;
			String lastname;
			char sex;
			int value;
			
			value = (int) (Math.floor(Math.random()*2.0+1.0));
			if(value==1) {
				sex = 'M';
				firstname = maleName.getList().get(
					(int) ((Math.random()*maleName.getList().size())+1));
			}
			else if(value==2){
				sex = 'F';
				firstname = femaleName.getList().get(
						(int) ((Math.random()*femaleName.getList().size())+1));
			}
			else {
				sex = 'I';
				firstname = maleName.getList().get(
						(int) ((Math.random()*maleName.getList().size())+1));
			
			}
			lastname = familyName.getList().get(
					(int) ((Math.random()*familyName.getList().size())+1));
		
			User user = new User(firstname,lastname,sex);
			list.add(user);
		
		}
	}
}