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

    public List<Group> getGroupListByUserId(String userId){
        return groupRepository.findByUserId(userId);
    }
    public Group getMainGroupByUserId(String userId){
        return groupRepository.findMainByUserId(userId);
    }
    public Group getGroupByGroupId(int groupId){
        return groupRepository.findByGroupId(groupId);
    }
    public Group createGroup(Group group){
        return groupRepository.createNewGroup(group);
    }
    public Group modifyGroup(Group group) {
        return groupRepository.modifyGroupByUserId(group);
    }
    public void deleteGroup(String groupId){
        groupRepository.deleteByGroupId(groupId);
    }
}
