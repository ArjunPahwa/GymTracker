package com.example.gymtracker.service;

import com.example.gymtracker.model.Exercise;
import com.example.gymtracker.repository.ExerciseRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;


    public ExerciseService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    public void createExercise(Exercise exercise){

        Optional<Exercise> Exercise=exerciseRepository.findByName(exercise.getName());
        if (Exercise.isPresent()){
            throw new RuntimeException("Exercise with that name already created");
        }else{
            exerciseRepository.save(exercise);
        }

    }

    public void deleteExercise(Long id){
        Optional<Exercise> exercise=exerciseRepository.findById(id);
        if(exercise.isPresent()){
            exerciseRepository.deleteById(id);
        }else{
            throw new RuntimeException("Exercise not found in database");
        }


        }

    public Exercise findById(Long id){
        Optional<Exercise> exercise=exerciseRepository.findById(id);
        if(exercise.isPresent()){
            return exerciseRepository.findById(id).get();
        }else{
            throw new RuntimeException("Exercise not found");
        }
    }

    public List<Exercise> getAllExercises(){
        return exerciseRepository.findAll();
    }

}
