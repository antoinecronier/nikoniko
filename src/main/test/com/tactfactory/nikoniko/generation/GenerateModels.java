package generation;

import java.util.ArrayList;
import java.util.Date;

import com.tactfactory.nikoniko.manager.database.manager.NikoNikoDBManager;
import com.tactfactory.nikoniko.manager.database.manager.ProjectDBManager;
import com.tactfactory.nikoniko.manager.database.manager.TeamDBManager;
import com.tactfactory.nikoniko.manager.database.manager.UserDBManager;
import com.tactfactory.nikoniko.models.NikoNiko;
import com.tactfactory.nikoniko.models.Project;
import com.tactfactory.nikoniko.models.Team;
import com.tactfactory.nikoniko.models.User;

public class GenerateModels {

	ArrayList<User> listUsers = new ArrayList<User>();
	ArrayList<Project> listProjects = new ArrayList<Project>();
	ArrayList<Team> listTeams = new ArrayList<Team>();
	ArrayList<NikoNiko> listNikoNikos = new ArrayList<NikoNiko>();

	public void insertUsers() {
		UserDBManager userDBManager = new UserDBManager();
		
		listUsers.add(new User("nathaliel", "asdfgh", "lefebvre", "nathalie", "0"));
		listUsers.add(new User("judithg", "charlie", "gay", "judith", "1"));
		listUsers.add(new User("jeang", "elephant", "giraud", "jean", "2"));
		listUsers.add(new User("marielleg", "success", "gautier", "marielle", "3"));
		listUsers.add(new User("jeanined", "booboo", "denis", "jeanine", "4"));
		listUsers.add(new User("arianec", "fisher", "collet", "ariane", "5"));
		listUsers.add(new User("joséew", "access", "weber", "josée", "6"));
		listUsers.add(new User("simonb", "goodluck", "bertrand", "simon", "7"));
		listUsers.add(new User("bonifaces", "baseball", "simon", "boniface", "8"));
		listUsers.add(new User("frédériquec", "emily", "chevallier", "frédérique", "9"));
		listUsers.add(new User("donatiennem", "whatever", "morel", "donatienne", "10"));
		listUsers.add(new User("yannl", "1234", "lamy", "yann", "11"));
		listUsers.add(new User("morganel", "delta", "leger", "morgane", "12"));
		listUsers.add(new User("laurentb", "alexander", "benoit", "laurent", "13"));
		listUsers.add(new User("michèler", "newyork", "riviere", "michèle", "14"));
		listUsers.add(new User("viviennem", "dakota", "mathieu", "vivienne", "15"));
		listUsers.add(new User("thibaultl", "apple", "lebrun", "thibault", "16"));
		listUsers.add(new User("jérômeh", "charlie", "hamon", "jérôme", "17"));
		listUsers.add(new User("michèlel", "lacrosse", "lucas", "michèle", "18"));
		listUsers.add(new User("michelleb", "anthony", "brun", "michelle", "19"));

		for(User user : listUsers) {
			userDBManager.insert(user);
		}
	}

	public void insertTeams() {

		TeamDBManager teamManager = new TeamDBManager();
		int nbTeams = 10;

		for (int i = 0; i < nbTeams; i++) {
			String name = "CGITEAM " + i;
			String serial = "300" + i;

			Team team = new Team(name, serial);

			teamManager.insert(team);
			listTeams.add(team);
		}
	}

	public void insertNikoNikos(){
		String [] positiveComment = Ressource.positiveComment;
		String [] negativecomment = Ressource.negativeComment;
		String [] comment = Ressource.comment;


		NikoNikoDBManager nikoManager = new NikoNikoDBManager();
		int satisfaction;
		int nbNikoNikos = 10;
		
		NikoNiko nikoNiko = null;
		String commentString = null;
		for (int i = 0; i < nbNikoNikos; i++) {
			if (i % 3 == 0) {
				satisfaction = 1;
				nikoNiko = new NikoNiko(listUsers.get(i), listProjects.get(i), satisfaction);
				if(i%2==0) {
					commentString = positiveComment[i%positiveComment.length];
					nikoNiko.setComment(commentString);
				}
			} else if (i % 3 == 1){
				satisfaction = 2;
				nikoNiko = new NikoNiko(listUsers.get(i), listProjects.get(i), satisfaction);
				if(i%2==0) {
					commentString = comment[i%comment.length];
					nikoNiko.setComment(commentString);
				}
			} else{
				satisfaction = 3;
				nikoNiko = new NikoNiko(listUsers.get(i), listProjects.get(i), satisfaction);
				if(i%2==0) {
					commentString = negativeComment[i%negativeComment.length];
					nikoNiko.setComment(commentString);
				}
			}
			nikoManager.insert(nikoNiko);
			
		}
	}

	public void insertProjects(){
		
		ProjectDBManager projectManager = new ProjectDBManager();
		int nbProjects = 10;
		
		for (int i = 0; i < nbProjects; i++) {
			String name = "CGIPROJECT " +i ;
			String dateString = String.format("1992-08-%02d", (i % 27) +1 );
			
			@SuppressWarnings("deprecation")
			Date date = new Date(dateString);
			
			Project project = new Project(name,date);
			projectManager.insert(project);
			
			listProjects.add(project);
			projectManager.insert(project);
		}
	}

	public void insertRelation() {

		UserDBManager userManager = new UserDBManager();

		// Relation between User and Team
		userManager.mapRelation(this.listUsers.get(0), this.listTeams.get(1));
		userManager.mapRelation(this.listUsers.get(0), this.listTeams.get(5));
		userManager.mapRelation(this.listUsers.get(1), this.listTeams.get(2));
		userManager.mapRelation(this.listUsers.get(1), this.listTeams.get(9));
		userManager.mapRelation(this.listUsers.get(1), this.listTeams.get(6));
		userManager.mapRelation(this.listUsers.get(1), this.listTeams.get(5));
		userManager.mapRelation(this.listUsers.get(2), this.listTeams.get(1));
		userManager.mapRelation(this.listUsers.get(4), this.listTeams.get(6));
		userManager.mapRelation(this.listUsers.get(4), this.listTeams.get(1));
		userManager.mapRelation(this.listUsers.get(4), this.listTeams.get(4));
		userManager.mapRelation(this.listUsers.get(5), this.listTeams.get(5));
		userManager.mapRelation(this.listUsers.get(6), this.listTeams.get(8));
		userManager.mapRelation(this.listUsers.get(7), this.listTeams.get(2));
		userManager.mapRelation(this.listUsers.get(8), this.listTeams.get(9));
		userManager.mapRelation(this.listUsers.get(9), this.listTeams.get(0));

		TeamDBManager teamManager = new TeamDBManager();

		// Relation between Team and Projects
		teamManager.mapRelation(this.listTeams.get(0), this.listProjects.get(0));
		teamManager.mapRelation(this.listTeams.get(0), this.listProjects.get(1));
		teamManager.mapRelation(this.listTeams.get(1), this.listProjects.get(7));
		teamManager.mapRelation(this.listTeams.get(2), this.listProjects.get(0));
		teamManager.mapRelation(this.listTeams.get(2), this.listProjects.get(1));
		teamManager.mapRelation(this.listTeams.get(2), this.listProjects.get(7));
		teamManager.mapRelation(this.listTeams.get(3), this.listProjects.get(9));
		teamManager.mapRelation(this.listTeams.get(3), this.listProjects.get(6));
		teamManager.mapRelation(this.listTeams.get(4), this.listProjects.get(5));
		teamManager.mapRelation(this.listTeams.get(5), this.listProjects.get(9));
		teamManager.mapRelation(this.listTeams.get(5), this.listProjects.get(2));
		teamManager.mapRelation(this.listTeams.get(6), this.listProjects.get(0));
		teamManager.mapRelation(this.listTeams.get(7), this.listProjects.get(1));
		teamManager.mapRelation(this.listTeams.get(8), this.listProjects.get(4));
		teamManager.mapRelation(this.listTeams.get(9), this.listProjects.get(8));
		teamManager.mapRelation(this.listTeams.get(9), this.listProjects.get(8));

		// Relation between user and NikoNiko
		listUsers.get(0).getNikoNikos().add(listNikoNikos.get(1));
		listUsers.get(0).getNikoNikos().add(listNikoNikos.get(2));
		listUsers.get(1).getNikoNikos().add(listNikoNikos.get(3));
		listUsers.get(2).getNikoNikos().add(listNikoNikos.get(4));
		listUsers.get(2).getNikoNikos().add(listNikoNikos.get(5));
		listUsers.get(3).getNikoNikos().add(listNikoNikos.get(6));
		listUsers.get(4).getNikoNikos().add(listNikoNikos.get(7));
		listUsers.get(4).getNikoNikos().add(listNikoNikos.get(8));
		listUsers.get(5).getNikoNikos().add(listNikoNikos.get(9));
		listUsers.get(6).getNikoNikos().add(listNikoNikos.get(0));
		listUsers.get(6).getNikoNikos().add(listNikoNikos.get(1));
		listUsers.get(6).getNikoNikos().add(listNikoNikos.get(2));
		listUsers.get(7).getNikoNikos().add(listNikoNikos.get(3));
		listUsers.get(8).getNikoNikos().add(listNikoNikos.get(4));
		listUsers.get(9).getNikoNikos().add(listNikoNikos.get(5));

	}

}