package main;

import java.sql.SQLException;
import java.sql.Date;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dao.AccountDAO;
import dao.EquipmentDAO;
import dao.MemberDAO;
import dao.MemberPTDAO;
import dao.PersonalTrainerDAO;
import dao.RepairCenterDAO;
import dao.RepairEquipmentDAO;

import model.Account;
import model.Equipment;
import model.Member;
import model.MemberPT;
import model.PersonalTrainer;
import model.RepairCenter;
import model.RepairEquipment;

import service.AccountService;
import service.EquipmentService;
import service.MemberPTService;
import service.MemberService;
import service.PersonalTrainerService;
import service.RepairCenterService;

import service.impl.AccountServiceImpl;
import service.impl.EquipmentServiceImpl;
import service.impl.MemberPTServiceImpl;
import service.impl.MemberServiceImpl;
import service.impl.PersonalTrainerServiceImpl;
import service.impl.RepairCenterServiceImpl;

import static main.MainAccount.getAllAccounts;

public class MainUser {
	public static Scanner sc = new Scanner(System.in);

	public static void login() throws SQLException {
		// Minimum 6 characters, or number, don't have special characters
		String username_regex = "^[\\w+]{6,}$";
		// Minimum 8 characters, at least 1 letter and 1 number
		String password_regex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";

		String username;
		String password;

		Pattern pattern;
		Matcher matcher;
		do {
			System.out.print(" Enter your username: ");
			username = sc.next();
			pattern = Pattern.compile(username_regex);
			matcher = pattern.matcher(username);
			if (matcher.find())
				break;
			else
				System.out.println(" Username is not valid: Minimum 6 characters, or number, don't have special characters");
		} while (!matcher.find());

		do {
			System.out.print(" Enter your password: ");
			password = sc.next();
			pattern = Pattern.compile(password_regex);
			matcher = pattern.matcher(password);
			if (matcher.find())
				break;
			else
				System.out.println(" Password is not valid: Minimum 8 characters, at least 1 letter and 1 number");
		} while (!matcher.find());

		boolean check = false;
		for (Account acc : getAllAccounts()) {
			if (acc.getRole().equals("Admin") && acc.getUsername().equals(username)
					&& acc.getPassword().equals(password)) {
				System.out.println("Welcome to Management Studio!");
				int chooseAdmin;
				do {
					System.out.println("*---------- MANAGEMENT STUDIO ----------*");
					System.out.println("---  1. Add new member                ---");
					System.out.println("---  2. Add new equipment             ---");
					System.out.println("---  3. Add new PT                    ---");
					System.out.println("---  4. Delete an exist member        ---");
					System.out.println("---  5. Delete an exist equipment     ---");
					System.out.println("---  6. Delete an exist PT            ---");
					System.out.println("---  7. Show list members             ---");
					System.out.println("---  8. Show list equipments          ---");
					System.out.println("---  9. Show list PT                  ---");
					System.out.println("---  10. Mantain equipment            ---");
					System.out.println("---  11. Statistic members of PT      ---");
					System.out.println("---  12. Statistic PT's income        ---");
					System.out.println("---  13. Exit                         ---");
					System.out.println("*---------------------------------------*");
					System.out.print("Enter choose :  ");
					chooseAdmin = sc.nextInt();
					sc.nextLine();

					switch (chooseAdmin) {
						case 1:
							addMember();
							break;
						case 2:
							addEquipment();
							break;
						case 3:
							addPT();
							break;
						case 4:
							removeMemberByID();
							break;
						case 5:
							removeEquipmentByID();
							break;
						case 6:
							removePT();
							break;
						case 7:
							printListMembers();
							break;
						case 8:
							printListEquipments();
							break;
						case 9:
							printListPTs();
							break;
						case 10:
							maintainEquipment();
							break;
						case 11:
							statisticMemberOfPT();
							break;
						case 12:
							statisticPTIncome();
							break;
						case 13:
							break;
						default:
							System.out.println("Option not found, try again!");
					}
				} while (chooseAdmin != 13);
				check = true;
				break;
			}

			if (acc.getRole().equals("User") && acc.getUsername().equals(username)
					&& acc.getPassword().equals(password)) {
				MemberDAO mptDAO = new MemberDAO();
				List<Member> list = mptDAO.findAll();
				String memberID = "";
				for (Member member : list) {
					if (member.getUsername().equals(username)) {
						memberID = member.getMemberId();
						break;
					}
				}

				System.out.println("Welcome back to your account");
				int chooseUser;

				do {

					System.out.println("*--- MANAGE YOUR GYM SERVICES ---*");
					System.out.println("--  1. Show my gym card         --");
					System.out.println("--  2. Update my card           --");
					System.out.println("--  3. Hire PT                  --");
					System.out.println("--  4. Exit                     --");
					System.out.println("*--------------------------------*");
					System.out.print("Enter choose :  ");
					chooseUser = sc.nextInt();
					sc.nextLine();

					switch (chooseUser) {
						case 1:
							printMemberInfo(memberID);
							break;
						case 2:
							editMemberInfor(memberID);
							break;
						case 3:
							hirePT(memberID);
							break;
						case 4:
							break;
						default:
							System.out.println("Option not found, try again!");
					}
				} while (chooseUser != 4);
				check = true;
				break;
			}
		}
		if (!check) {
			System.out.println("Accounnt not found!");
		}

	}

	public static void register() throws SQLException {
		int choose;
		do {
			System.out.println("*------- Choose regist options -------*");
			System.out.println("---  1. Regist for an exist member  ---");
			System.out.println("---  2. Regist for a new member     ---");
			System.out.println("---  3. Done!                       ---");
			System.out.println("*-------------------------------------*");
			System.out.print("Enter choose: ");
			choose = sc.nextInt();
			sc.nextLine();

			AccountService accService = new AccountServiceImpl();
			Account acc = new Account();
			AccountDAO accountDAO = new AccountDAO();

			MemberService memService = new MemberServiceImpl();
			Member mem = new Member();
			MemberDAO memberDAO = new MemberDAO();
			switch (choose) {
				case 1:
					System.out.print("Enter ID of member: ");
					String ID = sc.next();

					if (memberDAO.find(ID) != null && memberDAO.find(ID).getUsername() == null) {
						accService.input(acc);

						accountDAO.add(acc);

						memberDAO.find(ID).setUsername(acc.getUsername());
						memberDAO.updateUsername(acc.getUsername(), ID);
						System.out.println("Regist account success!");
					} else {
						System.out.println("Account not found or exist username!");
					}
					break;
				case 2:
					memService.input(mem);

					if (memberDAO.find(mem.getMemberId()) != null) {
						System.out.println("New member has duplicated ID. Regist failed!");
						break;
					}

					accService.input(acc);

					accountDAO.add(acc);

					mem.setUsername(acc.getUsername());

					memberDAO.add(mem, mem.getUsername());
					System.out.println("Regist account success!");
					break;
				default:
					System.out.println("Option not found, try again!");
			}
		} while (choose != 3);
	}

	public static void addMember() throws SQLException {
		MemberService memService = new MemberServiceImpl();
		Member memInput = new Member();
		memService.input(memInput);

		MemberDAO memberDAO = new MemberDAO();
		Member memFind = memberDAO.find(memInput.getMemberId());

		if (memFind == null) {
			memberDAO.add(memInput, null);
			System.out.println("New member added successfully!");
		} else {
			System.out.println("New member has duplicated ID. Add failed!");
		}
	}

	public static void removeMemberByID() throws SQLException {
		System.out.print("Enter ID of member to remove: ");
		String ID = sc.next();

		MemberDAO memberDAO = new MemberDAO();
		Member memFind = memberDAO.find(ID);

		if (memFind != null) {
			MemberPTDAO memberPTDAO = new MemberPTDAO();
			AccountDAO accountDAO = new AccountDAO();

			LocalDate today = LocalDate.now();
			if (memFind.getMemberDayEnd().toLocalDate().compareTo(today) >= 0) {
				System.out.print("This member is still here, want to remove ? (yes/no): ");
				String choose = sc.nextLine();
				while (!choose.equalsIgnoreCase("yes") && !choose.equalsIgnoreCase("no")) {
					System.out.print("Try again! (yes/no): ");
					choose = sc.nextLine();
				}

				if (choose.equalsIgnoreCase("yes")) {
					memberPTDAO.delete(ID);
					memberDAO.delete(ID);
					accountDAO.delete(memFind.getUsername());
					System.out.println("Remove member successfully!");
				} else {
					System.out.println("Nothing changed!");
				}
			} else {
				memberPTDAO.delete(ID);
				memberDAO.delete(ID);
				accountDAO.delete(memFind.getUsername());
				System.out.println("Remove member successfully!");
			}

		} else {
			System.out.println("Not found member has ID: " + ID);
		}

	}

	public static void editMemberInfor(String ID) throws SQLException {
		// System.out.print("Enter ID of member to edit: ");
		// String ID = sc.next();

		MemberDAO memberDAO = new MemberDAO();
		Member memFind = memberDAO.find(ID);

		if (memFind != null) {
			int choose;
			do {
				System.out.println("*---- Choose edit options ----*");
				System.out.println("---  1. Name, age           ---");
				System.out.println("---  2. Extend gym card     ---");
				System.out.println("---  3. Done!               ---");
				System.out.println("*-----------------------------*");
				System.out.print("Enter choose: ");
				choose = sc.nextInt();
				sc.nextLine();

				switch (choose) {
					case 1:
						Member memNew = new Member();
						System.out.print("Enter new name: ");
						memNew.setMemberName(sc.nextLine());
						do {
							System.out.print("Enter new age: ");
							memNew.setMemberAge(sc.nextInt());
							sc.nextLine();
							if (memNew.getMemberAge() <= 0) {
								System.out.println("Age is not valid, try again!");
							}
						} while (memNew.getMemberAge() <= 0);

						memberDAO.update(memNew, ID);
						System.out.println("Update member's name and age successfully!");
						break;
					case 2:
						SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
						System.out.println("Day start is: " + formatter.format(memFind.getMemberDayStart()));
						System.out.println("Day end is: " + formatter.format(memFind.getMemberDayEnd()));

						int month;
						do {
							System.out.print("Enter the number of month to extend: ");
							month = sc.nextInt();
							sc.nextLine();
							if (month <= 0) {
								System.out.println("Month is not valid, try again!");
							}
						} while (month <= 0);

						LocalDate localDate = memFind.getMemberDayEnd().toLocalDate();
						LocalDate resultDate = localDate.plusDays(30 * month);
						Date newDayEnd = Date.valueOf(resultDate);
						memFind.setMemberDayEnd(newDayEnd);
						memberDAO.updateDate(newDayEnd, ID);
						System.out.println("Extend gym card for " + month + " months successfully!");
						break;
					case 3:
						break;
					default:
						System.out.println("Option not found, try again!");
				}
			} while (choose != 3);
		} else {
			System.out.println("Not found member has ID: " + ID);
		}
	}

	public static void findMemberByID() throws SQLException {
		System.out.print("Enter ID's member to find: ");
		String ID = sc.next();

		MemberDAO memberDAO = new MemberDAO();
		Member memFind = memberDAO.find(ID);

		if (memFind != null) {
			MemberService memService = new MemberServiceImpl();

			System.out.printf("%-15s %-20s %-10s %-15s %-15s\n", "ID", "Name", "Age", "DayStart", "DayEnd");
			memService.output(memFind);
		} else {
			System.out.println("Not found member has ID: " + ID);
		}

	}

	public static void printMemberInfo(String ID) throws SQLException {
		MemberDAO memberDAO = new MemberDAO();
		Member member = memberDAO.find(ID);

		MemberService memService = new MemberServiceImpl();

		System.out.printf("%-15s %-20s %-10s %-15s %-15s\n", "ID", "Name", "Age", "DayStart", "DayEnd");
		memService.output(member);
	}

	public static void printListMembers() throws SQLException {
		MemberDAO memberDAO = new MemberDAO();
		List<Member> list = memberDAO.findAll();

		MemberService memService = new MemberServiceImpl();

		System.out.printf("%-15s %-20s %-10s %-15s %-15s\n", "ID", "Name", "Age", "DayStart", "DayEnd");
		for (Member member : list) {
			memService.output(member);
		}
	}

	public static void addEquipment() throws SQLException {
		EquipmentService equipService = new EquipmentServiceImpl();
		Equipment equipInput = new Equipment();
		equipService.input(equipInput);

		EquipmentDAO equipmentDAO = new EquipmentDAO();
		Equipment equipFind = equipmentDAO.find(equipInput.getEquipmentId());

		if (equipFind == null) {
			equipmentDAO.add(equipInput);
			System.out.println("New equipment added successfully!");
		} else {
			System.out.println("New equipment has duplicated ID. Add failed!");
		}
	}

	public static void removeEquipmentByID() throws SQLException {
		System.out.print("Enter ID of equipment to remove: ");
		String ID = sc.next();

		EquipmentDAO equipmentDAO = new EquipmentDAO();
		Equipment equipFind = equipmentDAO.find(ID);

		if (equipFind != null) {
			equipmentDAO.delete(ID);

			RepairEquipmentDAO reDAO = new RepairEquipmentDAO();
			reDAO.delete(ID);
			System.out.println("Remove equipment successfully!");
		} else {
			System.out.println("Not found equipment has ID: " + ID);
		}

	}

	public static void editEquipmentInfor() throws SQLException {
		System.out.print("Enter ID's equipment to edit: ");
		String ID = sc.next();

		EquipmentDAO equipmentDAO = new EquipmentDAO();
		Equipment equipFind = equipmentDAO.find(ID);

		if (equipFind != null) {
			int choose;
			do {
				System.out.println("Choose edit options");
				System.out.println("1. Name");
				System.out.println("2. Done!");
				System.out.print("Enter choose: ");
				choose = sc.nextInt();
				sc.nextLine();

				switch (choose) {
					case 1:
						Equipment equipNew = new Equipment();
						System.out.println("Enter new name: ");
						equipNew.setEquipmentName(sc.next());
						equipNew.setEquipmentDurability(equipFind.getEquipmentDurability());

						equipmentDAO.update(equipNew, ID);
						System.out.println("Update equipment's name successfully");
						break;
					case 2:
						break;
					default:
						System.out.println("Option not found, try again!");
				}
			} while (choose != 2);
		} else {
			System.out.println("Not found equipment has ID: " + ID);
		}
	}

	public static void printListEquipments() throws SQLException {
		EquipmentDAO equipmentDAO = new EquipmentDAO();
		List<Equipment> list = equipmentDAO.findAll();

		EquipmentService equipService = new EquipmentServiceImpl();

		System.out.printf("%-15s %-20s %-15s\n", "ID", "Name", "Durability");
		for (Equipment equipment : list) {
			equipService.output(equipment);
		}
	}

	public static void addPT() throws SQLException {
		PersonalTrainerService ptService = new PersonalTrainerServiceImpl();
		PersonalTrainer ptInput = new PersonalTrainer();
		ptService.input(ptInput);

		PersonalTrainerDAO personalTrainerDAO = new PersonalTrainerDAO();
		PersonalTrainer ptFind = personalTrainerDAO.find(ptInput.getPtId());

		if (ptFind == null) {
			personalTrainerDAO.add(ptInput);
			System.out.println("New Personal Trainer added successfully!");
		} else {
			System.out.println("New Personal Trainer has duplicated ID. Add failed!");
		}
	}

	public static void removePT() throws SQLException {
		System.out.print("Enter ID of PT to remove: ");
		String ID = sc.nextLine();

		PersonalTrainerDAO personalTrainerDAO = new PersonalTrainerDAO();
		PersonalTrainer ptFind = personalTrainerDAO.find(ID);

		if (ptFind != null) {
			MemberPTDAO mptDAO = new MemberPTDAO();
			if (!mptDAO.findPT().isEmpty()) {
				System.out.print("This PT was hired by member! Want to remove ? (yes/no): ");

				String choose = sc.nextLine();
				while (!choose.equalsIgnoreCase("yes") && !choose.equalsIgnoreCase("no")) {
					System.out.print("Try again! (yes/no): ");
					choose = sc.nextLine();
				}

				if (choose.equalsIgnoreCase("yes")) {
					mptDAO.deletePT(ID);
					personalTrainerDAO.delete(ID);
					System.out.println("Remove Personal Trainer successfully!");
				} else {
					System.out.println("Nothing changed!");
				}

			} else {
				personalTrainerDAO.delete(ID);
				System.out.println("Remove Personal Trainer successfully!");
			}

		} else {
			System.out.println("Not found Personal Trainer has ID: " + ID);
		}
	}

	public static void printListPTs() throws SQLException {
		PersonalTrainerDAO ptDAO = new PersonalTrainerDAO();
		List<PersonalTrainer> list = ptDAO.findAll();

		PersonalTrainerService ptService = new PersonalTrainerServiceImpl();

		System.out.printf("%-15s %-20s %-10s %-15s\n", "ID", "Name", "Gender", "Price(day)");
		for (PersonalTrainer pt : list) {
			ptService.output(pt);
		}
	}

	public static void printListRCs() throws SQLException {
		RepairCenterDAO ptDAO = new RepairCenterDAO();
		List<RepairCenter> list = ptDAO.findAll();

		RepairCenterService rcService = new RepairCenterServiceImpl();

		System.out.printf("%-15s %-20s %-15s\n", "ID", "Name", "Price");
		for (RepairCenter rc : list) {
			rcService.output(rc);
		}
	}

	public static void printListMemberAndPT() throws SQLException {
		MemberPTDAO mptDAO = new MemberPTDAO();
		List<MemberPT> list = mptDAO.findAll();

		MemberPTService mptService = new MemberPTServiceImpl();

		System.out.printf("%-15s %-15s %-10s %-10s\n", "MemberID", "PTID", "DayLeft", "AllPrice");
		for (MemberPT memberPT : list) {
			mptService.output(memberPT);
		}

	}

	public static void hirePT(String memberID) throws SQLException {
		// System.out.print("Enter your ID: ");
		// String memberID = sc.next();

		MemberDAO memberDAO = new MemberDAO();
		Member memFind = memberDAO.find(memberID);

		LocalDate localDate = LocalDate.now();
		if (memFind != null) {
			if(memFind.getMemberDayEnd().toLocalDate().compareTo(localDate) <= 0) {
				System.out.println("Your gym card has expired, please regist more month!");
				return;
			}
			PersonalTrainerDAO ptDAO = new PersonalTrainerDAO();
			List<PersonalTrainer> list = ptDAO.findAll();
			if (list.isEmpty()) {
				System.out.println("Run out of PT!");
			} else {
				System.out.println("---List of Personal Trainers---");
				printListPTs();

				String PTID;
				PersonalTrainer pt;
				do {
					System.out.print("Enter PT's ID want to hire: ");
					PTID = sc.next();
					pt = ptDAO.find(PTID);
					if (pt == null) {
						System.out.println("Not found Personal Trainer has ID: " + PTID);
					} else {
						int n;
						do {
							System.out.print("Enter the number of day: ");
							n = sc.nextInt();
							sc.nextLine();
							if (n <= 0) {
								System.out.println("Not valid, try again!");
							}
						} while (n <= 0);
						LocalDate resultDate = localDate.plusDays(n);
						if (memFind.getMemberDayEnd().toLocalDate().compareTo(resultDate) < 0) {
							System.out.println("You can't hire PT in " + n + " days!");
							System.out.println("Your gym card will be expired before that, please regist more month!");
						} else {
							MemberPTDAO mptDAO = new MemberPTDAO();
							MemberPT mpt = new MemberPT();

							mpt.setMem(memFind);
							mpt.setPt(pt);
							mpt.setTimeLeft(n);

							mptDAO.add(mpt);
							System.out.println("Hire PT " + PTID + " successfully!");
						}

					}
				} while (pt == null);
			}
		} else {
			System.out.println("Not found member has ID: " + memberID);
		}
	}

	public static void maintainEquipment() throws SQLException {
		System.out.print("Enter Equipment's ID want to maintain: ");
		String eID = sc.next();

		EquipmentDAO equipmentDAO = new EquipmentDAO();
		Equipment equipFind = equipmentDAO.find(eID);

		if (equipFind != null) {
			RepairCenterDAO rcDAO = new RepairCenterDAO();
			List<RepairCenter> list = rcDAO.findAll();
			if (list.isEmpty()) {
				System.out.println("Run out of Repair Center!");
			} else {
				System.out.println("---List of Repair Centers---");
				printListRCs();

				String rcID;
				RepairCenter rc;
				do {
					System.out.print("Enter RC's ID want to send: ");
					rcID = sc.next();
					rc = rcDAO.find(rcID);
					if (rc == null) {
						System.out.println("Not found Repair Center has ID: " + rcID);
					} else {
						equipFind.setEquipmentDurability(365);
						equipmentDAO.update(equipFind, eID);

						RepairEquipmentDAO reDAO = new RepairEquipmentDAO();
						RepairEquipment re = new RepairEquipment();

						re.setE(equipFind);
						re.setRc(rc);
						re.setTimeLeft(7);

						reDAO.add(re);
						System.out.println("Maintain equipment successfully!");
						System.out.println("It had mantained at " + rcID + ", ready to use");
					}
				} while (rc == null);
			}
		} else {
			System.out.println("Not found equipment has ID: " + eID);
		}

	}

	public static void statisticMemberOfPT() throws SQLException {
		MemberPTDAO mptDAO = new MemberPTDAO();
		List<MemberPT> list = mptDAO.statisticNumberMemberOfPT();

		System.out.printf("%-20s %-15s\n", "Name", "NumberOfMember");
		for (MemberPT memberPT : list) {
			System.out.printf("%-20s %-15d\n", memberPT.getPt().getPtName(), memberPT.getNumberOfMember());
		}
	}

	public static void statisticPTIncome() throws SQLException {
		MemberPTDAO mptDAO = new MemberPTDAO();
		List<MemberPT> list = mptDAO.statisticPTIncome();

		System.out.printf("%-20s %-10s %-15s\n", "Name", "Gender", "Income");
		for (MemberPT memberPT : list) {
			System.out.printf("%-20s %-10s %-15d\n", memberPT.getPt().getPtName(), memberPT.getPt().getPtGender(),
					memberPT.getIncome());
		}

	}

}
