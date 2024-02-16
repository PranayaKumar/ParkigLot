package service;

import exception.GateNotFoundException;
import exception.NoEmptyParkingSlotAvailableException;
import exception.ParkingLotNotFoundException;
import models.*;
import repository.GateRepository;
import repository.ParkingLotRepository;
import repository.TicketRepository;

import java.time.LocalDateTime;

public class TicketServiceImpl  implements TicketService {
    private ParkingLotRepository parkingLotRepository;
    private GateRepository gateRepository;
    private TicketRepository ticketRepository;

    public TicketServiceImpl(ParkingLotRepository parkingLotRepository, GateRepository gateRepository, TicketRepository ticketRepository) {
        this.parkingLotRepository = parkingLotRepository;
        this.gateRepository = gateRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Ticket createTicket(Vehicle vehicle, int gateId, int parkingLotId, LocalDateTime entryTime) throws ParkingLotNotFoundException, GateNotFoundException, NoEmptyParkingSlotAvailableException {
        ParkingLot parkingLot = parkingLotRepository.get(parkingLotId);
        Gate gate = gateRepository.get(gateId);
        ParkingSlot assignedParkingSlot = parkingLot.getSlotAllocationStrategy().findParkingSlot(vehicle.getVehicleType(), parkingLot, gate);
        Ticket ticket = new Ticket();
        ticket.setEntryTime(entryTime);
        ticket.setVehicle(vehicle);
        ticket.setParkingSlot(assignedParkingSlot);
        return ticketRepository.put(ticket);
    }
}
