package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import model.JukeboxModel;
import model.SongList;

public class LoginGUI extends JFrame
{
	public LoginGUI()
	{
	
	}

	private void layoutGUI()
	{
		setTitle("University of Arizona Student Jukebox");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		 setBackground(Color.BLUE);

		this.setSize(700, 500);
		this.setLocation(200, 200);

		
	}

	private class ButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			
		}
	}
}
