package com.aa.sample.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Booking {

    private Long id;
    private String seatNumber;
    private int movieId;
    private int showId;
    private String user;
}
