package service.impl;

import java.util.Scanner;
import java.sql.Date;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

import model.Member;

import service.MemberService;

public class MemberServiceImpl implements MemberService {

    @Override
    public void input(Member mem) {
        Scanner sc = new Scanner(System.in);
        System.out.println("---Enter member's information---");
        System.out.print("Enter member's ID: ");
        mem.setMemberId(sc.nextLine());
        System.out.print("Enter member's name: ");
        mem.setMemberName(sc.nextLine());
        System.out.print("Enter member's age: ");
        mem.setMemberAge(sc.nextInt());
        sc.nextLine();
        System.out.println("--------------------------------");

        LocalDate localDate = LocalDate.now();
        // the start day is initialized when creating the account: defaut today
        Date dayStart = Date.valueOf(localDate);
        mem.setMemberDayStart(dayStart);
        
        // the end day is 30 days from start day 
        LocalDate resultDate = localDate.plusDays(30);
        Date dayEnd = Date.valueOf(resultDate);
        mem.setMemberDayEnd(dayEnd);
    }

    @Override
    public void output(Member mem) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        System.out.printf("%-15s %-20s %-10d %-15s %-15s\n", mem.getMemberId(), mem.getMemberName(), mem.getMemberAge(), formatter.format(mem.getMemberDayStart()), formatter.format(mem.getMemberDayEnd()));
    }

}
