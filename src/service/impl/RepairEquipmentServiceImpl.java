package service.impl;

import java.util.Scanner;

import model.Equipment;
import model.RepairCenter;
import model.RepairEquipment;

import service.RepairEquipmentService;

public class RepairEquipmentServiceImpl implements RepairEquipmentService {

    @Override
    public void input(RepairEquipment re) {
        Equipment e = new Equipment();
        RepairCenter rc = new RepairCenter();

        Scanner sc = new Scanner(System.in);
        System.out.println("---Enter Repair Equip Service---");
        System.out.print("Enter Equipment's ID: ");
        e.setEquipmentId(sc.nextLine());
        re.setE(e);

        System.out.print("Enter Repair Center's ID: ");
        rc.setRcId(sc.nextLine());
        re.setRc(rc);

        // The default equipment maintenance is 7 days
        re.setTimeLeft(7);
        System.out.println("--------------------------------");

    }

    @Override
    public void output(RepairEquipment re) {
        System.out.printf("%-15s %-15s %-10d\n", re.getE().getEquipmentId(), re.getRc().getRcId(), re.getTimeLeft());
    }
    
}
