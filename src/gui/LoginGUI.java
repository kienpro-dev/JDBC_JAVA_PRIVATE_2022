package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JCheckBox;

public class LoginGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernameTf;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 438, 478);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("GYM FITNESS");
		lblNewLabel.setForeground(SystemColor.textHighlight);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(102, 75, 218, 39);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(96, 152, 81, 17);
		contentPane.add(lblNewLabel_1);
		
		usernameTf = new JTextField();
		usernameTf.setBounds(96, 179, 230, 17);
		contentPane.add(usernameTf);
		usernameTf.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(96, 221, 81, 17);
		contentPane.add(lblNewLabel_1_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(96, 254, 230, 17);
		contentPane.add(passwordField);
		
		JButton loginBtn = new JButton("LOGIN");
		loginBtn.setBackground(SystemColor.textHighlight);
		loginBtn.setForeground(SystemColor.text);
		loginBtn.setBounds(96, 304, 230, 23);
		contentPane.add(loginBtn);
		
		JCheckBox showPass = new JCheckBox("Show password");
		showPass.setBounds(93, 278, 130, 23);
		contentPane.add(showPass);
		
		JLabel lblNewLabel_2 = new JLabel("Don't have an account?");
		lblNewLabel_2.setBounds(96, 348, 147, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel registerLb = new JLabel("Register");
		registerLb.setForeground(SystemColor.textHighlight);
		registerLb.setFont(new Font("Tahoma", Font.PLAIN, 14));
		registerLb.setHorizontalAlignment(SwingConstants.RIGHT);
		registerLb.setBounds(253, 342, 73, 23);
		contentPane.add(registerLb);
		
		JLabel lblLogin = new JLabel("LOGIN");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setForeground(SystemColor.textHighlight);
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLogin.setBounds(102, 109, 218, 39);
		contentPane.add(lblLogin);
	}
}
