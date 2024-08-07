package com.personal.common.core.repo;

import com.personal.common.core.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepo extends JpaRepository<MessageEntity, String> {
}
