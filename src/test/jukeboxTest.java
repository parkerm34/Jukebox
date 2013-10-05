package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import model.JukeboxModel;
import model.Song;
import model.Student;
import model.StudentList;

import org.junit.Test;

public class jukeboxTest {
	 @Test
	  public void testJukeboxModelSongs() {
		 JukeboxModel model = new JukeboxModel();
		 
		//Test that the song ext are correct
		 assertEquals("BlueRidgeMountainMist.mp3", model.songs.getList().get(0).getSongExt());
		 assertEquals("DeterminedTumbao.mp3", model.songs.getList().get(1).getSongExt());
		 assertEquals("flute.aif", model.songs.getList().get(2).getSongExt());
		 assertEquals("spacemusic.au", model.songs.getList().get(3).getSongExt());
		 assertEquals("SwingCheese.mp3", model.songs.getList().get(4).getSongExt());
		 assertEquals("tada.wav", model.songs.getList().get(5).getSongExt());
		 assertEquals("UntameableFire.mp3", model.songs.getList().get(6).getSongExt());
		 
		 //Test that the song names are correct
		 assertEquals("Blue Ridge Mountain Mist", model.songs.getList().get(0).getSongName());
		 assertEquals("Determined Tumbao", model.songs.getList().get(1).getSongName());
		 assertEquals("Flute", model.songs.getList().get(2).getSongName());
		 assertEquals("Space Music", model.songs.getList().get(3).getSongName());
		 assertEquals("Swing Cheese", model.songs.getList().get(4).getSongName());
		 assertEquals("Tada", model.songs.getList().get(5).getSongName());
		 assertEquals("Untamable Fire", model.songs.getList().get(6).getSongName());
		 
		 //test that the artist names are correct
		 assertEquals("Ralph Schuckett", model.songs.getList().get(0).getArtistName());
		 assertEquals("FreePlay Music", model.songs.getList().get(1).getArtistName());
		 assertEquals("Sun Microsystems", model.songs.getList().get(2).getArtistName());
		 assertEquals("Unknown", model.songs.getList().get(3).getArtistName());
		 assertEquals("FreePlay Music", model.songs.getList().get(4).getArtistName());
		 assertEquals("Microsoft", model.songs.getList().get(5).getArtistName());
		 assertEquals("Pierre Langer", model.songs.getList().get(6).getArtistName());
		 
		 //test that the song times are correct
		 assertEquals(38, model.songs.getList().get(0).getSongTime());
		 assertEquals(20, model.songs.getList().get(1).getSongTime());
		 assertEquals(5, model.songs.getList().get(2).getSongTime());
		 assertEquals(6, model.songs.getList().get(3).getSongTime());
		 assertEquals(15, model.songs.getList().get(4).getSongTime());
		 assertEquals(2, model.songs.getList().get(5).getSongTime());
		 assertEquals(282, model.songs.getList().get(6).getSongTime());
		 
		 //test the getColoumnClass method in SongList.java
		 assertEquals(String.class, model.songs.getColumnClass(0));
		 assertEquals(String.class, model.songs.getColumnClass(1));
		 assertEquals(Integer.class, model.songs.getColumnClass(2));
		 
		 //test the getColumnCount method in SongList.java
		 assertEquals(3, model.songs.getColumnCount());
		 
		 //test the getColumnName method in SongList.java
		 assertEquals("Song Name", model.songs.getColumnName(0));
		 assertEquals("Artist", model.songs.getColumnName(1));
		 assertEquals("Time", model.songs.getColumnName(2));
		 
		 //test the getRowCount method in SongList.java
		 assertEquals(7, model.songs.getRowCount());
		 
		 //test the getValueAt method in SongList.java
		 //Test that the song names are correct
		 assertEquals("Blue Ridge Mountain Mist", model.songs.getValueAt(0,0));
		 assertEquals("Determined Tumbao", model.songs.getValueAt(1,0));
		 assertEquals("Flute", model.songs.getValueAt(2,0));
		 assertEquals("Space Music", model.songs.getValueAt(3,0));
		 assertEquals("Swing Cheese", model.songs.getValueAt(4,0));
		 assertEquals("Tada", model.songs.getValueAt(5,0));
		 assertEquals("Untamable Fire", model.songs.getValueAt(6,0));
		 
		 //test that the artist names are correct
		 assertEquals("Ralph Schuckett", model.songs.getValueAt(0,1));
		 assertEquals("FreePlay Music", model.songs.getValueAt(1,1));
		 assertEquals("Sun Microsystems", model.songs.getValueAt(2,1));
		 assertEquals("Unknown", model.songs.getValueAt(3,1));
		 assertEquals("FreePlay Music", model.songs.getValueAt(4,1));
		 assertEquals("Microsoft", model.songs.getValueAt(5,1));
		 assertEquals("Pierre Langer", model.songs.getValueAt(6,1));
		 
		 //test that the song times are correct
		 assertEquals(38, model.songs.getValueAt(0,2));
		 assertEquals(20, model.songs.getValueAt(1,2));
		 assertEquals(5, model.songs.getValueAt(2,2));
		 assertEquals(6, model.songs.getValueAt(3,2));
		 assertEquals(15, model.songs.getValueAt(4,2));
		 assertEquals(2, model.songs.getValueAt(5,2));
		 assertEquals(282, model.songs.getValueAt(6,2));
		
		 //test the isCellEditable method in SongList.java
		 for(int i = 0; i < 7; i++) {
			 for(int j = 0; j < 3; j++)
				 assertEquals(false, model.songs.isCellEditable(i,j)); 
		 }
	 
		 //test the getters and setters for instance variables in Song.java
		 for(int i = 0; i < 7; i++) {
			 model.songs.getList().get(i).setSongExt("yo" + i + ".ext");
			 model.songs.getList().get(i).setSongName("yo" + i + ".ext");
			 model.songs.getList().get(i).setArtistName("yo" + i + ".ext");
			 model.songs.getList().get(i).setSongTime(i);
			 model.songs.getList().get(i).setDayCount(i);
			 
			 assertEquals("yo" + i + ".ext", model.songs.getList().get(i).getSongExt());
			 assertEquals("yo" + i + ".ext", model.songs.getList().get(i).getSongName());
			 assertEquals("yo" + i + ".ext", model.songs.getList().get(i).getArtistName());
			 assertEquals(i, model.songs.getList().get(i).getSongTime());
			 assertEquals(i, model.songs.getList().get(i).getDayCount());
		 }
		 
		 //test the getters and setters for instance variables in Song.java
		 model.songs.getList().get(0).setDayCount(4); 
		 model.songs.getList().get(1).setDayCount(5); 
		 model.songs.getList().get(2).setDayCount(6); 
		 assertTrue(model.songs.getList().get(0).allowedToPlay()); 
		 assertFalse(model.songs.getList().get(1).allowedToPlay()); 
		 assertFalse(model.songs.getList().get(2).allowedToPlay()); 
	}
	 
	 @Test
	  public void testJukeboxModelStudents() {
		 JukeboxModel model = new JukeboxModel();
		
		 //Test that the student id (names) are correct
		 assertEquals("Ali", model.students.getStudents().get(0).getId());
		 assertEquals("Chris", model.students.getStudents().get(1).getId());
		 assertEquals("River", model.students.getStudents().get(2).getId());
		 assertEquals("Ryan", model.students.getStudents().get(3).getId());
		 assertEquals("asdf", model.students.getStudents().get(4).getId());
		 
		//Test that the student passwords are correct
		 assertEquals("1111", model.students.getStudents().get(0).getPassword());
		 assertEquals("2222", model.students.getStudents().get(1).getPassword());
		 assertEquals("3333", model.students.getStudents().get(2).getPassword());
		 assertEquals("4444", model.students.getStudents().get(3).getPassword());
		 assertEquals("asdf", model.students.getStudents().get(4).getPassword());
		 
		 //Test the addStudent and removeStudent functions in StudentList.java
		 Student chuck = new Student("Chuck", "yoyo");
		 model.students.addStudent(chuck);
		 assertEquals("Chuck", model.students.getStudents().get(5).getId());
		 assertEquals("yoyo", model.students.getStudents().get(5).getPassword());
		 
		 model.students.removeStudent(chuck);
		 assertEquals(5, model.students.getStudents().size());
		 
		 model.students.addStudent(chuck);
		 Student chuck2 = model.students.removeStudent(5);
		 assertEquals(5, model.students.getStudents().size());
		 assertEquals("Chuck", chuck2.getId());
		 assertEquals("yoyo", chuck2.getPassword());
		 
		 //test isValidLogin method in StudentList.java
		 assertEquals(model.students.getStudents().get(0) , model.students.isValidLogin("Ali", "1111"));
		 assertEquals(model.students.getStudents().get(1) , model.students.isValidLogin("Chris", "2222"));
		 assertEquals(model.students.getStudents().get(2) , model.students.isValidLogin("River", "3333"));
		 assertEquals(model.students.getStudents().get(3) , model.students.isValidLogin("Ryan", "4444"));
		 assertEquals(model.students.getStudents().get(4) , model.students.isValidLogin("asdf", "asdf"));
		 assertEquals(null , model.students.isValidLogin("ali", "1111"));
		 
		 //test setStudents method in StudentList.java
		 ArrayList<Student> tempStudents = new ArrayList<Student>();
		 tempStudents.add(new Student("River","3333"));
		 tempStudents.add(new Student("Ryan","4444"));
		 model.students.setStudents(tempStudents);
		 
		 assertEquals("River", model.students.getStudents().get(0).getId());
		 assertEquals("Ryan", model.students.getStudents().get(1).getId());
		 assertEquals("3333", model.students.getStudents().get(0).getPassword());
		 assertEquals("4444", model.students.getStudents().get(1).getPassword());
		
		 model.students.getStudents().get(0).setId("Harry");
		 model.students.getStudents().get(0).setPassword("1234");
		 model.students.getStudents().get(0).setTimeLeft(5);
		 model.students.getStudents().get(0).setDayCount(1);
		 model.students.getStudents().get(0).setActiveStudent(false);
		 
		 assertEquals("Harry", model.students.getStudents().get(0).getId());
		 assertEquals("1234", model.students.getStudents().get(0).getPassword());
		 assertEquals(5 , model.students.getStudents().get(0).getTimeLeft());
		 assertEquals(1 , model.students.getStudents().get(0).getDayCount());
		 assertEquals(false, model.students.getStudents().get(0).isActiveStudent());
		 
		 assertEquals(true , model.students.getStudents().get(0).allowedToPlay());
		 model.students.getStudents().get(0).setDayCount(2);
		 assertEquals(false , model.students.getStudents().get(0).allowedToPlay());
		 
		 model.students.getStudents().get(0).subtractFromTimeLeft(3);
		 assertEquals(2 , model.students.getStudents().get(0).getTimeLeft());
		 
	 }

}
