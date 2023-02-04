package com.family.gati.service;

import com.family.gati.entity.Group;
import com.family.gati.entity.GroupMember;
import com.family.gati.repository.GroupMemberRepository;
import com.family.gati.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupMemberService {

    private final GroupMemberRepository groupMemberRepository;

    public List<GroupMember> getGroupListByUserId(String userId){
        return groupMemberRepository.findAllByUserId(userId);
    }
    public GroupMember getMainGroupMemberByUserId(String userId){
        return groupMemberRepository.findMainByUserId(userId);
    }
}
