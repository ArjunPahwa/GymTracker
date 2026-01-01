package com.example.gymtracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
@Getter
@Setter
public class WorkoutSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int reps;
    private int weight;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    @JsonIgnore
    private WorkoutExercise exercise;

    public WorkoutSet(int reps, int weight){
        this.reps=reps;
        this.weight=weight;
    }

}
