package leet.code.solutions.OOP;

import java.util.*;

public class ElevatorSystemDes {

    // Enums for elevator states and direction
    enum Direction {
        UP(1), DOWN(-1), IDLE(0);
        private final int value;
        Direction(int value) { this.value = value; }
    }

    enum ElevatorState {
        MOVING, STOPPED, MAINTENANCE
    }

    // Request class to handle elevator calls
    class Request implements Comparable<Request> {
        private final int pickupFloor;
        private final int destinationFloor;
        private final long timestamp;

        public Request(int pickupFloor, int destinationFloor) {
            this.pickupFloor = pickupFloor;
            this.destinationFloor = destinationFloor;
            this.timestamp = System.currentTimeMillis();
        }

        public int getPickupFloor() {
            return pickupFloor;
        }

        public int getDestinationFloor() {
            return destinationFloor;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public Direction getDirection() {
            return destinationFloor > pickupFloor ? Direction.UP : Direction.DOWN;
        }

        @Override
        public int compareTo(Request other) {
            return Integer.compare(this.destinationFloor, other.destinationFloor);
        }
    }

    // Individual elevator control
    class Elevator {
        private final int id;
        private int currentFloor;
        private Direction direction;
        private ElevatorState state;
        private final PriorityQueue<Request> upRequests;
        private final PriorityQueue<Request> downRequests;
        private int currentLoad;
         static final int MAX_CAPACITY = 10;

        // Getters
        public int getId() {
            return id;
        }

        public int getCurrentFloor() {
            return currentFloor;
        }

        public Direction getDirection() {
            return direction;
        }

        public ElevatorState getState() {
            return state;
        }

        public int getCurrentLoad() {
            return currentLoad;
        }

        public static int getMaxCapacity() {
            return MAX_CAPACITY;
        }

        public Elevator(int id) {
            this.id = id;
            this.currentFloor = 1;
            this.direction = Direction.IDLE;
            this.state = ElevatorState.STOPPED;
            this.upRequests = new PriorityQueue<>();
            this.downRequests = new PriorityQueue<>(Collections.reverseOrder());
            this.currentLoad = 0;
        }

        public boolean addRequest(Request request) {
            if (currentLoad >= MAX_CAPACITY) return false;

            if (request.getDirection() == Direction.UP) {
                upRequests.offer(request);
            } else {
                downRequests.offer(request);
            }

            if (direction == Direction.IDLE) {
                direction = request.getDirection();
            }
            currentLoad++;
            return true;
        }

        public void move() {
            if (state != ElevatorState.MOVING) return;

            switch (direction) {
                case UP:
                    if (!upRequests.isEmpty()) {
                        currentFloor++;
                    } else if (!downRequests.isEmpty()) {
                        direction = Direction.DOWN;
                    } else {
                        direction = Direction.IDLE;
                        state = ElevatorState.STOPPED;
                    }
                    break;

                case DOWN:
                    if (!downRequests.isEmpty()) {
                        currentFloor--;
                    } else if (!upRequests.isEmpty()) {
                        direction = Direction.UP;
                    } else {
                        direction = Direction.IDLE;
                        state = ElevatorState.STOPPED;
                    }
                    break;
            }
        }

        public void processFloor() {
            PriorityQueue<Request> currentQueue =
                    (direction == Direction.UP) ? upRequests : downRequests;

            while (!currentQueue.isEmpty() &&
                    currentQueue.peek().destinationFloor == currentFloor) {
                currentQueue.poll();
                currentLoad--;
            }
        }
    }

    // Main elevator system controller
    class ElevatorSystem {
        private final List<Elevator> elevators;
        private final List<Request> pendingRequests;

        public ElevatorSystem(int elevatorCount) {
            this.elevators = new ArrayList<>();
            for (int i = 0; i < elevatorCount; i++) {
                elevators.add(new Elevator(i));
            }
            this.pendingRequests = new ArrayList<>();
        }

        public Integer requestElevator(Request request) {
            Elevator bestElevator = null;
            double minCost = Double.MAX_VALUE;

            for (Elevator elevator : elevators) {
                if (elevator.getState() == ElevatorState.MAINTENANCE) continue;

                double cost = calculateCost(elevator, request);
                if (cost < minCost) {
                    minCost = cost;
                    bestElevator = elevator;
                }
            }

            if (bestElevator != null && bestElevator.addRequest(request)) {
                return bestElevator.getId();
            }

            pendingRequests.add(request);
            return null;
        }

        private double calculateCost(Elevator elevator, Request request) {
            double cost = Math.abs(elevator.getCurrentFloor() - request.getPickupFloor());

            // Add penalties
            if (elevator.getDirection() != Direction.IDLE &&
                    elevator.getDirection() != request.getDirection()) {
                cost *= 2;  // Penalty for opposite direction
            }

            if (elevator.getCurrentLoad() > Elevator.MAX_CAPACITY * 0.7) {
                cost *= 1.5;  // Penalty for heavy load
            }

            return cost;
        }

        public void step() {
            // Move all elevators
            elevators.forEach(Elevator::move);

            // Process floors for each elevator
            elevators.forEach(Elevator::processFloor);

            // Try to assign pending requests
            Iterator<Request> iterator = pendingRequests.iterator();
            while (iterator.hasNext()) {
                Request request = iterator.next();
                if (requestElevator(request) != null) {
                    iterator.remove();
                }
            }
        }
    }
}
