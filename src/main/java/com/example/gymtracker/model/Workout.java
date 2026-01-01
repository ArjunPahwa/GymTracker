package com.example.gymtracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "workout")
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    private boolean isTemplate = false; //keep for template so users dont have to keep on re-creating workouts and they can just save as a template and call it.

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "workout", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<WorkoutExercise> exerciseList;

    public Workout(String name, String description){
        this.name=name;
        this.description=description;
    }



}
