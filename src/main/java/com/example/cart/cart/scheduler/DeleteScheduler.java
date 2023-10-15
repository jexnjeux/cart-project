package com.example.cart.cart.scheduler;

import com.example.cart.cart.model.entity.CartItem;
import com.example.cart.cart.repository.CartItemRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableCaching
@AllArgsConstructor
public class DeleteScheduler {

  private final CartItemRepository cartItemRepository;

  @Transactional
  @Scheduled(cron = "0 0 0 * * *")
  public void deleteCartScheduling() {
    LocalDate cutoffDate = LocalDate.now().minusDays(30);
    List<CartItem> modifiedDateBeforeList = cartItemRepository.findByModifiedDateBefore(cutoffDate);
    modifiedDateBeforeList.forEach(cartItem ->
        {
          log.info(cartItem.getId() + " 삭제");
          cartItemRepository.deleteById(cartItem.getId());
        }
    );

  }

}
