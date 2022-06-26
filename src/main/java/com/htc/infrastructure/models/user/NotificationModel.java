package com.htc.infrastructure.models.user;

import javax.persistence.*;

@Entity
//@Table(name = "notifications")
public class NotificationModel {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String type, message;
}