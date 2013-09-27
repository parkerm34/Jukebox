package controller;

import view.JukeboxGUI;
import view.LoginGUI;
import model.JukeboxModel;

/*Parker Mathewson Homer Glascock IV\
 * hi have a look at this http://www.cs.wcupa.edu/~rkline/java/mvc-design.html*/

public class JukeboxController
{
	public static void main(String args[])
	{
		new JukeboxController();
	}
	
	public JukeboxController()
	{
		JukeboxModel theModel = new JukeboxModel();
		JukeboxGUI theGUI = new JukeboxGUI();
		LoginGUI loginWindow = new LoginGUI();
		
		theGUI.setVisible(true);
	}
}