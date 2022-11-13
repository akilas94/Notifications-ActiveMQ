package com.emailsms.demo.repository;

import com.emailsms.demo.entity.MessageLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface MessageLogRepository extends JpaRepository<MessageLog, Long> {

    @Modifying
    @Query(value = "UPDATE message_log SET status = ?2, jms_send_time = ?3 WHERE message_id = ?1", nativeQuery = true)
    int updateStatus(Integer mailId, String status, Date jmsSend);
}
