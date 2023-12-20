package com.fsse2309.project_backend.service;

import com.fsse2309.project_backend.data.cartitem.domainObject.CartItemDetailData;
import com.fsse2309.project_backend.data.cartitem.entity.CartItemEntity;
import com.fsse2309.project_backend.data.user.domainObject.FirebaseUserData;
import com.fsse2309.project_backend.data.user.entity.UserEntity;
import jakarta.transaction.Transactional;

import java.util.List;

public interface CartItemService {


   boolean addCartItem(int pid, int quantity, FirebaseUserData firebaseUserData);


   List<CartItemDetailData>getAllFirebaseUserData(FirebaseUserData firebaseUserData);

   CartItemDetailData getCartItemQuantity(FirebaseUserData firebaseUserData, int pid, int quantity);

   boolean deleteCartItemByPid(FirebaseUserData firebaseUserData, int pid);

   List<CartItemEntity> getEntityListByUser(UserEntity user);

   @Transactional
   void emptyUserCart(UserEntity userEntity);
}
