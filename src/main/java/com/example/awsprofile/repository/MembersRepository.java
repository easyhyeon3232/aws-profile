package com.example.awsprofile.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.awsprofile.entity.Member;

public interface MembersRepository extends JpaRepository<Member, Long> {
}
