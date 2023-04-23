package org.study.gallery.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.study.gallery.backend.entity.Item;
import org.study.gallery.backend.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Member findByEmailAndPassword(String email, String password);
}
