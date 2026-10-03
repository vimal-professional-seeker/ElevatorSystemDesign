package com.vimal.ElevatorSystemDesign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ElevatorSystem {
    List<Elevator> elevatorList= new ArrayList<>();
    List<Thread> threads=new ArrayList<>();

    @Autowired
    SchedulingAlgorithm schedulingAlgorithm;

    public Elevator requestElevator(ExternalRequest request)
    {
        Elevator elevator=schedulingAlgorithm.getElevator(elevatorList,request);
        elevator.assignRequest(request);
        return elevator;
    }

    public Elevator internalRequestElevator(InternalRequest request)
    {
        Elevator elevator=schedulingAlgorithm.internalRequest(elevatorList,request);
        if (elevator!=null)
        {
            elevator.assignInternalRequest(request);
            return elevator;
        }
        return null;
    }

    public void startElevatorSystem(int count)
    {
        for(int j=1;j<=count;j++)
        {
            Elevator elevator=new Elevator(j);
            elevatorList.add(elevator);
            Thread thread=new Thread(elevator);
            threads.add(thread);
            thread.start();
        }
    }

    public void stopAllElevators() {
        elevatorList.forEach(Elevator::stopElevator);
    }

    public boolean getStatus() {
        for(Elevator elevator : elevatorList) {
            if (elevator.isRunning()) {
                return true;
            }
        }
        return false;
    }
}
