import controller.TicketController;
import dto.TicketRequestDTO;
import dto.TicketResponseDTO;
import exception.GateNotFoundException;
import exception.NoEmptyParkingSlotAvailableException;
import exception.ParkingLotNotFoundException;
import models.ParkingLot;
import models.constants.VehicleType;
import repository.*;
import service.InitService;
import service.InitServiceImpl;

public class ParkingLotMain {

    public static void main(String[] args) throws ParkingLotNotFoundException, NoEmptyParkingSlotAvailableException, GateNotFoundException {
        ParkingLotRepository parkingLotRepository = new ParkingLotRepository();
        ParkingFloorRepository parkingFloorRepository = new ParkingFloorRepository();
        ParkingSlotRepository parkingSlotRepository = new ParkingSlotRepository();
        GateRepository gateRepository = new GateRepository();
        TicketRepository ticketRepository = new TicketRepository();

        InitService initService = new InitServiceImpl(parkingSlotRepository, parkingFloorRepository, parkingLotRepository, gateRepository);
        initService.init();

        TicketController ticketController = new TicketController(parkingLotRepository, gateRepository, ticketRepository);

        ParkingLot parkingLot = parkingLotRepository.get(1);

        TicketRequestDTO ticketRequestDTO = new TicketRequestDTO();
        ticketRequestDTO.setParkingLotId(1);
        ticketRequestDTO.setGateId(31);
        ticketRequestDTO.setName("Mercedez");
        ticketRequestDTO.setColor("Blue");
        ticketRequestDTO.setVehicleType(VehicleType.CAR);
        ticketRequestDTO.setNumber("1234");

        TicketResponseDTO ticketResponseDTO = ticketController.createTicket(ticketRequestDTO);
        System.out.println(ticketResponseDTO);
    }
}
