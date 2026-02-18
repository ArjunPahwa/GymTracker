package com.example.gymtracker.controller;


import com.example.gymtracker.model.Exercise;
import com.example.gymtracker.service.ExerciseService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/exercises")
@CrossOrigin(origins="http://localhost:5173/")
public class ExerciseController {

    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> createExercise(@RequestBody Exercise exercise){

        exerciseService.createExercise(exercise);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Exercise created successfully");
    }

    @GetMapping(path="/{id}")
    public Exercise findById(@PathVariable Long id){

        return exerciseService.findById(id);
    }

    @GetMapping(path = "/all")
    public List<Exercise> getAllExercises(){
        return exerciseService.getAllExercises();
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("TRAINER")
    public ResponseEntity<String> deleteExerciseById(@PathVariable Long id){
        exerciseService.deleteExercise(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body("Exercise deleted successfully");

    }


}
