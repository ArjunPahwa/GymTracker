package com.example.gymtracker.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name="globalExercises")
@NoArgsConstructor
public class Exercise{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique = true)
    private String name;
    @Column(nullable = false,unique = false)
    private String equipment;
    @Column(nullable = false,unique = false)
    private String bodyPart;
    @Column(nullable = false)
    private String description;

    public Exercise(String name, String equipment, String bodyPart,String description){
        this.name=name;
        this.equipment=equipment;
        this.bodyPart=bodyPart;
        this.description=description;
    }

    @OneToMany(mappedBy = "exercise",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<WorkoutExercise> workoutExercise;


}
