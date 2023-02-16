package com.family.gati.service;

import com.family.gati.dto.MailDto;
import com.family.gati.dto.UserSelectMainDto;
import com.family.gati.dto.UserUpdateDto;
import com.family.gati.entity.User;
import com.family.gati.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
//@Transactional(readOnly = true) // 기본적으로 트랜잭션 안에서만 데이터 변경하게 설정(그만큼 최적화 되어 읽는게 빨라짐)
@RequiredArgsConstructor // final, @NotNull 붙은 필드의 생성자 자동 생성해주는 롬복 어노테이션
@Slf4j
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JavaMailSender javaMailSender;

    @Transactional
    public void join(User user){
        validateDuplicateUser(user);
        user.setUserId(user.getUserId());
        user.setEmail(user.getEmail());

        user.setPassword(user.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setNickName(user.getNickName());
        user.setBirth(user.getBirth());
        user.setPhoneNumber(user.getPhoneNumber());

        user.setPlusMinus(user.getPlusMinus());
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());

        userRepository.save(user); // 여기서 뭔가 문제 발생..
    }

    public User getUserByUserId(String userId){
        return userRepository.findByUserId(userId);
    }

    public User getUserByUserSeq(int userSeq){
        return userRepository.findByUserSeq(userSeq);
    }

    public User getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    // 메인 그룹 선택
    @Transactional
    public void selectMainFamily(UserSelectMainDto selectMainDto){
        User user = userRepository.findByUserId(selectMainDto.getUserId());
        user.setMainFamily(selectMainDto.getMainFamily());
    }

    @Transactional
    public void updateUser(String userId, final UserUpdateDto request) {
        User originUser = userRepository.findByUserId(userId);

        if(originUser == null){
            log.debug("회원 못찾음: {}", originUser);
            return;
        }

        originUser.setEmail(request.getEmail());
        originUser.setNickName(request.getNickName());
        originUser.setBirth(request.getBirth());
        originUser.setPhoneNumber(request.getPhoneNumber());
        originUser.setUpdateTime(LocalDateTime.now());

        userRepository.save(originUser);
    }

    @Transactional
    public void deleteUser(int userSeq){
        userRepository.deleteByUserSeq(userSeq);
    }

    // 아이디 중복 체크
    private void validateDuplicateUser(User user){
        User findUser = userRepository.findByEmail(user.getUserId());
        if(findUser != null) throw new IllegalStateException("일치하는 아이디 이미 존재");
    }

    // 메일 내용을 생성하고 임시 비밀번호로 회원 비밀번호 변경
    public MailDto createMailAndChangePassword(String email){
        String str = getTempPassword();
        MailDto mail = new MailDto();
        mail.setAddress(email);
        mail.setTitle("Gati 임시비밀번호 안내 이메일 입니다.");
        mail.setMessage("안녕하세요. Gati 임시비밀번호 안내 관련 이메일 입니다." + " 회원님의 임시 비밀번호는 "
                + str + " 입니다." + "로그인 후에 비밀번호를 변경을 해주세요");
        updatePassword(str, email);

        return mail;
    }

    public MailDto createMailForId(String email){
        User user = userRepository.findByEmail(email);
        String userId = user.getUserId();

        MailDto mail = new MailDto();
        mail.setAddress(email);
        mail.setTitle("Gati 아이디 찾기 안내 이메일 입니다.");
        mail.setMessage("안녕하세요. Gati 아이디 찾기 안내 관련 이메일 입니다." + " 회원님의 아이디는 "
                + userId + " 입니다.");

        return mail;
    }

    // 임시 비밀번호 10자리
    public String getTempPassword(){
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        String str = "";

        // 문자 배열 길이의 값을 랜덤으로 10개를 뽑아 구문을 작성함
        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
        return str;
    }

    @Transactional
    // 임시 비밀번호로 업데이트
    public void updatePassword(String str, String email){
        String userPassword = str;
        User user = userRepository.findByEmail(email);

        user.setPassword(passwordEncoder.encode(str));
        userRepository.save(user);
    }

    // 이메일 전송
    public void mailSend(MailDto mailDto, String from){
        log.debug("이메일 전송 완료");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailDto.getAddress());
        message.setSubject(mailDto.getTitle());
        message.setText(mailDto.getMessage());
        message.setFrom(from); // 보내는 사람(관리자)
        message.setReplyTo(from);

        javaMailSender.send(message);
    }
    
    // 비밀번호 변경
    @Transactional
    public void changePassword(User user, String newPassword){
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
}
