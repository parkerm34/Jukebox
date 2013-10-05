package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import controller.JukeboxController;

import model.JukeboxModel;
import model.LimitedQueue;
import model.PlayList;
import model.Song;
import model.SongList;
import model.Student;
import model.StudentList;

public class JukeboxGUI extends JFrame
{
	private SongList songList;
	private StudentList studentList;
	private TableModel songTableModel;
	private JTable songTable;
	private PlayList queued = new PlayList();
	private static String[] displayQueue = new String[5];
	private static JList halp = new JList(displayQueue);
	private Student currentUser;
	
	
	private JTextArea userTextArea = new JTextArea();
	private JTextArea timeTextArea = new JTextArea();
	private JTextArea songsTextArea = new JTextArea();
	private JButton songSelect = new JButton("Queue Song");
	private JButton logoutButton = new JButton("logout");
	private JukeboxController controller;
	private JukeboxModel model;
	
	public static String baseDir = System.getProperty("user.dir") + File.separator + "SerializedObjects" + File.separator;

    public static final String FILE_NAME_WHERE_JUKEBOX_STORED = baseDir + "jukeboxCollection.object";
    public static final String FILE_NAME_WHERE_PLAYLIST_STORED = baseDir + "playlistCollection.object";


	public JukeboxGUI(JukeboxController helper, JukeboxModel model)
	{
		this.model = model;
		this.controller = helper;
		layoutGUI();
	}

	private void layoutGUI()
	{
		addWindowListener(new CloseListener());
		
		setTitle("University of Arizona Student Jukebox");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		// setBackground(Color.BLUE);

		this.setSize(700, 500);
		this.setLocation(200, 200);

		songList = new SongList();
		songTableModel = songList;
		songTable = new JTable(songTableModel);
		songTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scrollPane = new JScrollPane(songTable);

		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(songTableModel);

		songTable.setRowSorter(sorter);

		add(scrollPane, BorderLayout.CENTER);

		JPanel panel2 = new JPanel();
		panel2.add(songSelect);
		add(panel2, BorderLayout.NORTH);
		songSelect.addActionListener(new ButtonListener());		

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 1, 5, 5));
		

		
		JLabel queueLabel = new JLabel("  Up next...  ", JLabel.CENTER);
		panel.add(queueLabel);
		queueLabel.setVerticalAlignment(JLabel.BOTTOM);
		queueLabel.setVerticalTextPosition(JLabel.BOTTOM);
		
		
		panel.add(halp);
		panel.setSize(new Dimension(100, 500));
		halp.setBackground(Color.lightGray);
		add(panel, BorderLayout.WEST);

		JPanel panel3 = new JPanel();
		panel3.setSize(new Dimension(700, 100));
		
		JLabel userLabel = new JLabel("Current User: ");
		JLabel timeLeftLabel = new JLabel("Time Left: ");
		JLabel songsLeftLabel = new JLabel("Songs Left: ");
		
		panel3.add(userLabel);
		userTextArea.setText("Not logged in");
		panel3.add(userTextArea);
		
		panel3.add(timeLeftLabel);
		timeTextArea.setText("Not logged in");
		panel3.add(timeTextArea);
		
		panel3.add(songsLeftLabel);
		songsTextArea.setText("Not logged in");
		panel3.add(songsTextArea);
		
		panel3.add(logoutButton);
		logoutButton.addActionListener(new ButtonListener());				
		
		add(panel3, BorderLayout.SOUTH);
	}

	private class CloseListener implements WindowListener {
		@Override
		public void windowClosing(WindowEvent e) {
			String message = "Would you like to save?";
			String title = "Closing Jukebox Session";
			
			int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_CANCEL_OPTION);
	        if (reply == JOptionPane.YES_OPTION) {
	        	try {
		        	FileOutputStream stream = new FileOutputStream(FILE_NAME_WHERE_JUKEBOX_STORED);
		        	ObjectOutputStream outFile = new ObjectOutputStream(stream);
		        	// outFile understands the writeObject message.
		        	// Make the object persist so it can be read later.
		        	outFile.writeObject(model);
		        	outFile.close(); // Always close the output file!
		        	
		        	stream = new FileOutputStream(FILE_NAME_WHERE_PLAYLIST_STORED);
		            outFile = new ObjectOutputStream(stream);
		            outFile.writeObject(halp);
		            outFile.writeObject(queued.songsQueued);
		            outFile.writeObject(displayQueue);
		            // Do NOT forget to close the output stream!
		            outFile.close();
		          
		            
	        	} catch (IOException ioe) {
	        		System.out.println("Writing objects failed");
	        	}
	        	System.exit(0);
	        }
	        
	        else if(reply == JOptionPane.NO_OPTION) {
	        	JOptionPane.showMessageDialog(null, "Current State Erased");
	        	System.exit(0);
	        }
	        else {
	        	System.out.println("da fuq");
	        }
		}
		
		@Override
		public void windowActivated(WindowEvent e) {
			
		}

		@Override
		public void windowClosed(WindowEvent e) {
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			
		}

		@Override
		public void windowIconified(WindowEvent e) {
			
		}

		@Override
		public void windowOpened(WindowEvent e) {
			String message = "Would you like to use the previously saved state?";
			String title = "Opening Jukebox Session";
			
			int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
	        if (reply == JOptionPane.YES_OPTION)
	        {
	        	try
	        	{
	                // Read the model object from its disk file
	                FileInputStream inFile = new FileInputStream(FILE_NAME_WHERE_JUKEBOX_STORED);
	                ObjectInputStream inputStream = new ObjectInputStream(inFile);
	                model = (JukeboxModel) inputStream.readObject();
	                inputStream.close();
	                
	                setCurrentUser(currentUser.getId());

	                // Use the same stream objects to read the other disk file
	                inFile = new FileInputStream(FILE_NAME_WHERE_PLAYLIST_STORED);
	                inputStream = new ObjectInputStream(inFile);
	                halp = (JList) inputStream.readObject();
	                queued.songsQueued = (LimitedQueue<Song>) inputStream.readObject();
	                displayQueue = (String[]) inputStream.readObject();
	                inputStream.close();
	                
	                System.out.println(displayQueue[0]);
	                System.out.println(displayQueue[1]);
	                System.out.println(queued.songsQueued.get(0).getSongName());
	                
//	                updateDisplayQueue();
					halp.setListData(displayQueue);
	                
	                // TODO Set displayQueue to display the queue on the left sidebar
	                // Should be stored in halp. Cant figure it out.
	                
	                PlayList.playSong();
	        	}
	        	catch (Exception exep)
	        	{
	                String errorMes = "Error reading serialzed objects\n";
	                errorMes += "Run tests.InitializeAccountAndTransactionCollections";
	                JOptionPane.showMessageDialog(null, message);
	        	}
	          JOptionPane.showMessageDialog(null, "Starting on previously saved state");
	        }
	        
	        else {
	           JOptionPane.showMessageDialog(null, "Starting on new state");
	        }
		}
	}
	
	private class ButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			if(arg0.getSource() == songSelect)
			{
				if(currentUser != null)
				{
					int viewRow = songTable.getSelectedRow();
				
					if (viewRow < 0)
						System.out.println("index " + viewRow + " means no row is selected");
					else
					{
						int modelRow = songTable.convertRowIndexToModel(viewRow);
						System.out.println("index " + viewRow + " has the name '"
								+ songTableModel.getValueAt(modelRow, 0) + "'");
					
						int songIndex = queued.find(songTableModel.getValueAt(modelRow,  0).toString());
						int studentIndex = queued.findStudent(LoginGUI.getUsername(), studentList);
				
				
						// Adds song to queue
						if(model.songs.getList().get(songIndex).allowedToPlay() && model.students.getStudents().get(studentIndex).allowedToPlay())
						{
					
							// increments daycount after the song is added
							if(PlayList.songsQueued.add(model.songs.getList().get(songIndex)))
							{
								model.songs.getList().get(songIndex).setDayCount(model.songs.getList().get(songIndex).getDayCount() + 1);
								model.students.getStudents().get(studentIndex).setDayCount(model.students.getStudents().get(studentIndex).getDayCount() + 1);
								model.students.getStudents().get(studentIndex).subtractFromTimeLeft(model.songs.getList().get(songIndex).getSongTime());
								System.out.println(model.students.getStudents().get(studentIndex).getTimeLeft());
							}
				
							displayQueue[PlayList.songsQueued.size()-1] = model.songs.getList().get(songIndex).getSongName();
							System.out.println(displayQueue[PlayList.songsQueued.size()-1]);
							halp.setListData(displayQueue);
				
							// plays song if this item is the only thing in the list
							if(PlayList.songsQueued.size() == 1)
							{
								PlayList.playSong();
								halp.setListData(displayQueue);
							}
							setCurrentUser(currentUser.getId());

						}
						else if( !model.songs.getList().get(songIndex).allowedToPlay() && model.students.getStudents().get(studentIndex).allowedToPlay() )
						{
							JOptionPane cannotPlay = new JOptionPane();
							cannotPlay.showMessageDialog(null, "This song has reached it's maximum plays for today");
						}
						else
						{
							JOptionPane cannotPlay = new JOptionPane();
							cannotPlay.showMessageDialog(null, "You have reached your maximum plays for today");					
						}
					}
				}
				else
				{
					JOptionPane notLoggedIn = new JOptionPane();
					notLoggedIn.showMessageDialog(null, "Please login first");
				}
			}
			else
			{
				setCurrentUser(null);
				controller.reLogin();
				System.out.println("hi");
			}
		}
	}
	
	public static void updateDisplayQueue()
	{
		int size = PlayList.songsQueued.size();
		halp.setListData(displayQueue);
		for(int x = 0; x < size; x ++)
			displayQueue[x] = displayQueue[x+1];
		
		if(size != 0)
			for(int x = size; x < 5; x++)
				displayQueue[x] = "";
		if(size == 0)
			displayQueue[0] = "";
		
		halp.setListData(displayQueue);
	}
	
	public void setCurrentUser(String loggedIn)
	{
		if(loggedIn != null)
		{
			this.currentUser = model.students.getStudents().get(queued.findStudent(loggedIn, studentList));
			this.userTextArea.setText(model.students.getStudents().get(queued.findStudent(loggedIn, studentList)).getId());
			this.timeTextArea.setText(model.students.getStudents().get(queued.findStudent(loggedIn, studentList)).getTimeString());
			this.songsTextArea.setText("" + (2 - model.students.getStudents().get(queued.findStudent(loggedIn, studentList)).getDayCount()));
		}
		else
		{
			this.currentUser = null;
			this.userTextArea.setText("Not logged in");
			this.timeTextArea.setText("Not logged in");
			this.songsTextArea.setText("Not logged in");
		}
	}
}
