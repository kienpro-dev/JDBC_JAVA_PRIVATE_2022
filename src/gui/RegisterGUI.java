package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.AccountDAO;
import dao.MemberDAO;
import model.Account;
import model.Member;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.UUID;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegisterGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernameTf;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
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
	public RegisterGUI() {
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
		lblNewLabel.setBounds(102, 30, 218, 39);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(96, 135, 81, 17);
		contentPane.add(lblNewLabel_1);
		
		usernameTf = new JTextField();
		usernameTf.setBounds(96, 162, 230, 17);
		contentPane.add(usernameTf);
		usernameTf.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Retype password:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(96, 255, 127, 17);
		contentPane.add(lblNewLabel_1_1);
		
		passwordField = new JPasswordField();
		passwordField.setEchoChar('*');
		passwordField.setBounds(96, 288, 230, 17);
		contentPane.add(passwordField);
		
		JButton loginBtn = new JButton("REGISTER");
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(usernameTf.getText().length() == 0 || passwordField.getPassword().length == 0 || passwordField_1.getPassword().length == 0) {
					JOptionPane.showMessageDialog(loginBtn, "You must enter all information");
					return;
				} else if(!new String(passwordField.getPassword()).equals(new String(passwordField_1.getPassword()))) {
					JOptionPane.showMessageDialog(loginBtn, "Password does not match");
					return;
				}
				
				String username = usernameTf.getText();
				String password = new String(passwordField.getPassword());
				String role = "User";
				Account account = new Account(username, password, role);
				
				LocalDate localDate = LocalDate.now();
		        Date dayStart = Date.valueOf(localDate);
		        LocalDate resultDate = localDate.plusDays(30);
		        Date dayEnd = Date.valueOf(resultDate);

				Member member = new Member(UUID.randomUUID().toString(), username, 18, dayStart, dayEnd, username);
				MemberDAO memberDAO = new MemberDAO();
				AccountDAO accountDAO = new AccountDAO();
				try {
					
					accountDAO.add(account);
					memberDAO.add(member, username);
					JOptionPane.showMessageDialog(loginBtn, "Register successfully");
					new UserGUI(account).setVisible(true);
					dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		loginBtn.setBackground(SystemColor.textHighlight);
		loginBtn.setForeground(SystemColor.text);
		loginBtn.setBounds(96, 343, 230, 23);
		contentPane.add(loginBtn);
		
		JCheckBox showPass = new JCheckBox("Show password");
		showPass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(showPass.isSelected()) {
					passwordField.setEchoChar('\u0000');
					passwordField_1.setEchoChar('\u0000');
				} else {
					passwordField.setEchoChar('*');
					passwordField_1.setEchoChar('*');
				}
			}
		});
		showPass.setBounds(93, 312, 130, 23);
		contentPane.add(showPass);
		
		JLabel registerLb = new JLabel("Back");
		registerLb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new LoginGUI().setVisible(true);
				dispose();
			}
		});
		registerLb.setForeground(SystemColor.textHighlight);
		registerLb.setFont(new Font("Tahoma", Font.PLAIN, 14));
		registerLb.setHorizontalAlignment(SwingConstants.RIGHT);
		registerLb.setBounds(253, 381, 73, 23);
		contentPane.add(registerLb);
		
		JLabel lblRegister = new JLabel("REGISTER");
		lblRegister.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegister.setForeground(SystemColor.textHighlight);
		lblRegister.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRegister.setBounds(102, 67, 218, 39);
		contentPane.add(lblRegister);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Password:");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1_1.setBounds(96, 196, 81, 17);
		contentPane.add(lblNewLabel_1_1_1);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setEchoChar('*');
		passwordField_1.setBounds(96, 229, 230, 17);
		contentPane.add(passwordField_1);
	}
}
