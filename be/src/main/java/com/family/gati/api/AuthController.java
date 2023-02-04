//package com.family.gati.api;
//
//import com.family.gati.entity.AuthProvider;
//import com.family.gati.entity.User;
//import com.family.gati.exception.BadRequestException;
//import com.family.gati.payload.ApiResponse;
//import com.family.gati.payload.AuthResponse;
//import com.family.gati.payload.LoginRequest;
//import com.family.gati.payload.SignUpRequest;
//import com.family.gati.repository.UserRepository;
//import com.family.gati.security.jwt.JwtTokenProvider;
//import io.swagger.annotations.Api;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//
//import javax.validation.Valid;
//import java.net.URI;
//
//// 소셜 로그인
//@Slf4j
//@RestController
//@Api(tags = "Social API")
//@RequiredArgsConstructor
//@RequestMapping("/auth")
//public class AuthController {
//
//        private AuthenticationManager authenticationManager;
//        private UserRepository userRepository;
//        private PasswordEncoder passwordEncoder;
//        private JwtTokenProvider jwtTokenProvider;
//
//        @PostMapping("/login")
//        public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
//            log.debug("loginRequest: {}", loginRequest);
//            System.out.println(loginRequest.getEmail());
//            System.out.println(loginRequest.getPassword());
//
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            loginRequest.getEmail(),
//                            loginRequest.getPassword()
//                    )
//            );
//
//            System.out.println(authentication);
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//
//            String token = jwtTokenProvider.createAccessToken(authentication);
//            System.out.println(token);
//            return ResponseEntity.ok(new AuthResponse(token));
//        }
//
//        @PostMapping("/signup")
//        public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
//            if(userRepository.findOneByEmail(signUpRequest.getEmail()) == null) {
//                throw new BadRequestException("Email address already in use.");
//            }
//
//            // Creating user's account
//            User user = new User();
//            user.setUserId(signUpRequest.getUserId());
//            user.setEmail(signUpRequest.getEmail());
//            user.setPassword(signUpRequest.getPassword());
//            System.out.println(user);
//            // 추후 소셜 로그인이 늘어나면 provider 분류해서 지정해주는 logic 작성 필요
//            user.setAuthProvider(AuthProvider.GOOGLE);
//
//            user.setPassword(passwordEncoder.encode(user.getPassword()));
//
//            User result = userRepository.save(user);
//
//            URI location = ServletUriComponentsBuilder
//                    .fromCurrentContextPath().path("/user/me")
//                    .buildAndExpand(result.getUserId()).toUri();
//
//            return ResponseEntity.created(location)
//                    .body(new ApiResponse(true, "User registered successfully@"));
//        }
//
//    }
