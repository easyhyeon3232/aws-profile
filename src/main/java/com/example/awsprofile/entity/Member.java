package com.example.awsprofile.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "members")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 50, nullable = false)
	private String name;

	@Column(nullable = false)
	private int age;

	@Column(length = 4, nullable = false)
	private String mbti;

	@Column(length = 500)
	private String profileImageKey;

	public Member(String name, int age, String mbti) {
		this.name = name;
		this.age = age;
		this.mbti = mbti;
	}

	public void updateProfileImageKey(String profileImageKey) {
		this.profileImageKey = profileImageKey;
	}

}
