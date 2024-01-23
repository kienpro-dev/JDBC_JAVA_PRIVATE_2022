package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.AccountDAO;
import dao.EquipmentDAO;
import dao.MemberDAO;
import dao.MemberPTDAO;
import dao.PersonalTrainerDAO;
import model.Account;
import model.Equipment;
import model.Member;
import model.MemberPT;
import model.PersonalTrainer;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.ActionEvent;

public class AdminGUI extends JFrame {
	private Account account;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable revenueTb;
	private JTable memberTb;
	private JTable equipmentTb;
	private JTable ptTb;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminGUI frame = new AdminGUI(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public AdminGUI(Account account) throws SQLException {
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
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 103, 655, 348);
		contentPane.add(tabbedPane);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Revenue", null, panel_3, null);
		panel_3.setLayout(null);
		
		JScrollPane revenuePane = new JScrollPane();
		revenuePane.setBounds(10, 11, 630, 264);
		panel_3.add(revenuePane);
		
		MemberPTDAO memberPTDAO = new MemberPTDAO();
		List<MemberPT> memberPTs = memberPTDAO.statisticNumberMemberOfPT();
		List<MemberPT> ptIncome = memberPTDAO.statisticPTIncome();
		
		revenueTb = new JTable();
		String[] columnNames = new String[] {"Personal Trainer", "Gender", "Income", "Number of members"};
		DefaultTableModel model = new DefaultTableModel(null, columnNames);
		
		revenueTb.setModel(model);
		int sum = 0;
		for (MemberPT mem1 : memberPTs) { 
			for (MemberPT mem2 : ptIncome) {
				if(mem1.getPt().getPtName().equals(mem2.getPt().getPtName())) {
					model.addRow(new Object[]{mem1.getPt().getPtName(), mem1.getPt().getPtGender(), mem2.getIncome(), mem1.getNumberOfMember()});
					sum += mem2.getIncome();
				}
			}
			
		}
		
		revenuePane.setViewportView(revenueTb);
		
		JLabel revenue = new JLabel("Revenue:");
		revenue.setFont(new Font("Tahoma", Font.BOLD, 14));
		revenue.setHorizontalAlignment(SwingConstants.RIGHT);
		revenue.setBounds(505, 286, 135, 23);
		panel_3.add(revenue);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Member", null, panel, null);
		panel.setLayout(null);
		
		JScrollPane memberPane = new JScrollPane();
		memberPane.setBounds(10, 11, 630, 264);
		panel.add(memberPane);
		
		MemberDAO memberDAO = new MemberDAO();
		List<Member> members = memberDAO.findAll();
		
		String[] columnNames2 = new String[] {"ID", "Name", "Age", "Day Start", "Day End"};
		DefaultTableModel model2 = new DefaultTableModel(null, columnNames2);
			
		memberTb = new JTable();
		memberTb.setModel(model2);
		
		for(Member member : members) {
			model2.addRow(new Object[] {member.getMemberId(), member.getMemberName(), member.getMemberAge(), member.getMemberDayStart(), member.getMemberDayEnd()});
		}
		
		sum += members.size() * 200000;
		
		revenue.setText("Total: " + sum);
		
		memberPane.setViewportView(memberTb);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setForeground(SystemColor.text);
		btnUpdate.setBackground(SystemColor.textHighlight);
		btnUpdate.setBounds(452, 286, 89, 23);
		panel.add(btnUpdate);
		
		JButton btnNewButton_1_1 = new JButton("Delete");
		btnNewButton_1_1.setForeground(SystemColor.text);
		btnNewButton_1_1.setBackground(SystemColor.textHighlight);
		btnNewButton_1_1.setBounds(551, 286, 89, 23);
		panel.add(btnNewButton_1_1);
		
		JButton btnNewButton_2_1 = new JButton("Add");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new RegisterGUI().setVisible(true);
				dispose();
			}
		});
		btnNewButton_2_1.setForeground(SystemColor.text);
		btnNewButton_2_1.setBackground(SystemColor.textHighlight);
		btnNewButton_2_1.setBounds(353, 286, 89, 23);
		panel.add(btnNewButton_2_1);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Equipment", null, panel_1, null);
		panel_1.setLayout(null);
		
		JScrollPane EquipmentPane = new JScrollPane();
		EquipmentPane.setBounds(10, 11, 630, 264);
		panel_1.add(EquipmentPane);
		
		EquipmentDAO equipmentDAO = new EquipmentDAO();
		List<Equipment> equipments = equipmentDAO.findAll();
		
		String[] columnNames3 = new String[] {"ID", "Name", "Durability"};
		DefaultTableModel model3 = new DefaultTableModel(null, columnNames3);
			
		equipmentTb = new JTable();
		equipmentTb.setModel(model3);
		
		for(Equipment equipment : equipments) {
			model3.addRow(new Object[] {equipment.getEquipmentId(), equipment.getEquipmentName(), equipment.getEquipmentDurability()});
		}
		
		EquipmentPane.setViewportView(equipmentTb);
		
		JButton btnNewButton_2_1_1 = new JButton("Add");
		btnNewButton_2_1_1.setForeground(SystemColor.text);
		btnNewButton_2_1_1.setBackground(SystemColor.textHighlight);
		btnNewButton_2_1_1.setBounds(353, 286, 89, 23);
		panel_1.add(btnNewButton_2_1_1);
		
		JButton btnUpdate_1 = new JButton("Update");
		btnUpdate_1.setForeground(SystemColor.text);
		btnUpdate_1.setBackground(SystemColor.textHighlight);
		btnUpdate_1.setBounds(452, 286, 89, 23);
		panel_1.add(btnUpdate_1);
		
		JButton btnNewButton_1_1_1 = new JButton("Delete");
		btnNewButton_1_1_1.setForeground(SystemColor.text);
		btnNewButton_1_1_1.setBackground(SystemColor.textHighlight);
		btnNewButton_1_1_1.setBounds(551, 286, 89, 23);
		panel_1.add(btnNewButton_1_1_1);
		
		JButton btnNewButton_2_1_1_1 = new JButton("Maintain");
		btnNewButton_2_1_1_1.setForeground(SystemColor.text);
		btnNewButton_2_1_1_1.setBackground(SystemColor.textHighlight);
		btnNewButton_2_1_1_1.setBounds(10, 286, 89, 23);
		panel_1.add(btnNewButton_2_1_1_1);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Personal Trainer", null, panel_2, null);
		panel_2.setLayout(null);
		
		JScrollPane ptPane = new JScrollPane();
		ptPane.setBounds(10, 11, 630, 264);
		panel_2.add(ptPane);
		
		ptTb = new JTable();
		ptTb.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Name", "Gender", "Price (day)"
			}
		));
		
		PersonalTrainerDAO personalTrainerDAO = new PersonalTrainerDAO();
		List<PersonalTrainer> pts = personalTrainerDAO.findAll();
		
		String[] columnNames4 = new String[] {"ID", "Name", "Gender", "Price (day)"};
		DefaultTableModel model4 = new DefaultTableModel(null, columnNames4);
			
		ptTb = new JTable();
		ptTb.setModel(model4);
		
		for(PersonalTrainer pt : pts) {
			model4.addRow(new Object[] {pt.getPtId(), pt.getPtName(), pt.getPtGender(), pt.getPtHirePricePerDay()});
		}
		
		ptPane.setViewportView(ptTb);
		
		JButton btnNewButton_2_1_1_2 = new JButton("Add");
		btnNewButton_2_1_1_2.setForeground(SystemColor.text);
		btnNewButton_2_1_1_2.setBackground(SystemColor.textHighlight);
		btnNewButton_2_1_1_2.setBounds(353, 286, 89, 23);
		panel_2.add(btnNewButton_2_1_1_2);
		
		JButton btnUpdate_1_1 = new JButton("Update");
		btnUpdate_1_1.setForeground(SystemColor.text);
		btnUpdate_1_1.setBackground(SystemColor.textHighlight);
		btnUpdate_1_1.setBounds(452, 286, 89, 23);
		panel_2.add(btnUpdate_1_1);
		
		JButton btnNewButton_1_1_1_1 = new JButton("Delete");
		btnNewButton_1_1_1_1.setForeground(SystemColor.text);
		btnNewButton_1_1_1_1.setBackground(SystemColor.textHighlight);
		btnNewButton_1_1_1_1.setBounds(551, 286, 89, 23);
		panel_2.add(btnNewButton_1_1_1_1);
	}
}
