package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.AccountDAO;
import model.Account;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ChangePasswordGUI extends JFrame {

	private Account account;
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField current;
	private JTextField newpass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangePasswordGUI frame = new ChangePasswordGUI(null);
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
	public ChangePasswordGUI(Account account) {
		this.account = account;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CHANGE PASSWORD");
		lblNewLabel.setForeground(SystemColor.textHighlight);
		lblNewLabel.setBackground(SystemColor.textHighlight);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(86, 11, 261, 65);
		contentPane.add(lblNewLabel);
		
		current = new JTextField();
		current.setBounds(160, 100, 187, 20);
		contentPane.add(current);
		current.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Current password:");
		lblNewLabel_1.setBounds(50, 103, 100, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("New password:");
		lblNewLabel_1_1.setBounds(50, 134, 100, 14);
		contentPane.add(lblNewLabel_1_1);
		
		newpass = new JTextField();
		newpass.setColumns(10);
		newpass.setBounds(160, 131, 187, 20);
		contentPane.add(newpass);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!current.getText().equals(account.getPassword())) {
					JOptionPane.showMessageDialog(btnNewButton, "Wrong current password");
					return;
				}
				AccountDAO accountDAO = new AccountDAO();
				try {
					accountDAO.update(account.getUsername(), newpass.getText());
					JOptionPane.showMessageDialog(btnNewButton, "Change password successfully");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				new UserGUI(account).setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBackground(SystemColor.textHighlight);
		btnNewButton.setForeground(SystemColor.text);
		btnNewButton.setBounds(172, 183, 89, 23);
		contentPane.add(btnNewButton);
	}
}
