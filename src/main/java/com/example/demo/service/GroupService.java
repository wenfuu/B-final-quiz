package com.example.demo.service;

import com.example.demo.model.Group;
import com.example.demo.model.Trainee;
import com.example.demo.model.Trainer;
import com.example.demo.repository.GroupRepository;
import com.example.demo.repository.TraineeRepository;
import com.example.demo.repository.TrainerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final TrainerRepository trainerRepository;
    private final TraineeRepository traineeRepository;

    public GroupService(GroupRepository groupRepository, TrainerService trainerService, TraineeService traineeService, TrainerRepository trainerRepository, TraineeRepository traineeRepository) {
        this.groupRepository = groupRepository;
        this.trainerRepository = trainerRepository;
        this.traineeRepository = traineeRepository;
    }


    public List<Group> getGroups() {
        return groupRepository.findAll();
    }


    //todo: need to initialize group list
    public List<Group> randomlyAllocated() {
        List<Group> groupList = groupRepository.findAll();
        List<Trainee> traineeList = traineeRepository.findAll();
        List<Trainer> trainerList = trainerRepository.findAll();

        groupList.forEach(group -> group.setTrainees((new ArrayList<>())));
        groupList.forEach(group -> group.setTrainers((new ArrayList<>())));
        Collections.shuffle(trainerList);
        Collections.shuffle(traineeList);

        int groupSize = (int)Math.floor(traineeList.size() / 2.0);

        for (int i = 0; i < traineeList.size(); i++) {
            groupList.get(i % groupSize).getTrainees().add(traineeList.get(i));
            traineeList.get(i).setGrouped(true);
            traineeRepository.save(traineeList.get(i));
        }
        for (int i = 0; i < trainerList.size(); i++) {
            groupList.get(i % groupSize).getTrainers().add(trainerList.get(i));
            trainerList.get(i).setGrouped(true);
            trainerRepository.save(trainerList.get(i));
        }
        for(Group group : groupList) {
            groupRepository.save(group);
        }

        return groupRepository.findAll();
    }
}
