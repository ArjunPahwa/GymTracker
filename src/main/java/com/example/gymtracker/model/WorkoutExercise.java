package com.example.gymtracker.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class WorkoutExercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "workout_id") //joins a column workout_id to exercise model as a foreign key to the workout model(primary key id as defined with @id)
    @JsonIgnore
    private Workout workout;

    @OneToMany(mappedBy = "exercise",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<WorkoutSet> sets;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    public WorkoutExercise(Exercise exercise){
        this.exercise=exercise;
    }

}
