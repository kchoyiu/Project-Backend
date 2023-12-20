package com.fsse2309.project_backend.api;

import com.fsse2309.project_backend.dto.SuccessResponseDto;
import com.fsse2309.project_backend.dto.TransactionResponseDto;
import com.fsse2309.project_backend.repository.TransactionRepository;
import com.fsse2309.project_backend.service.CartItemService;
import com.fsse2309.project_backend.service.TransactionService;
import com.fsse2309.project_backend.util.JwtUtil;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionApi {
    private TransactionService transactionService;
    private CartItemService cartItemService;
    private TransactionRepository transactionRepository;
    public TransactionApi(TransactionRepository transactionRepository,TransactionService transactionService,CartItemService cartItemService){
        this.transactionRepository=transactionRepository;
        this.transactionService=transactionService;
        this.cartItemService=cartItemService;
    }
    @PostMapping("/perpare")
    public TransactionResponseDto prepareTransaction(JwtAuthenticationToken jwt){
        return new TransactionResponseDto(
                transactionService.prepareTransaction(JwtUtil.getFirebaseUser(jwt))
        );
    }
    @GetMapping("/{tid}")
    public TransactionResponseDto getTransactionByTid(JwtAuthenticationToken jwt,
                                                      @PathVariable int tid){
        return new TransactionResponseDto(transactionService.getTransactionByTid(JwtUtil.getFirebaseUser(jwt),tid));
    }
    @PatchMapping("{tid}/pay")
    public SuccessResponseDto payTransaction(JwtAuthenticationToken jwt,
                                             @PathVariable int tid){
        transactionService.processTransaction(JwtUtil.getFirebaseUser(jwt),tid);
        return new SuccessResponseDto();
    }
    @PatchMapping("{tid}/finish")
    public TransactionResponseDto finishTransaction(JwtAuthenticationToken jwt,
                                                    @PathVariable int tid){
        return new TransactionResponseDto(transactionService.finishTransaction(JwtUtil.getFirebaseUser(jwt),tid));
    }
}
