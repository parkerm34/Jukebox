package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
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


	public JukeboxGUI(JukeboxController helper)
	{
		this.controller = helper;
		layoutGUI();
	}

	private void layoutGUI()
	{
		addWindowListener(new CloseListener());
		
		setTitle("University of Arizona Student Jukebox");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
	          JOptionPane.showMessageDialog(null, "Current State Saved");
	          System.exit(0);
	        }
	        
	        else if(reply == JOptionPane.NO_OPTION) {
	           JOptionPane.showMessageDialog(null, "Current State Erased");
	           System.exit(0);
	        }
	        else {}
		}
		
		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowOpened(WindowEvent e) {
			String message = "Would you like to use the previously saved state?";
			String title = "Opening Jukebox Session";
			
			int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
	        if (reply == JOptionPane.YES_OPTION) {
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
//					 	+ songTable.getValueAt(songTable.getSelectedRow(),songTable.convertColumnIndexToModel(0))+"'");
//						this is another way to so it
					
						Song temp = queued.find(songTableModel.getValueAt(modelRow, 0).toString());
						Student student = queued.findStudent(LoginGUI.getUsername(), studentList);
//						System.out.println(student);
				
				
						// Adds song to queue
						if(temp.allowedToPlay() && student.allowedToPlay())
						{
					
							// increments daycount after the song is added
							if(PlayList.songsQueued.add(temp))
							{
								temp.setDayCount(temp.getDayCount() + 1);
								student.setDayCount(student.getDayCount() + 1);
								//TODO: SUBTRACT TIME!
								student.subtractFromTimeLeft(temp.getSongTime());
								System.out.println(student.getTimeLeft());
//								System.out.println(student.getId());
							}
				
							displayQueue[PlayList.songsQueued.size()-1] = temp.getSongName();
							System.out.println(displayQueue[PlayList.songsQueued.size()-1]);
							halp.setListData(displayQueue);
				
							//testing for printing the song name
//							System.out.println(PlayList.songsQueued.peek().getSongName());
							
							// plays song if this item is the only thing in the list
							if(PlayList.songsQueued.size() == 1)
							{
								// Would take away current song playing on displayed playlist
//								displayQueue[0] = "";
							
								PlayList.playSong();
								halp.setListData(displayQueue);
							}
							setCurrentUser(currentUser.getId());

						}
						else if( !temp.allowedToPlay() && student.allowedToPlay() )
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
			this.currentUser = queued.findStudent(loggedIn, studentList);
			this.userTextArea.setText(this.currentUser.getId());
			this.timeTextArea.setText(this.currentUser.getTimeString());
			this.songsTextArea.setText("" + (2 - this.currentUser.getDayCount()));
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
