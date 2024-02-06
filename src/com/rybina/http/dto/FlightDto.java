package com.rybina.http.dto;

import lombok.Data;
import lombok.Value;

//@AllArgsConstructor
//@NoArgsConstructor(force = true)
//@Getter
//@EqualsAndHashCode
//@ToString
//public class FlightDto {
//    private final Long id;
//    private final String description;
//}


//Equal to

//@Data
//public class FlightDto {
//    private final Long id;
//    private final String description;
//}

// also we can use @Value, the difference is the variables are automatically private
@Value
public class FlightDto {
    Long id;
    String description;
}
