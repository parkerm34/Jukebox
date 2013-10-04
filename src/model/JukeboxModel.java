package model;

import java.io.Serializable;

/*Parker Mathewson Homer Glascock IV\
 * hi */

public class JukeboxModel implements Serializable
{

	public SongList songs = new SongList();
	public StudentList students = new StudentList();
	private Student currentUser;
	

	public Student getCurrentUser() {
		return currentUser;
	}
	
	//returns true if login is sucessfull and false otherwise
	public Boolean login(String id, String pass)
	{
		currentUser = students.isValidLogin(id, pass);	
		

		
		if(currentUser != null)
		{
			return true;
		}
		
		return false;
	}
	
}
