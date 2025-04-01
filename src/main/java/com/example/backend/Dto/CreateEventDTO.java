package com.example.backend.Dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CreateEventDTO {
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
    private String location;
    private String eventType;
    private String status;
}
