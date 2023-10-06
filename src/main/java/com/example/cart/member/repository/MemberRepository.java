package com.example.cart.member.repository;

import com.example.cart.member.model.entity.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberRepository extends JpaRepository<Member, Long> {

  boolean existsByUsername(String username);

  Optional<Member> findByUsername(String username);

}
