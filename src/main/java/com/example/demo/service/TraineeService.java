package com.example.demo.service;

import com.example.demo.model.Trainee;
import com.example.demo.repository.TraineeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TraineeService {

    final TraineeRepository traineeRepository;

    public TraineeService(TraineeRepository repository) {
        this.traineeRepository = repository;
    }

    public List<Trainee> getTraineesByGrouped(Boolean grouped) {
        return traineeRepository.findAll()
                .stream()
                .filter(trainee -> trainee.getGrouped().equals(grouped))
                .collect(Collectors.toList());
    }

    public Trainee addTrainee(Trainee trainee) {
        traineeRepository.save(trainee);
        return trainee;
    }

    public void deleteTrainee(Long id) {
        Trainee traineeToDelete = traineeRepository.findById(id).orElse(null);
        if (traineeToDelete != null) {
            traineeRepository.delete(traineeToDelete);
        }
    }
}
