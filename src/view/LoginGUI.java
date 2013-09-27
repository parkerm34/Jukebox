package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginGUI extends JFrame
{
	private JTextField nameField = new JTextField("User Name");
	private JPasswordField passwordField = new JPasswordField("Password");
	private JPanel pannel = new JPanel(new FlowLayout());
	private JPanel buttonPannel = new JPanel();
	private JLabel loginFailedText = new JLabel("Login Failed Try again.");
	private JButton loginButton = new JButton("login");

	public LoginGUI()
	{
		setTitle("UA Student Jukebox Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setSize(300, 140);
		this.setLocationRelativeTo(null);

		nameField.setPreferredSize(new Dimension(150, 20));
		passwordField.setPreferredSize(new Dimension(150, 20));
		
		//add listners to clear fields when you click on name and password
		initFocusListeners();

		loginButton.setActionCommand("login");

		pannel.add(new JLabel("Username:  "));
		pannel.add(nameField);
		pannel.add(new JLabel("Password:  "));
		pannel.add(passwordField);
		pannel.add(loginFailedText);

		buttonPannel.add(loginButton);

		loginFailedText.setVisible(false);

		add(pannel, BorderLayout.CENTER);
		add(buttonPannel, BorderLayout.SOUTH);
	}

	// these listners clear the text int name and password fields when you focus
	// on them
	private void initFocusListeners()
	{
		nameField.addFocusListener(new FocusListener()
		{
			@Override
			public void focusGained(FocusEvent e)
			{
				nameField.setText("");
			}

			@Override
			public void focusLost(FocusEvent e)
			{
			}
		});
		passwordField.addFocusListener(new FocusListener()
		{
			@Override
			public void focusGained(FocusEvent e)
			{
				passwordField.setText("");
			}

			@Override
			public void focusLost(FocusEvent e)
			{
			}
		});
	}

	public void displayInvalidLogin()
	{
		loginFailedText.setVisible(true);
	}

	public String getUsername()
	{
		return nameField.getText();
	}

	public String getPassword()
	{
		return new String(passwordField.getPassword());
	}

	public void addButtonActionListener(ActionListener listener)
	{
		loginButton.addActionListener(listener);
	}

}
