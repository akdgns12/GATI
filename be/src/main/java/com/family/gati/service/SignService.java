package com.family.gati.service;

import com.family.gati.entity.User;
import com.family.gati.repository.UserRepository;
import com.family.gati.service.token.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignService {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //    @Transactional
    public UserRegisterResponseDto registerUser(UserRegisterRequestDto requestDto) {
        validateDuplicated(requestDto.getEmail());
        User user = userRepository.save(
                User.builder()
                        .email(requestDto.getEmail())
                        .password(passwordEncoder.encode(requestDto.getPassword()))
                        .build());
        return new UserRegisterResponseDto(user.getId(), user.getEmail());
    }

    /**
     * Unique한 값을 가져야하나, 중복된 값을 가질 경우를 검증
     * @param email
     */
    public void validateDuplicated(String email) {
        if (userRepository.findByEmail(email).isPresent())
            throw new UserEmailAlreadyExistsException();
    }

    public UserLoginResponseDto loginMember(UserLoginRequestDto requestDto) {
        User user = userRepository.findByEmail(requestDto.getEmail()).orElseThrow(LoginFailureException::new);
        if (!passwordEncoder.matches(requestDto.getPassword(), member.getPassword()))
            throw new LoginFailureException();
        return new UserLoginResponseDto(member.getId(), jwtTokenProvider.createToken(requestDto.getEmail()));

    }
}
