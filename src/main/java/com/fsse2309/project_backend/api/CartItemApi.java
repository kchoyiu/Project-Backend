package com.fsse2309.project_backend.api;

import com.fsse2309.project_backend.config.EnvConfig;
import com.fsse2309.project_backend.data.cartitem.domainObject.CartItemDetailData;
import com.fsse2309.project_backend.data.product.domainObject.Product;
import com.fsse2309.project_backend.dto.CartItemResponseDto;
import com.fsse2309.project_backend.dto.ListAllProductResponseDto;
import com.fsse2309.project_backend.dto.SuccessResponseDto;
import com.fsse2309.project_backend.service.CartItemService;
import com.fsse2309.project_backend.util.JwtUtil;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cart")
@CrossOrigin({EnvConfig.devEnvBaseUrl,EnvConfig.prodEnvBaseUrl})
public class CartItemApi {
    private CartItemService cartItemService;

    public CartItemApi (CartItemService cartItemService){
        this.cartItemService=cartItemService;
    }

    @PutMapping("/{pid}/{quantity}")
    public SuccessResponseDto addCartItem(JwtAuthenticationToken jwt,
                            @PathVariable int pid,
                            @PathVariable int quantity) {
        cartItemService.addCartItem(pid, quantity,
                JwtUtil.getFirebaseUser(jwt)
        );
        return new SuccessResponseDto();
    }

    @GetMapping()
    public List<CartItemResponseDto> getAllProduct(JwtAuthenticationToken jwt) {
    List<CartItemResponseDto> cartItemResponseDtoList = new ArrayList<>();
    for (CartItemDetailData data : cartItemService.getAllFirebaseUserData(JwtUtil.getFirebaseUser(jwt))){
        cartItemResponseDtoList.add(new CartItemResponseDto(data));
        }
    return cartItemResponseDtoList;
    }

    @PatchMapping("/{pid}/{quantity}")
    public CartItemResponseDto patchCartQuantity (JwtAuthenticationToken jwt,
                                                  @PathVariable int pid,
                                                  @PathVariable int quantity){
        return new CartItemResponseDto(cartItemService.getCartItemQuantity(JwtUtil.getFirebaseUser(jwt),
                pid,
                quantity));
    }

    @DeleteMapping("/{pid}")
    public SuccessResponseDto deleteCartItem(JwtAuthenticationToken jwt,
                                             @PathVariable int pid){
        cartItemService.deleteCartItemByPid(JwtUtil.getFirebaseUser(jwt),pid);
        return new SuccessResponseDto();
    }
}
