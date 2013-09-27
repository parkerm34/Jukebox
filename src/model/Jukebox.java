package model;

/*Parker Mathewson Homer Glascock IV\
 * hi*/

public class Jukebox
{

	private SongList songs;
	private StudentList students;
	private PlayList queued;
	private Student currentUser;


	public static void main(String[] args)
	{
		Jukebox theJukebox = new Jukebox();
		theJukebox.initStudents();
		theJukebox.initSongs();
		
	}

	private void initStudents()
	{
		students = new StudentList();
		students.addStudent(new Student("Ali","1111"));
		students.addStudent(new Student("Chris","2222" ));
		students.addStudent(new Student("River","3333" ));
		students.addStudent(new Student("Ryan","4444" ));
		students.addStudent(new Student("asdf", "asdf"));
	}

	private void initSongs()
	{

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

	public Student getCurrentUser() {
		return currentUser;
	}
	
}
