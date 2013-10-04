package model;

import java.util.ArrayList;

public class StudentList {

	private ArrayList<Student> students = new ArrayList<Student>();

	public StudentList()
	{
		addStudent(new Student("Ali","1111"));
		addStudent(new Student("Chris","2222" ));
		addStudent(new Student("River","3333" ));
		addStudent(new Student("Ryan","4444" ));
		addStudent(new Student("asdf", "asdf"));
	}
	
	public void addStudent(Student stu) {
		students.add(stu);
	}

	public void removeStudent(Student stu) {
		students.remove(stu);
	}

	public Student removeStudent(int index) {
		return students.remove(index);
	}

	//checks to see if login is valid if it is it returns the student
	//it returns null otherwise
	public Student isValidLogin(String id, String pass) {
		
		for(Student x: students)
		{

			if(x.getId().equals(id) && x.getPassword().equals(pass))
			return x;
		}
		
		return null;
	}
	

	/*******************************************************************************************/
	/* SETTERS AND GETTERS FOR CLASS STUDENTLIST */
	/*******************************************************************************************/

	public ArrayList<Student> getStudents() {
		return students;
	}

	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}


}
