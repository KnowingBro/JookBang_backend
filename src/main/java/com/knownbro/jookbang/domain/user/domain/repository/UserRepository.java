package com.knownbro.jookbang.domain.user.domain.repository;

import com.knownbro.jookbang.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
