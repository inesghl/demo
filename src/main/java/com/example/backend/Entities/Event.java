package com.example.backend.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Getter
@Setter
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String title;
    
    @Column(nullable = false)
    private String description;
    
    @Column(nullable = false)
    private Date startDate;
    
    @Column(nullable = false)
    private Date endDate;
    
    private String location;
    
    // Type: CONFERENCE, WEBINAR, WORKSHOP, etc.
    private String eventType;
    
   
    
     @ManyToOne
     @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User createdBy;
    
    
    // Many-to-many relationship with User for participants/attendees
    @ManyToMany
    @JoinTable(
        name = "event_participants",
        joinColumns = @JoinColumn(name = "event_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> participants;
    
    // Status: UPCOMING, ONGOING, COMPLETED, CANCELLED
    private String status;
}