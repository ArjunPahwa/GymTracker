package com.example.gymtracker.controller;


import com.example.gymtracker.model.Workout;
import com.example.gymtracker.model.WorkoutExercise;
import com.example.gymtracker.service.WorkoutService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/users/{userid}/workouts")
public class WorkoutController {

    private final WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }


    @PostMapping
    public ResponseEntity<String> createNewUserWorkout(@PathVariable Long userid,
                                                       @RequestBody Workout workout){

        workoutService.createNewUserWorkout(userid,workout);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("User with id: "+userid+" created new workout");
    }



    @GetMapping("/all")
    public List<Workout> getAllUserWorkouts(@PathVariable Long userid){
        return workoutService.getAllUserWorkouts(userid);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserWorkout(@PathVariable Long userid,
                                                    @PathVariable Long id){
        workoutService.deleteWorkoutById(userid,id);

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body("Workout deleted successfully for user:"+userid);
    }
}
