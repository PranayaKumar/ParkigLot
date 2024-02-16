package service.strategy.billCalculationStrategy;

import models.Ticket;
import models.constants.VehicleType;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class LowBillCalculationStrategy implements BillCalculationStrategy{
    private static final int PER_HOUR_CHARGE_2_WHEELER = 50;
    private static final int PER_HOUR_CHARGE_4_WHEELER = 100;

    private static final double INCREMENT_FACTOR = 0.1;


    @Override
    public double calculateBillAmount(Ticket ticket, LocalDateTime exitTime) {
        LocalDateTime entryTime = ticket.getEntryTime();
        long totalHours = ChronoUnit.HOURS.between(exitTime, entryTime); // 40 mins -> 1 , 1:30 -> 2 2:40 -> 3
        int costPerHour = (ticket.getVehicle().getVehicleType().equals(VehicleType.CAR)) ? // TODO : Write a separate method to calculate per hour cost based on vehicle type
                PER_HOUR_CHARGE_4_WHEELER :
                PER_HOUR_CHARGE_2_WHEELER;

        double baseCost = costPerHour  * totalHours;
        return baseCost + (baseCost * (INCREMENT_FACTOR * (totalHours-1)));
    }
}
