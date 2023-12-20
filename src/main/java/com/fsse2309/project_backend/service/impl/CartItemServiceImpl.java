package com.fsse2309.project_backend.service.impl;

import com.fsse2309.project_backend.data.cartitem.domainObject.CartItemDetailData;
import com.fsse2309.project_backend.data.cartitem.entity.CartItemEntity;
import com.fsse2309.project_backend.data.product.entity.ProductEntity;
import com.fsse2309.project_backend.data.user.domainObject.FirebaseUserData;
import com.fsse2309.project_backend.data.user.entity.UserEntity;
import com.fsse2309.project_backend.exception.CartItemNotFoundException;
import com.fsse2309.project_backend.exception.NoStockException;
import com.fsse2309.project_backend.repository.CartItemRepository;
import com.fsse2309.project_backend.service.CartItemService;
import com.fsse2309.project_backend.service.ProductService;
import com.fsse2309.project_backend.service.UserService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {
    private Logger logger = LoggerFactory.getLogger(CartItemServiceImpl.class);

    private UserService userService;

    private ProductService productService;

    private CartItemRepository cartItemRepository;
    @Autowired
    public CartItemServiceImpl(CartItemRepository cartItemRepository, UserService userService,ProductService productService){
        this.cartItemRepository=cartItemRepository;
        this.userService=userService;
        this.productService=productService;
    }
@Override
    public boolean addCartItem(int pid, int quantity, FirebaseUserData firebaseUserData){
      try {
          UserEntity userEntity = getUserEntity(firebaseUserData);

          if (existedByUidAndPid(userEntity.getUid(), pid)) {
              CartItemEntity cartItemEntity = getEntityByPidAndUid(userEntity.getUid(), pid);
              if (!hasStock(pid, cartItemEntity.getQuantity() + quantity)) {
                  throw new NoStockException();
              }
              cartItemEntity.setQuantity(cartItemEntity.getQuantity() + quantity);
              cartItemRepository.save(cartItemEntity);
          } else {
              ProductEntity productEntity = productService.getByPid(pid);
              CartItemEntity cartItemEntity = new CartItemEntity(productEntity, userEntity, quantity);
              if (!hasStock(pid, cartItemEntity.getQuantity() + quantity)) {
                  throw new NoStockException();
              }
              cartItemRepository.save(cartItemEntity);
          }
          return true;
      }catch (NoStockException ex){
          logger.warn(pid+"No stock! Cannot add item");
          throw ex;
      }
    }

    public UserEntity getUserEntity(FirebaseUserData data){
        return userService.getEntityByFirebaseUserData(data);
    }
    @Override
    public List<CartItemDetailData>getAllFirebaseUserData(FirebaseUserData firebaseUserData){
        UserEntity userEntity = getUserEntity(firebaseUserData);
        return getDataListByUser(userEntity);
    }
    @Override
    public CartItemDetailData getCartItemQuantity(FirebaseUserData firebaseUserData, int pid, int quantity) {
        UserEntity userEntity = getUserEntity(firebaseUserData);
        ProductEntity productEntity = productService.getByPid(pid);
        CartItemEntity cartItemEntity = getEntityByPidAndUid(userEntity.getUid(), productEntity.getPid());

        if (!hasStock(pid, cartItemEntity.getQuantity())) {
            throw new NoStockException();
        }
        cartItemEntity.setQuantity(quantity);
        return new CartItemDetailData(cartItemRepository.save(cartItemEntity));
    }

    @Transactional
    @Override
    public boolean deleteCartItemByPid(FirebaseUserData firebaseUserData, int pid){
        UserEntity  userEntity = getUserEntity(firebaseUserData);
        getEntityByPidAndUid(userEntity.getUid(),pid);

        cartItemRepository.deleteByUser_UidAndProduct_Pid(userEntity.getUid(),pid);
        return true;
    }

    public boolean existedByUidAndPid(int uid, int pid){
        return cartItemRepository.existsByUser_UidAndProduct_Pid(uid, pid);
    }

    public CartItemEntity getEntityByPidAndUid (int uid,int pid){
        Optional<CartItemEntity>optionalCartItemEntity = cartItemRepository.findByUser_UidAndProduct_Pid(uid, pid);
        if (optionalCartItemEntity.isPresent()){
            return optionalCartItemEntity.get();
        }else {
            throw new CartItemNotFoundException();
        }
    }
    public boolean hasStock (Integer pid,Integer quantity){
        return productService.isValidStock(pid,quantity);
    }

    @Override
    public List<CartItemEntity> getEntityListByUser(UserEntity user){
        return cartItemRepository.findAllByUser(user);
    }
    public List<CartItemDetailData>getDataListByUser(UserEntity user){
        List<CartItemDetailData> dataList = new ArrayList<>();
        for (CartItemEntity entity : getEntityListByUser(user)) {
            dataList.add(new CartItemDetailData(entity));
        }
        return dataList;
    }
    @Transactional
    @Override
    public void emptyUserCart(UserEntity userEntity){
        cartItemRepository.deleteAllByUser(userEntity);
    }



}
