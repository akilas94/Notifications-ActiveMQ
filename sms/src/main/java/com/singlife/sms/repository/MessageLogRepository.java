package com.singlife.sms.repository;

import com.singlife.sms.entity.MessageLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface MessageLogRepository extends JpaRepository<MessageLog, Integer> {
    @Modifying
    @Query(value = "UPDATE message_log SET status = ?2, message_send_time = ?3 WHERE message_id = ?1", nativeQuery = true)
    int updateStatus(Long mailId, String status, Date mailSentTime);
}
