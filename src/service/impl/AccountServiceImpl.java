package service.impl;

import java.util.Scanner;

import model.Account;

import service.AccountService;

public class AccountServiceImpl implements AccountService {

    @Override
    public void input(Account acc) {
        Scanner sc = new Scanner(System.in);
        System.out.println("---Enter your account---");
        System.out.print("Username: ");
        acc.setUsername(sc.nextLine());
        System.out.print("Password: ");
        acc.setPassword(sc.nextLine());
        System.out.println("------------------------");
        
        // each account is created by default as a user, admin's account is created in database
        acc.setRole("User");
    }

    @Override
    public void output(Account acc) {
        System.out.printf("%-20s %-20s\n", acc.getUsername(), acc.getPassword());
    }
    
}
