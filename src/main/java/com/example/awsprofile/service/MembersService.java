package com.example.awsprofile.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.awsprofile.dto.CreateResponseDto;
import com.example.awsprofile.dto.GetResponseDto;
import com.example.awsprofile.dto.createRequestDto;
import com.example.awsprofile.entity.Member;
import com.example.awsprofile.repository.MembersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MembersService {

	private final MembersRepository membersRepository;

	public CreateResponseDto save(createRequestDto requestDto) {
		Member member = new Member(
			requestDto.getName(),
			requestDto.getAge(),
			requestDto.getMbti()
		);

		Member save = membersRepository.save(member);

		return new CreateResponseDto(
			save.getId(),
			save.getName(),
			save.getAge(),
			save.getMbti()
		);
	}

	public GetResponseDto getMembers(Long id) {
		Member member = membersRepository.findById(id).orElseThrow(
			() -> new IllegalArgumentException("없는 팀원입니다.")
		);

		return new GetResponseDto(
			member.getId(),
			member.getName(),
			member.getAge(),
			member.getMbti()
		);
	}
}
