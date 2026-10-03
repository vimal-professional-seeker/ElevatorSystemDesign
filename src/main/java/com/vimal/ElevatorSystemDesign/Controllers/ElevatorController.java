package com.vimal.ElevatorSystemDesign.Controllers;

import com.vimal.ElevatorSystemDesign.Elevator;
import com.vimal.ElevatorSystemDesign.ElevatorSystem;
import com.vimal.ElevatorSystemDesign.ExternalRequest;
import com.vimal.ElevatorSystemDesign.InternalRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class ElevatorController {

    @Autowired
    ElevatorSystem elevatorSystem;

    @GetMapping("/elevator/start/{count}")
    public String startElevatorSystem(@PathVariable("count") int count) {
        elevatorSystem.startElevatorSystem(count);
        // Logic to start the elevator system with the specified number of elevators
        return "Elevator system started with " + count + " elevators.";
    }

    @GetMapping("/elevator/stop")
    public String stopElevatorSystem() {
        elevatorSystem.stopAllElevators();
        log.info("Elevator system stopped.");
        return "Elevator system stopped.";

    }

    @GetMapping("/elevator/status")
    public String getElevatorStatus() {
        boolean status = elevatorSystem.getStatus();
        if (status) {
            return "Elevator system is running";
        } else {
            return "Elevator system is stopped";
        }
    }

    @PostMapping("elevator/requestElevatorAtYourFloor")
    public String getElevatorAtYourFloor(@RequestBody ExternalRequest request) {
        // Logic to get the nearest elevator at the specified floor
        Elevator elevator=elevatorSystem.requestElevator(request);
        return "Your request is assign to your Nearest Elevator No."+elevator.getElevatorId()+", please wait...";
    }

    @PostMapping("elevator/requestElevatorInternal")
    public String goToDestinationFloor(@RequestBody InternalRequest request) {
        // Logic to get the nearest elevator at the specified floor
        Elevator elevator=elevatorSystem.internalRequestElevator(request);
        return "Now your Elevator No."+elevator.getElevatorId()+" is going to your Destination Floor"+request.getDestinationFloor();
    }

}
