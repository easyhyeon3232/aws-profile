package com.example.awsprofile.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.awsprofile.dto.CreateResponseDto;
import com.example.awsprofile.dto.GetResponseDto;
import com.example.awsprofile.dto.createRequestDto;
import com.example.awsprofile.service.MembersService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class MembersController {

	private final MembersService membersService;

	@PostMapping("/members")
	public ResponseEntity<CreateResponseDto> create(@RequestBody createRequestDto requestDto) {
		log.info("[API - LOG] POST/api/members");
		return ResponseEntity.ok(membersService.save(requestDto));

	}

	@GetMapping("/members/{id}")
	public ResponseEntity<GetResponseDto> getMembers(@PathVariable Long id) {
		log.info("[API - LOG] GET/api/members/{id}");
		return ResponseEntity.ok(membersService.getMembers(id));
	}
}
