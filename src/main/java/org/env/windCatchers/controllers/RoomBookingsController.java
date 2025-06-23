package org.env.windCatchers.controllers;

import java.util.List;

import org.env.windCatchers.dtos.roomBooking.CreateRoomBookingDTO;
import org.env.windCatchers.dtos.roomBooking.RoomBookingResponseDTO;
import org.env.windCatchers.services.roomBooking.RoomBookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/room_bookings")
public class RoomBookingsController {


    private final RoomBookingService roomBookingService;

    public RoomBookingsController(RoomBookingService roomBookingService) {
        this.roomBookingService = roomBookingService;
    }




    // List all rooms bookings 
    @GetMapping
    public ResponseEntity<List<RoomBookingResponseDTO>> listAll() {
        List<RoomBookingResponseDTO> roomBookings = roomBookingService.getAll();

        return ResponseEntity.ok(roomBookings);
    }


    // create new room booking 
    @PostMapping
    public ResponseEntity<RoomBookingResponseDTO> create(@RequestBody CreateRoomBookingDTO roomBookingDTO) {
     RoomBookingResponseDTO roomBooking = roomBookingService.create(roomBookingDTO);

     return ResponseEntity.status(HttpStatus.CREATED).body(roomBooking);
    }    
}
