package com.htc.application.dto;

import com.htc.domain.entities.user.User;

interface DataTransferObject<DtoT extends DataTransferObject<DtoT, EntityT>, EntityT> {
  DtoT fromEntity(EntityT entity);

  EntityT toEntity(DtoT e);
}

class UserBasicDto implements DataTransferObject<UserBasicDto, User> {
  @Override
  public UserBasicDto fromEntity(User entity) {
    return null;
  }

  @Override
  public User toEntity(UserBasicDto e) {
    return null;
  }
}

class UserRequestDto implements DataTransferObject<UserRequestDto, User> {

  @Override
  public UserRequestDto fromEntity(User entity) {
    return null;
  }

  @Override
  public User toEntity(UserRequestDto e) {
    return null;
  }
}
