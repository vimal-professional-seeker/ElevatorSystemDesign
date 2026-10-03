package com.vimal.ElevatorSystemDesign;

import com.vimal.ElevatorSystemDesign.Enums.Direction;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ExternalRequest {
    int floor;
    Direction direction;
}
