package com.family.gati.service;

import com.family.gati.entity.Group;
import com.family.gati.entity.GroupMember;
import com.family.gati.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;

//    public List<Group> getGroupListByUserId(String userId){
//        return groupRepository.findAllByUserId(userId);
//    }
//    public Group getMainGroupByUserId(String userId){
//        return groupRepository.findMainByUserId(userId);
//    }
    public Group getGroupByGroupId(int groupId){
        return groupRepository.findByGroupId(groupId);
    }
    public void createGroup(Group group){
        groupRepository.save(group);
    }
    public void modifyGroup(Group group) {
        groupRepository.save(group);
    }

    @Transactional
    public void deleteGroup(String groupId){
        groupRepository.deleteByGroupId(groupId);
    }
}
