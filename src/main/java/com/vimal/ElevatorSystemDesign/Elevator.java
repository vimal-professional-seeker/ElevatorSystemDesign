package com.vimal.ElevatorSystemDesign;

import com.vimal.ElevatorSystemDesign.Enums.Direction;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.PriorityQueue;

@Slf4j
@Setter
@Getter
public class Elevator implements Runnable {
    int elevatorId;
    int currentFloor;
    Direction currentDirection = Direction.IDLE;
    boolean isRunning = false;
    //MinHeap
    PriorityQueue<Integer> upStops = new PriorityQueue<>();
    //MaxHeap
    PriorityQueue<Integer> downStops = new PriorityQueue<>(Collections.reverseOrder());

    public Elevator(int elevatorId) {
        this.elevatorId = elevatorId;
        this.currentFloor = 0;
    }

    public void stopElevator() {
        isRunning = false;
    }

    @Override
    public void run(){
        isRunning=true;
        log.info("Elevator No. {} is running", elevatorId);
        while(isRunning){
            while(!upStops.isEmpty()){
                log.info("Elevator No. {} moving UP, current floor: {}", elevatorId,currentFloor);
                if(currentDirection == Direction.DOWN || currentDirection == Direction.IDLE){
                    currentDirection = Direction.UP;
                }
                step();
                if(upStops.peek() == currentFloor){
                    log.info("Elevator No. " + elevatorId + " Arrived At Floor " + currentFloor );
                    upStops.poll();
                }
            }

            while(!downStops.isEmpty()){
                log.info("Elevator No. {} moving DOWN, current floor: {}", elevatorId,currentFloor);
                if(currentDirection == Direction.UP || currentDirection == Direction.IDLE){
                    currentDirection = Direction.DOWN;
                }
                step();
                if(downStops.peek() == currentFloor){
                    log.info("Elevator No. " + elevatorId + " Arrived At Floor " + currentFloor );
                    downStops.poll();
                }
            }
            currentDirection = Direction.IDLE;
        }
    }

    public void assignRequest(ExternalRequest request) {
        if (currentFloor > request.floor) {
            downStops.add(request.floor);
        } else {
            upStops.add(request.floor);
        }
    }

    public void assignInternalRequest(InternalRequest request) {
        if (currentFloor > request.destinationFloor) {
            downStops.add(request.destinationFloor);
        } else {
            upStops.add(request.destinationFloor);
        }
    }

    public void step() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (currentDirection == Direction.UP) {
            currentFloor++;

        } else if (currentDirection == Direction.DOWN) {
            currentFloor--;

        } else {
            currentFloor=0;
        }
    }

    public void addInternalRequest(int destinationFloor) {
        if (currentFloor > destinationFloor) {
            downStops.add(destinationFloor);
        } else {
            upStops.add(destinationFloor);
        }
    }
}
