package com.example.gymtracker.service;


import com.example.gymtracker.model.User;
import com.example.gymtracker.model.Workout;
import com.example.gymtracker.model.WorkoutExercise;
import com.example.gymtracker.repository.UserRepository;
import com.example.gymtracker.repository.WorkoutRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final UserRepository userRepository;

    public WorkoutService(WorkoutRepository workoutRepository, UserRepository userRepository) {
        this.workoutRepository = workoutRepository;
        this.userRepository = userRepository;
    }

    public void createNewUserWorkout(Long userid, Workout workout) {
        User user=userRepository.findById(userid)
                .orElseThrow(() -> new RuntimeException("User not found"));
        workout.setUser(user);

        workoutRepository.save(workout);

    }

    public List<Workout> getAllUserWorkouts(Long userid) {
        User user=userRepository.findById(userid)
                .orElseThrow(()->new RuntimeException("User not found"));
        return workoutRepository.findByUserId(userid);
    }


    public void deleteWorkoutById(Long userid, Long id) {

        Workout workout = workoutRepository.findByIdAndUserId(id,userid)
                .orElseThrow(() -> new RuntimeException("Workout not found with given userid and workoutid"));
        User user= userRepository.findById(userid)
                        .orElseThrow(()->new RuntimeException("User not found with that userid"));
        user.getWorkouts().remove(workout);
        workoutRepository.delete(workout);

    }
}
