package com.vimal.ElevatorSystemDesign;

import java.util.List;

public interface SchedulingAlgorithm {
    Elevator getElevator(List<Elevator> elevatorList, ExternalRequest request);

    Elevator internalRequest(List<Elevator> elevatorList, InternalRequest request);
}
