package com.tactfactory.nikoniko;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.tactfactory.nikoniko.manager.NikoNikoManager;
import com.tactfactory.nikoniko.models.*;

public class Application {

	public static void main(String[] args) {
		User u1 = new User();
		User u2 = new User();

		Team t1 = new Team();
		u1.getTeams().add(t1);
		u2.getTeams().add(t1);
		t1.getUsers().add(u1);
		t1.getUsers().add(u2);

		Project p1 = new Project();
		p1.getTeams().add(t1);
		t1.getProjects().add(p1);

		NikoNikoManager.createNikoNikoConsole(u1, p1);

		NikoNikoManager.showAllNikoNikoForProject(p1);
		NikoNikoManager.showTeamStateForProject(p1);
	}
}
