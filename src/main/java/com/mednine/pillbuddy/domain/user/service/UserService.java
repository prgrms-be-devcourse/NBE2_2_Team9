package com.mednine.pillbuddy.domain.user.service;

import com.mednine.pillbuddy.domain.user.caregiver.entity.Caregiver;
import com.mednine.pillbuddy.domain.user.caregiver.repository.CaregiverRepository;
import com.mednine.pillbuddy.domain.user.caretaker.entity.Caretaker;
import com.mednine.pillbuddy.domain.user.caretaker.repository.CaretakerRepository;
import com.mednine.pillbuddy.domain.user.dto.JoinDto;
import com.mednine.pillbuddy.domain.user.dto.LoginDto;
import com.mednine.pillbuddy.domain.user.dto.UserDto;
import com.mednine.pillbuddy.global.exception.ErrorCode;
import com.mednine.pillbuddy.global.exception.PillBuddyCustomException;
import com.mednine.pillbuddy.global.jwt.JwtToken;
import com.mednine.pillbuddy.global.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final CaretakerRepository caretakerRepository;
    private final CaregiverRepository caregiverRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    public UserDto join(JoinDto joinDto) {
        // 회원가입 유효성 검증
        if (caregiverRepository.existsByLoginId(joinDto.getLoginId()) || caretakerRepository.existsByLoginId(
                joinDto.getLoginId())) {
            throw new PillBuddyCustomException(ErrorCode.USER_ALREADY_REGISTERED_LOGIN_ID);
        }
        if (caregiverRepository.existsByEmail(joinDto.getEmail()) || caretakerRepository.existsByEmail(
                joinDto.getEmail())) {
            throw new PillBuddyCustomException(ErrorCode.USER_ALREADY_REGISTERED_EMAIL);
        }
        if (caregiverRepository.existsByPhoneNumber(joinDto.getPhoneNumber())
                || caretakerRepository.existsByPhoneNumber(joinDto.getPhoneNumber())) {
            throw new PillBuddyCustomException(ErrorCode.USER_ALREADY_REGISTERED_PHONE_NUMBER);
        }

        joinDto.encodePassword(passwordEncoder);

        // 사용자 타입에 따라 회원 저장
        return switch (joinDto.getUserType()) {
            case CAREGIVER -> {
                Caregiver savedCaregiver = caregiverRepository.save(joinDto.toCaregiverEntity());
                yield new UserDto(savedCaregiver);
            }
            case CARETAKER -> {
                Caretaker savedCaretaker = caretakerRepository.save(joinDto.toCaretakerEntity());
                yield new UserDto(savedCaretaker);
            }
        };
    }

    @Transactional(readOnly = true)
    public JwtToken login(LoginDto loginDto) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginDto.getLoginId(), loginDto.getPassword());

        // 사용자 유효성 검증
        // TODO: 2024-09-30 시큐리티 예외처리 공부 후 추가 예정
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        return jwtTokenProvider.generateToken(authentication);
    }
}