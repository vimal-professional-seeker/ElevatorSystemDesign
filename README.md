
Functional Requirement:

1. Elevator System should support multiple elevators in a building.
2. The system should allow users to request an elevator from any floor.
3. The system should prioritize elevator requests based on the current position and direction of the elevators.
4. The system should handle multiple simultaneous requests efficiently.

Non-Functional Requirements:
1. Should follow SOLID principles for maintainability and scalability.
2. Thread Safety: The system should be thread-safe to handle concurrent requests from multiple users.

Core Entities:
ExtrnalRequest
    -int requestFloor
    -DirectionEnum {UP,DOWN,IDLE}
InternalRequest,
    -elevatorId
    -destinationFloor
ElevatorSystem 
    -startElevator(3) => starts all 3 threads
    -Elevator List
    -ThreadList
Elevator implements Runnable
    -int elevatorId
    -int currentFloor
    -DirectionEnum {UP,DOWN,IDLE}
    -upStops {minHeap}
    -downStops {maxHeap}
    -isRunning
    -step() it just apply 2 seconds delay, based on current direction, it will either increment or decrement the current floor
    -run method
        isRunnning=true
        while(isRunning)
        {
            whileUpStopsIsNotEmpty   --> 
                    if current direction is either DOWN or IDLE, then set current direction to UP
                    if upstops min heap .peek == currentFloor --> upStops.poll()
                    else step()
            whileDownStopsIsNotEmpty -->
                    similar to upStops
            currentDirection = Direction.IDLE;
        }

SchedulingAlgorithm->getElevator(ElevatorList, Request)
NearestElevatorAlgorithm implements SchedulingAlgorithm
    -getElevator(ElevatorList, Request)
        distanceMap Math.abs(elevator.currentFloor - request.requestFloor)
        return minDistanceElevator