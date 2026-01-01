package com.example.gymtracker.controller;


import com.example.gymtracker.model.WorkoutSet;
import com.example.gymtracker.service.SetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/{userid}/workouts/{workoutid}/exercises/{workoutexerciseid}/sets")
public class SetsController {

    private final SetService setService;

    public SetsController(SetService setService) {
        this.setService = setService;
    }

    @PostMapping
    public ResponseEntity<String> createUserWorkoutExerciseSet(@PathVariable Long userid,
                                                               @PathVariable Long workoutid,
                                                               @PathVariable Long workoutexerciseid,
                                                               @RequestBody WorkoutSet workoutSet){
        setService.createUserWorkoutExerciseSet(userid,workoutid, workoutexerciseid,workoutSet);


        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Set Created successfully for exerciseid:" + workoutexerciseid +", userid:" +userid+", workoutid:" +workoutid);
    }

    @GetMapping(path="/all")
    public List<WorkoutSet> getAllExerciseSets(@PathVariable Long userid,
                                               @PathVariable Long workoutid,
                                               @PathVariable Long workoutexerciseid)

                                               {

        return setService.getAllExerciseSets(userid,workoutid, workoutexerciseid);
    }

    @GetMapping("/{setid}")
    public WorkoutSet getSetById(@PathVariable Long userid,
                                 @PathVariable Long workoutid,
                                 @PathVariable Long workoutexerciseid,
                                 @PathVariable Long setid){

        return setService.getSetById(userid,workoutid, workoutexerciseid,setid);

    }


    @DeleteMapping("/{setid}")
    public ResponseEntity deleteUserWorkoutExerciseSet(@PathVariable Long userid,
                                                       @PathVariable Long workoutid,
                                                       @PathVariable Long workoutexerciseid,
                                                       @PathVariable Long setid){

        setService.deleteUserWorkoutExerciseSet(userid,workoutid,workoutexerciseid,setid);

        return ResponseEntity.status(HttpStatus.OK)
                .body("Set deleted successfully with userid: "+userid+" and workoutid:" +workoutid+" and workoutexerciseid: "+workoutexerciseid);
    }


}
