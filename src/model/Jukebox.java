package model;

/*Parker Mathewson Homer Glascock IV\
 * hi*/

public class Jukebox
{

	private SongList songs;
	private StudentList students;
	private PlayList queued;

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
	}

	private void initSongs()
	{

	}
}
