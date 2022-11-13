package com.emailsms.demo.repository;

import com.emailsms.demo.entity.EmailLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface EmailLogRepository extends JpaRepository<EmailLog, Integer> {

    @Modifying
    @Query(value = "UPDATE email_log SET send_status = ?2, jms_sent_time = ?3 WHERE mail_id = ?1", nativeQuery = true)
    int updateStatus(Integer mailId, String status, Date jmsSent);
}
