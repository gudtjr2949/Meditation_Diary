package com.project.mt.authentication.application;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.mt.authentication.domain.AuthTokens;
import com.project.mt.authentication.infra.kakao.KakaoLoginParams;
import com.project.mt.member.dto.response.MemberResponseDto;

import lombok.RequiredArgsConstructor;


/**
 * 카카오 서버에서 받은 인증코드를 가지고 들어오는 Controller
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final OAuthLoginService oAuthLoginService;

    /**
     * @param code : 소셜 서비스, 인가코드를 가지고있는 파라미터
     * @return
     */
    @GetMapping("/kakao")
    public ResponseEntity<?> loginKakao(@RequestParam("code") String code) {
        KakaoLoginParams params = new KakaoLoginParams(code);
        return ResponseEntity.ok(oAuthLoginService.login(params));
    }

}
