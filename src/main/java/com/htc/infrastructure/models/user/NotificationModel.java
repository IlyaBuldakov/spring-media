package com.htc.infrastructure.models.user;

import com.htc.domain.entities.notification.Notification;
import com.htc.domain.entities.notification.NotificationType;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
//@Table(name = "notifications")
public class NotificationModel {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String type;

  private String message;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}