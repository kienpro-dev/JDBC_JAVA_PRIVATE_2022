package main;

import java.sql.SQLException;

import java.util.List;
import java.util.Scanner;

import dao.AccountDAO;
import dao.MemberDAO;
import model.Account;
import model.Member;

import static dao.MemberDAO.getMemberName;

public class MainAccount {
    public static List<Account> getAllAccounts() throws SQLException {
        AccountDAO accountDAO = new AccountDAO();
        return accountDAO.getAccounts();
    }
    
    public static void forgotPassword() throws SQLException {
    	Scanner sc = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        
        System.out.print("Enter member's ID: ");
        String ID = sc.nextLine();
        
        MemberDAO memberDAO = new MemberDAO();
        Member mem = memberDAO.find(ID);
        
        if(mem != null) {
        	if(mem.getUsername().equals(username)) {
        		String newPassword;
                String confirmPassword;
                do {
                    System.out.print("Enter new password: ");
                    newPassword = sc.nextLine();
                    System.out.print("Confirm password: ");
                    confirmPassword = sc.nextLine();
                    if (!newPassword.equals(confirmPassword)) {
                        System.out.println("New password does not match, try again!");
                    }
                    
                } while (!newPassword.equals(confirmPassword));
                AccountDAO accountDAO = new AccountDAO();
                accountDAO.update(username, newPassword);
                System.out.println("Change password success!");
                
        	} else {
        		System.out.println("This member has no account or wrong username!");
        	}
        } else {
        	System.out.println("Member's ID is not exist!");
        }
    }

    public static void changePassword() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = sc.nextLine();

        if (getMemberName(username) != null) {
            String newPassword;
            String confirmPassword;
            do {
                System.out.print("Enter new password: ");
                newPassword = sc.next();
                System.out.print("Confirm password: ");
                confirmPassword = sc.next();
                if (!newPassword.equals(confirmPassword)) {
                    System.out.println("New password does not match, try again!");
                }
            } while (!newPassword.equals(confirmPassword));
            AccountDAO accountDAO = new AccountDAO();
            accountDAO.update(username, newPassword);
            System.out.println("Change password success!");
        } else {
            System.out.println("Username is not exits!");
        }
    }
}
