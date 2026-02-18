package com.example.gymtracker.controller;
import com.example.gymtracker.model.User;
import com.example.gymtracker.model.Workout;
import com.example.gymtracker.service.WorkoutService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(path="/users/workouts")
public class WorkoutController {

    private final WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }


    @PostMapping
    public ResponseEntity<String> createNewUserWorkout(@RequestBody Workout workout,
                                                       Authentication authentication){


        User user = (User) authentication.getPrincipal();

        final String username= user.getUsername();

        workoutService.createNewUserWorkout(username,workout);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("User with username: "+username+" created new workout");
    }



    @GetMapping("/all")
    public List<Workout> getAllUserWorkouts(Authentication authentication){

        User user = (User) authentication.getPrincipal();

        final Long userid = user.getId();

        return workoutService.getAllUserWorkouts(userid);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserWorkout(@PathVariable Long id,
                                                    Authentication authentication){
        User user = (User) authentication.getPrincipal();

        final Long userid = user.getId();

        workoutService.deleteWorkoutById(userid,id);

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body("Workout deleted successfully for user:"+userid);
    }
}
