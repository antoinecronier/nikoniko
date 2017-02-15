package com.tactfactory.nikoniko.manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.tactfactory.nikoniko.manager.base.ManagerBase;
import com.tactfactory.nikoniko.models.NikoNiko;
import com.tactfactory.nikoniko.models.Project;
import com.tactfactory.nikoniko.models.User;

public class NikoNikoManager extends ManagerBase {

	public static final int[] satisfactionItems = {1,2,3};
	public static final int defaultSatisfactionError = 0;

	public static Boolean inSatisfactionItems(int satisfaction){
		Boolean flag = false;
		for (int i = 0; i < satisfactionItems.length; i++) {
			if (satisfaction == satisfactionItems[i]) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	public static int satisfactionRule(int satisfaction) {
		if (inSatisfactionItems(satisfaction)) {
			return satisfaction;
		} else {
			String error = "Error satisfaction not in ";
			for (int i = 0; i < satisfactionItems.length-1; i++) {
				error += satisfactionItems[i] + ", ";
			}
			error += satisfactionItems[satisfactionItems.length-1] + ".";
			System.err.println(error);
			return defaultSatisfactionError;
		}
	}

	public static void showAllNikoNikoForProject(Project project) {
		System.out.println("Project nikoniko items :");
		for (NikoNiko nikoNiko : project.getNikoNikos()) {
			if (nikoNiko.getSatisfaction() != defaultSatisfactionError) {
				System.out.println("  " + nikoNiko.getSatisfaction());
				if (nikoNiko.getComment() != null) {
					System.out.println("  => " + nikoNiko.getComment());
				}
			}
		}
	}

	public static int showTeamStateForProject(Project project) {
		float allItems = 0;
		for (NikoNiko nikoNiko : project.getNikoNikos()) {
			allItems += nikoNiko.getSatisfaction();
		}
		allItems = Math.round(allItems / project.getNikoNikos().size());
		System.out.println("Project nikoniko state is " + (int) allItems + ".");

		return (int) allItems;
	}

	public static void createNikoNikoConsole(User user, Project project){
		BufferedReader br = null;

		while (true) {
			br = new BufferedReader(new InputStreamReader(System.in));
			try {
				System.out.print("Enter your satisfaction : ");
				String input;

				input = br.readLine();

				if ("q".equals(input)) {
					break;
				}

				int satisfaction = 0;
				try {
					satisfaction = Integer.parseInt(input);
				} catch (Exception e) {
					// TODO: handle exception
				}

				if(NikoNikoManager.inSatisfactionItems(satisfaction)){
					System.out.print("Comment ? : ");
					String input1 = br.readLine();
					if (!input1.equals("")) {
						NikoNiko tempNiko = new NikoNiko(user, project, Integer.parseInt(input),input1);
					}else{
						NikoNiko tempNiko = new NikoNiko(user, project, Integer.parseInt(input));
					}
				}

				if (satisfaction == 0 || !NikoNikoManager.inSatisfactionItems(satisfaction)) {
					System.err.println("Input issue");
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
