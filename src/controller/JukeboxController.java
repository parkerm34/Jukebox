package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import model.JukeboxModel;
import view.JukeboxGUI;
import view.LoginGUI;

/*Parker Mathewson Homer Glascock IV\
 * hi have a look at this http://www.cs.wcupa.edu/~rkline/java/mvc-design.html*/

public class JukeboxController implements Serializable
{

	private JukeboxModel theModel = new JukeboxModel();
	private JukeboxGUI theGUI = new JukeboxGUI(this, this.theModel);
	private LoginGUI loginWindow = new LoginGUI();
	
	public static void main(String args[])
	{
		new JukeboxController();
	}

	public JukeboxController()
	{
		loginWindow.addButtonActionListener(new ButtonListener());

		loginWindow.setVisible(true);
	}
	
	public void reLogin()
	{
		loginWindow = new LoginGUI();
		loginWindow.addButtonActionListener(new ButtonListener());

		loginWindow.setVisible(true);
		
		System.out.println(theModel.students.getStudents().get(4).getTimeLeft());
	}
	
	private void login(String username, String password)
	{
		if(theModel.login(loginWindow.getUsername(), loginWindow.getPassword()))
		{
			System.out.println("fuck Yeah");
			theGUI.setCurrentUser(loginWindow.getUsername());
			theGUI.setVisible(true);
			loginWindow.setVisible(false);
		}
		else
		{
			loginWindow.displayInvalidLogin();
		}
	}

	private class ButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(e.getActionCommand().equals("login"))
			{
				login(loginWindow.getUsername(), loginWindow.getPassword());
			}
		}
	}
}