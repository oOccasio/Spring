package hello.hello_spring.Repository;

import hello.hello_spring.domain.Member; // JPA 엔터티로 설정된 Member 클래스 import
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    @Override
    Optional<Member> findByName(String name);

}
