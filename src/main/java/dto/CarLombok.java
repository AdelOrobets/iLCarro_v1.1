package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString
public class CarLombok {

    private String city;
    private String manufacture;
    private String model;
    private int year;
    private String fuel;
    private int seats;
    private String carClass;
    private String serialNumber;
    private Double pricePerDay;
    private String about;
    private String image;
}