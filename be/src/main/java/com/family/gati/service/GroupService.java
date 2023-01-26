package com.family.gati.service;

import com.family.gati.entity.Group;
import com.family.gati.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;

    public List<Group> getGroupByUserId(String userId){
        return groupRepository.findByUserId(userId);
    }
}
