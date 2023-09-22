package com.project.mt.meditation.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.RequiredArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.mt.fileupload.config.AwsS3Uploader;
import com.project.mt.meditation.dto.response.MeditationListResponseDto;
import com.project.mt.meditation.dto.response.MeditationResponseDto;
import com.project.mt.meditation.service.MeditationService;

import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/meditation")
@RequiredArgsConstructor
public class MeditationController {

	private final MeditationService meditationService;
	private final AwsS3Uploader awsS3Uploader;

	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<?> save(@RequestParam List<MultipartFile> images,
								  @RequestParam Long memberIdx) throws IOException {
		Map<String, Object> response = new HashMap<>();

		String[] imageUrl = awsS3Uploader.upload(images, "image");

		Long meditationIdx = meditationService.getMedia(memberIdx, imageUrl);

		response.put("meditationIdx", meditationIdx);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/{meditationIdx}")
	public ResponseEntity<?> findMeditationByMeditationIdx(@PathVariable("meditationIdx") Long meditationIdx) {
		return ResponseEntity.ok(meditationService.findMeditationByMeditationIdx(meditationIdx));
	}

	@GetMapping("/list/{memberIdx}")
	public ResponseEntity<?> findMeditationByMemberIdx(@PathVariable("memberIdx") Long memberIdx) {
		Map<String, List<MeditationListResponseDto>> response = new HashMap<>();
		response.put("meditationList", meditationService.findMeditationByMemberIdx(memberIdx));
		return ResponseEntity.ok(response);
	}
}
