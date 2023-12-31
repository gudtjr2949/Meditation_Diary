package com.project.mt.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    /*
     * 400 BAD_REQUEST: 잘못된 요청
     */
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "Invalid request."),

    /*
     * 401 UNAUTHORIZED: 인증되지 않은 사용자의 요청
     */
    UNAUTHORIZED_REQUEST(HttpStatus.UNAUTHORIZED, "헤더에 토큰이 없거나 적합하지 않은 형태입니다."),
    VALID_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "토큰이 만료되었습니다."),

    /*
     * 403 FORBIDDEN: 권한이 없는 사용자의 요청
     */
    FORBIDDEN_ACCESS(HttpStatus.FORBIDDEN, "Forbidden."),

    /*
     * 404 NOT_FOUND: 리소스를 찾을 수 없음
     */
    NOT_FOUND(HttpStatus.NOT_FOUND, "Not found."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND,"존재하지 않는 회원입니다."),
    MEDITATION_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 명상 글입니다."),
    MEMO_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 메모 글입니다."),
    VOICE_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 목소리입니다."),

    MEMO_NOT_DELETE(HttpStatus.NOT_FOUND, "메모 글 삭제에 실패했습니다."),
    MEMO_NOT_MODIFY(HttpStatus.NOT_FOUND, "메모 글 수정에 실패했습니다."),


    /*
     * 405 METHOD_NOT_ALLOWED: 허용되지 않은 Request Method 호출
     */
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "Not allowed method."),

    /*
     * 500 INTERNAL_SERVER_ERROR: 내부 서버 오류
     */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Server error.");


    private final HttpStatus httpStatus;
    private final String message;
}
