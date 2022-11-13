package com.emailsms.demo.repository;

import com.emailsms.demo.entity.MessageTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageTemplateRepository extends JpaRepository<MessageTemplate, Long> {
}
