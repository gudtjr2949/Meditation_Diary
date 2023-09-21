package com.project.mt.meditation.controller;

import java.io.IOException;
import java.util.Map;

import com.project.mt.fileupload.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.mt.fileupload.config.AwsS3Uploader;
import com.project.mt.meditation.dto.request.MeditationRequestDto;
import com.project.mt.meditation.dto.response.MeditationResponseDto;
import com.project.mt.meditation.service.MeditationService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/meditaion")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class MeditationController {

	private final MeditationService meditationService;
	private final FileUploadService fileUploadService;
	private AwsS3Uploader awsS3Uploader;

	@PostMapping
	public void save(@RequestBody MeditationRequestDto requestDto) throws IOException {
		String[] imageFileNames = awsS3Uploader.upload(requestDto.getImages(), "image");

		fileUploadService.getText(imageFileNames);
	}
}