package service.impl;

import java.util.Scanner;

import model.RepairCenter;

import service.RepairCenterService;

public class RepairCenterServiceImpl implements RepairCenterService {

    @Override
    public void input(RepairCenter rc) {
        Scanner sc = new Scanner(System.in);
        System.out.println("---Enter repair center's information---");
        System.out.print("Enter RC's ID: ");
        rc.setRcId(sc.nextLine());
        System.out.print("Enter RC's name: ");
        rc.setRcName(sc.nextLine());
        System.out.print("Enter RC's price: ");
        rc.setRcPrice(sc.nextInt());
        sc.nextLine();
        System.out.println("---------------------------------------");
    }

    @Override
    public void output(RepairCenter rc) {
        System.out.printf("%-15s %-20s %-15d\n",rc.getRcId(), rc.getRcName(), rc.getRcPrice());
    }
    
}
