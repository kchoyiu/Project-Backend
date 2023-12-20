package com.fsse2309.project_backend.repository;

import com.fsse2309.project_backend.data.cartitem.entity.CartItemEntity;
import com.fsse2309.project_backend.data.user.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends CrudRepository<CartItemEntity,Integer> {
  boolean existsByUser_UidAndProduct_Pid(int uid, int pid);
  Optional<CartItemEntity>findByUser_UidAndProduct_Pid(int uid,int pid);

  List<CartItemEntity>findAllByUser(UserEntity user);

  void deleteByUser_UidAndProduct_Pid(Integer uid, int pid);

  int deleteAllByUser(UserEntity user);
}
