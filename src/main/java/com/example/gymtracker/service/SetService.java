package com.example.gymtracker.service;

import com.example.gymtracker.model.WorkoutExercise;
import com.example.gymtracker.model.WorkoutSet;
import com.example.gymtracker.repository.SetRepository;
import com.example.gymtracker.repository.WorkoutExerciseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetService {

    private final SetRepository setRepository;
    private final WorkoutExerciseRepository workoutExerciseRepository;

    public SetService(SetRepository setRepository, WorkoutExerciseRepository workoutExerciseRepository) {
        this.setRepository = setRepository;

        this.workoutExerciseRepository = workoutExerciseRepository;
    }


    public void createUserWorkoutExerciseSet(Long userid, Long workoutid, Long workoutexerciseid, WorkoutSet workoutSet) {

        WorkoutExercise workoutExercise= workoutExerciseRepository.findByIdAndWorkoutIdAndWorkoutUserId(workoutexerciseid,workoutid,userid)
                .orElseThrow(()->new RuntimeException("Exercise not found with given userid,workoutid,exerciseid"));

        workoutSet.setExercise(workoutExercise);

        workoutExercise.getSets().add(workoutSet);

        setRepository.save(workoutSet);

    }

    public List<WorkoutSet> getAllExerciseSets(Long userid,Long workoutid, Long workoutexerciseid){

        WorkoutExercise workoutExercise=workoutExerciseRepository.findByIds(workoutexerciseid,workoutid,userid)
                .orElseThrow(() ->new RuntimeException("Exercise not found with that user id exercise id and workout id"));


        return workoutExercise.getSets();

    }

    public void deleteUserWorkoutExerciseSet(Long userid,Long workoutid,Long exerciseid,Long setid){

        WorkoutExercise workoutExercise= workoutExerciseRepository.findByIdAndWorkoutIdAndWorkoutUserId(exerciseid,workoutid,userid)
                .orElseThrow(() -> new RuntimeException("Workout exercise not found with that userid, workoutid, and exercise"));

        WorkoutSet workoutSet= workoutExercise.getSets().stream()
                .filter(ws -> ws.getId().equals(setid))
                .findFirst()
                .orElseThrow(()->new RuntimeException("Workout set not found with that id"));

        workoutExercise.getSets().remove(workoutSet);
        setRepository.delete(workoutSet);




    }


    public WorkoutSet getSetById(Long userid, Long workoutid, Long workoutexerciseid, Long setid) {

        WorkoutSet workoutSet=setRepository.findByIdAndExercise_IdAndExercise_Workout_IdAndExercise_Workout_User_Id(setid,workoutexerciseid,workoutid,userid)
                .orElseThrow(() -> new RuntimeException("Set not found"));
        return workoutSet;

    }
}
