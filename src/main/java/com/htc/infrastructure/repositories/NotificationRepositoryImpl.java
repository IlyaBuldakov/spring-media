package com.htc.infrastructure.repositories;

import com.htc.infrastructure.models.user.NotificationModel;
import org.springframework.data.repository.CrudRepository;

public interface NotificationRepositoryImpl extends CrudRepository<NotificationModel, Long> {

}
