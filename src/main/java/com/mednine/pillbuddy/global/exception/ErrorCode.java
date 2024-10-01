package com.mednine.pillbuddy.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    USER_AUTHENTICATION_REQUIRED(UNAUTHORIZED, "인증이 필요한 회원입니다."),
    USER_NOT_FOUND(NOT_FOUND, "회원 정보를 찾을 수 없습니다."),
    USER_ALREADY_REGISTERED_EMAIL(CONFLICT, "이미 등록된 이메일입니다."),
    USER_ALREADY_REGISTERED_LOGIN_ID(CONFLICT, "이미 등록된 아이디입니다."),
    USER_ALREADY_REGISTERED_PHONE_NUMBER(CONFLICT, "이미 등록된 전화번호입니다."),
    USER_INVALID_TYPE(BAD_REQUEST, "유효하지 않은 사용자 유형입니다."),

    JWT_TOKEN_INVALID(UNAUTHORIZED, "유효하지 않은 JWT 토큰입니다."),
    JWT_TOKEN_EXPIRED(UNAUTHORIZED, "JWT 토큰이 만료되었습니다."),
    JWT_TOKEN_UNSUPPORTED(BAD_REQUEST, "지원되지 않는 JWT 토큰입니다."),
  
    MEDICATION_NOT_FOUND(NOT_FOUND, "약 정보를 찾을 수 없습니다."),
    MEDICATION_NOT_MATCHED(BAD_REQUEST, "약 정보가 일치하지 않습니다."),
    MEDICATION_NOT_REMOVED(CONFLICT, "약 정보 삭제에 실패했습니다."),
    MEDICATION_NOT_REGISTERED(CONFLICT, "약 정보 등록에 실패했습니다."),
    MEDICATION_NOT_MODIFIED(CONFLICT, "약 정보 수정에 실패했습니다."),
    MEDICATION_NOT_VALID(BAD_REQUEST, "유효하지 않는 약 정보입니다."),
    MEDICATION_IS_NULL(NOT_FOUND, "현재 복용중인 약이 없습니다."),

    CARETAKER_CAREGIVER_NOT_FOUND(CONFLICT, "부모 정보 등록에 실패했습니다"),
    CARETAKER_CAREGIVER_REMOVED(CONFLICT, "부모 정보 삭제에 실패했습니다"),

    CARETAKER_NOT_FOUND(NOT_FOUND, "사용자 정보를 찾을 수 없습니다."),
    CARETAKER_NOT_MATCHED(BAD_REQUEST, "사용자 정보가 일치하지 않습니다."),
    CARETAKER_NOT_REMOVED(CONFLICT, "사용자 정보 삭제에 실패했습니다."),
    CARETAKER_NOT_REGISTERED(CONFLICT, "사용자 정보 등록에 실패했습니다."),
    CARETAKER_NOT_MODIFIED(CONFLICT, "사용자 정보 수정에 실패했습니다."),
    CARETAKER_NOT_VALID(BAD_REQUEST, "유효하지 않은 사용자 정보입니다."),
    CARETAKER_ALREADY_REGISTERED(CONFLICT, "이미 등록된 사용자 정보입니다"),

    CAREGIVER_NOT_FOUND(NOT_FOUND, "보호자 정보를 찾을 수 없습니다."),
    CAREGIVER_NOT_MATCHED(BAD_REQUEST, "보호자 정보가 일치하지 않습니다."),
    CAREGIVER_NOT_REMOVED(CONFLICT, "보호자 정보 삭제에 실패했습니다."),
    CAREGIVER_NOT_REGISTERED(CONFLICT, "보호자 정보 등록에 실패했습니다."),
    CAREGIVER_NOT_MODIFIED(CONFLICT, "보호자 정보 수정에 실패했습니다."),
    CAREGIVER_NOT_VALID(BAD_REQUEST, "유효하지 않은 보호자 정보입니다."),

    CARETAKER_CAREGIVER_NOT_REGISTERED(CONFLICT, "이미 등록된 보호자 정보입니다"),
    CARETAKER_CAREGIVER_NOT_VALID(CONFLICT, "유효하지 않은 보호자 정보입니다"),
    CAREGIVER_CARETAKER_NOT_MATCHED(BAD_REQUEST, "사용자 정보가 일치하지 않습니다"),
    CARETAKER_CAREGIVER_NOT_MATCHED(BAD_REQUEST, "보호자 정보가 일치하지 않습니다"),


    //e약은요 API 예외
    REQUIRED_VALUE(BAD_REQUEST,"제품명은 필수입니다."),
    OUT_OF_PAGE(BAD_REQUEST,"페이지 설정이 잘못됐습니다."),
    ERROR_CONNECTION(GATEWAY_TIMEOUT,"외부 API 서버에 연결하는 중 오류가 발생했습니다."),
    NETWORK_ERROR(SERVICE_UNAVAILABLE, "네트워크 통신 중 오류가 발생했습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
