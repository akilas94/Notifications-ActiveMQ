package com.emailsms.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "message_template")
@Data
public class MessageTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "template_id")
    private Long templateId;
    @Lob
    @Column(name = "template", length = 16777215 ,nullable = false)
    private String template;
    @Column(name = "source")
    private String source;
}
