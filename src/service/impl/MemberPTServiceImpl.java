package service.impl;

import java.util.Scanner;

import model.Member;
import model.MemberPT;
import model.PersonalTrainer;

import service.MemberPTService;

public class MemberPTServiceImpl implements MemberPTService{

    @Override
    public void input(MemberPT mpt) {
        Member mem = new Member();
        PersonalTrainer pt = new PersonalTrainer();

        Scanner sc = new Scanner(System.in);
        System.out.println("---Enter Member PT Service---");
        System.out.print("Enter member's ID: ");
        mem.setMemberId(sc.nextLine());
        mpt.setMem(mem);

        System.out.print("Enter PT's ID: ");
        pt.setPtId(sc.nextLine());
        mpt.setPt(pt);

        System.out.print("Enter time: ");
        mpt.setTimeLeft(sc.nextInt());
        sc.nextLine();
        // price = timeLeft * ptHirePricePerDay 
        
        System.out.println("-----------------------------");

    }

    @Override
    public void output(MemberPT mpt) {
        System.out.printf("%-15s %-15s %-10d %-10d\n", mpt.getMem().getMemberId(), mpt.getPt().getPtId(), mpt.getTimeLeft(), mpt.getAllPrice());
    }
    
}
