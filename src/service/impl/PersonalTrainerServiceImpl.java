package service.impl;

import java.util.Scanner;

import model.PersonalTrainer;

import service.PersonalTrainerService;

public class PersonalTrainerServiceImpl implements PersonalTrainerService {

    @Override
    public void input(PersonalTrainer pt) {
        Scanner sc = new Scanner(System.in);
        System.out.println("---Enter PT's information---");
        System.out.print("Enter PT's ID: ");
        pt.setPtId(sc.nextLine());
        System.out.print("Enter PT's name: ");
        pt.setPtName(sc.nextLine());
        System.out.print("Enter PT's gender: ");
        pt.setPtGender(sc.nextLine());
        System.out.print("Enter PT's hire price per day: ");
        pt.setPtHirePricePerDay(sc.nextInt());
        sc.nextLine();
        System.out.println("----------------------------");
    }

    @Override
    public void output(PersonalTrainer pt) {
        System.out.printf("%-15s %-20s %-10s %-15d\n",pt.getPtId(), pt.getPtName(), pt.getPtGender(), pt.getPtHirePricePerDay());
    }
    
}
