package com.example.awsprofile.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GetResponseDto {
	private final Long id;
	private final String name;
	private final int age;
	private final String mbti;

}
