package service.impl;

import java.util.Scanner;

import model.Equipment;

import service.EquipmentService;

public class EquipmentServiceImpl implements EquipmentService{

    @Override
    public void input(Equipment e) {
        Scanner sc = new Scanner(System.in);
        System.out.println("---Enter equipment's information---");
        System.out.print("Enter equipment's ID: ");
        e.setEquipmentId(sc.nextLine());
        System.out.print("Enter equipment's name: ");
        e.setEquipmentName(sc.nextLine());
        System.out.println("-----------------------------------");

        // each equipment is required by default have 365 days of durability
        e.setEquipmentDurability(365);
    }

    @Override
    public void output(Equipment e) {
        System.out.printf("%-15s %-20s %-15s\n", e.getEquipmentId(), e.getEquipmentName(), e.getEquipmentDurability());
    }
    
}
