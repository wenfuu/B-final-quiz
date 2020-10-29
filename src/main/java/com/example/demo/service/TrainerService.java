package com.example.demo.service;

import com.example.demo.model.Trainer;
import com.example.demo.repository.TrainerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class TrainerService {

    final TrainerRepository trainerRepository;

    public TrainerService(TrainerRepository repository) {
        this.trainerRepository = repository;
    }

    public List<Trainer> getTrainersByGrouped(Boolean grouped) {
        return trainerRepository.findAll()
                .stream()
                .filter(trainer -> trainer.getGrouped().equals(grouped))
                .collect(Collectors.toList());
    }

    public Trainer addTrainer(Trainer trainer) {
        trainerRepository.save(trainer);
        return trainer;
    }

    public void deleteTrainer(Long id) {
        Trainer trainerToDelete = trainerRepository.findById(id).orElse(null);
        if (trainerToDelete != null) {
            trainerRepository.delete(trainerToDelete);
        }
    }
}
