package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.MemberDAO;
import model.Account;
import model.Member;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserGUI extends JFrame {
	private Account account;
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField idTf;
	private JTextField nameTf;
	private JTextField ageTf;
	private JTextField startTf;
	private JTextField remainTf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserGUI frame = new UserGUI(null);
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
	public UserGUI(Account account) {
		this.account = account;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 691, 501);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("GYM FITNESS");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(SystemColor.textHighlight);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setBounds(228, 11, 218, 39);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("LOG OUT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LoginGUI().setVisible(true);
				dispose();
			}
		});
		btnNewButton.setForeground(SystemColor.text);
		btnNewButton.setBackground(SystemColor.textHighlight);
		btnNewButton.setBounds(462, 58, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Exit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setBackground(SystemColor.textHighlight);
		btnNewButton_1.setForeground(SystemColor.text);
		btnNewButton_1.setBounds(561, 58, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblYourInformation = new JLabel("Your information");
		lblYourInformation.setHorizontalAlignment(SwingConstants.CENTER);
		lblYourInformation.setForeground(SystemColor.textHighlight);
		lblYourInformation.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblYourInformation.setBounds(29, 105, 218, 39);
		contentPane.add(lblYourInformation);
		
		JLabel lblNewLabel_1 = new JLabel("ID:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(56, 173, 80, 20);
		contentPane.add(lblNewLabel_1);
		
		idTf = new JTextField();
		idTf.setEditable(false);
		idTf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		idTf.setBounds(190, 174, 134, 20);
		contentPane.add(idTf);
		idTf.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Name:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(377, 173, 80, 20);
		contentPane.add(lblNewLabel_1_1);
		
		nameTf = new JTextField();
		nameTf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nameTf.setColumns(10);
		nameTf.setBounds(462, 173, 134, 20);
		contentPane.add(nameTf);
		
		JLabel lblNewLabel_1_2 = new JLabel("Age:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_2.setBounds(377, 216, 80, 20);
		contentPane.add(lblNewLabel_1_2);
		
		ageTf = new JTextField();
		ageTf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ageTf.setColumns(10);
		ageTf.setBounds(462, 216, 134, 20);
		contentPane.add(ageTf);
		
		JLabel lblNewLabel_1_3 = new JLabel("Day Start:");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_3.setBounds(56, 216, 107, 20);
		contentPane.add(lblNewLabel_1_3);
		
		startTf = new JTextField();
		startTf.setEditable(false);
		startTf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		startTf.setColumns(10);
		startTf.setBounds(190, 216, 134, 20);
		contentPane.add(startTf);
		
		remainTf = new JTextField();
		remainTf.setEditable(false);
		remainTf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		remainTf.setColumns(10);
		remainTf.setBounds(190, 258, 134, 20);
		contentPane.add(remainTf);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("Remain Day:");
		lblNewLabel_1_3_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_3_1.setBounds(56, 258, 127, 20);
		contentPane.add(lblNewLabel_1_3_1);
		
		JLabel lblNewLabel_1_3_1_1 = new JLabel("Your PT:");
		lblNewLabel_1_3_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_3_1_1.setBounds(56, 300, 127, 20);
		contentPane.add(lblNewLabel_1_3_1_1);
		
		JButton extendBtn = new JButton("EXTEND GYM CARD");
		extendBtn.setForeground(SystemColor.text);
		extendBtn.setBackground(SystemColor.textHighlight);
		extendBtn.setBounds(377, 255, 219, 23);
		contentPane.add(extendBtn);
		
		JButton hireBtn = new JButton("HIRE PERSONAL TRAINER");
		hireBtn.setForeground(SystemColor.text);
		hireBtn.setBackground(SystemColor.textHighlight);
		hireBtn.setBounds(377, 300, 219, 23);
		contentPane.add(hireBtn);
		
		JButton saveBTn = new JButton("SAVE");
		saveBTn.setForeground(SystemColor.text);
		saveBTn.setBackground(SystemColor.textHighlight);
		saveBTn.setBounds(561, 369, 89, 23);
		contentPane.add(saveBTn);
		
		JLabel lblNewLabel_2 = new JLabel("Change password?");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new ChangePasswordGUI(account).setVisible(true);
				dispose();
			}
		});
		lblNewLabel_2.setForeground(SystemColor.textHighlight);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(43, 373, 120, 19);
		contentPane.add(lblNewLabel_2);
		
		JComboBox ptCbb = new JComboBox();
		ptCbb.setBounds(190, 302, 134, 22);
		contentPane.add(ptCbb);
		
		MemberDAO memberDAO = new MemberDAO();
		try {
			Member member = memberDAO.findByUsername(account.getUsername());
			idTf.setText(member.getMemberId());
			startTf.setText(member.getMemberDayStart().toString());
			LocalDate now = LocalDate.now();
	        LocalDate then = member.getMemberDayEnd().toLocalDate();
	        if(now.compareTo(then)>0) {
	        	remainTf.setText("0");
	        } else {
				remainTf.setText(Long.toString(ChronoUnit.DAYS.between(now, then)));
	        }
	        nameTf.setText(member.getMemberName());
	        ageTf.setText(Integer.toString(member.getMemberAge()));
			
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
