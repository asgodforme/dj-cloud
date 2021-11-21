package com.dj.cloud.user.repository;

import com.dj.cloud.user.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Integer> {
}
