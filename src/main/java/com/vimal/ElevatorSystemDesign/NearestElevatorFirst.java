package com.vimal.ElevatorSystemDesign;

import com.vimal.ElevatorSystemDesign.Enums.Direction;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class NearestElevatorFirst implements SchedulingAlgorithm{
    @Override
    public Elevator getElevator(List<Elevator> elevatorList, ExternalRequest request) {
        HashMap<Integer,Elevator> distanceMap=new HashMap<>();
        int minDistance=Integer.MAX_VALUE;
        for (Elevator elevator : elevatorList)
        {
            if (elevator.currentDirection== Direction.UP && elevator.currentFloor <= request.floor)
            {
                int distance=Math.abs(elevator.currentFloor-request.floor);
                if (distance<minDistance)
                {
                    minDistance=distance;
                    distanceMap.put(minDistance,elevator);
                }
            }
            else if (elevator.currentDirection== Direction.DOWN && elevator.currentFloor >= request.floor)
            {
                int distance=Math.abs(elevator.currentFloor-request.floor);
                if (distance<minDistance)
                {
                    minDistance=distance;
                    distanceMap.put(minDistance,elevator);
                }
            }
            else if (elevator.currentDirection== Direction.IDLE)
            {
                int distance=Math.abs(elevator.currentFloor-request.floor);
                if (distance<minDistance)
                {
                    minDistance=distance;
                    distanceMap.put(minDistance,elevator);
                }
            }
        }
        Elevator elevator= distanceMap.get(minDistance);
        distanceMap.clear();
        return elevator;
    }

    @Override
    public Elevator internalRequest(List<Elevator> elevatorList, InternalRequest request) {
        for (Elevator elevator : elevatorList)
        {
            if (elevator.elevatorId==request.elevatorId)
            {
                return elevator;
            }
        }
        return null;
    }
}
