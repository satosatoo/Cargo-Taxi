package com.cargotaxi.coursework;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Contract {
    static int id;
    public final int contractId;
    LocalDate deliveryDate;
    Cargo cargoC;
    OrderReceiver orderReceiverC;
    Driver driverC;

    public static List<Contract> contractList = new ArrayList<>();

    Contract(Cargo cargo, OrderReceiver orderReceiver, Driver driver, LocalDate date) {        // ВОЗМОЖНО ДРУГОЙ АТРИБУТ
        this.cargoC = cargo;
        this.orderReceiverC = orderReceiver;
        this.driverC = driver;
        driverC.setInfoFromContract(cargoC.getCargoId(), cargoC.getWeight());
        this.deliveryDate = driverC.deliveryTime(date, cargoC.getWeight(), driverC.getLimitWeight());
        this.contractId = Contract.id++;
    }

    public void createContract(Cargo cargo, OrderReceiver orderReceiver, Driver driver, LocalDate date) {      // ВОЗМОЖНО ДРУГОЙ АТРИБУТ
        Contract contract = new Contract(cargo, orderReceiver, driver, date);
        Contract.contractList.add(contract);
    }

    public void deleteContract(ArrayList<Contract> array, int id) {
        for (Contract obj : array) {
            if (obj.getContractId() == id) {
                obj = null;
            }
        }
    }

    public int getContractId() { return this.contractId; }

    public void printInfo() {
        System.out.println("Contract id: " + getContractId() + " |~| Delivery date: " + getDeliveryDate() + " |~| Driver fullname: " + driverC.getFullName() +
        " |~| Driver id: " + driverC.getId() + " |~| Order receiver fullname: " + orderReceiverC.getFullName() + 
        " |~| Order receiver id: " + orderReceiverC.getId() + " |~| Cargo name: " + cargoC.getCargoName() + " |~| Cargo id: " + cargoC.getCargoId());
    }

    // Выбор грузов и диспетчеров и водителей
    public List<Cargo> chooseListOfCargo() {
        List<Cargo> cargoL = Cargo.cargoList;
        for (int i = 0; i < cargoL.size(); i++) {
            if (cargoL.get(i).getCargoStatusBoolean() == false) {
                cargoL.remove(i);
            }
        }
        return cargoL;
    }

    public List<OrderReceiver> chooseListOfOrderReceivers() {
        return OrderReceiver.orderReceiverList;
    }

    public List<Driver> chooseListOfDriver() {
        List<Driver> driverL = Driver.driverList;
        for (int i = 0; i < driverL.size(); i++) {
            if (driverL.get(i).getDriverStatusBoolean() == false) {
                driverL.remove(i);
            }
        }
        return driverL;
    }

    public LocalDate getDeliveryDate() { return this.deliveryDate; }
}