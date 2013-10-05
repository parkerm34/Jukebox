/*Parker Mathewson
 * Homer Glascock IV
 * Nicholas Pierson
 * 
 * This is the JukeboxController class. This was done to try to
 * teach ourselves more about MVC style programming. This holds
 * the model and view classes needed for the jukebox to run smoothly.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import model.JukeboxModel;
import view.JukeboxGUI;
import view.LoginGUI;


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
		
	}
	
	private void login(String username, String password)
	{
		if(theModel.login(loginWindow.getUsername(), loginWindow.getPassword()))
		{
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
