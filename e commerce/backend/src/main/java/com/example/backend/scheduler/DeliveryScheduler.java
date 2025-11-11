package com.example.backend.scheduler;

import com.example.backend.dao.OrderDao;
import com.example.backend.entity.Order;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class DeliveryScheduler {

    @Autowired
    private OrderDao orderDao;

    @Transactional
    @Scheduled(fixedRate = 6000)
    public void autoMarkDelivered() {
        List<Order> list =  orderDao.findByStatus("PENDING");
        System.out.println(list);
        list.stream()
                .filter(o -> o.getOrderDate() != null)
                .filter(o -> Duration.between(o.getOrderDate(), LocalDateTime.now()).toMinutes() >= 1)
                .forEach(o -> {
                    o.setStatus("DELIVERED");
                    o.setDeliveryDate(LocalDateTime.now());
                    orderDao.save(o);
                    System.out.println("✅ Order ID " + o.getId() + " marked as DELIVERED");
                });
    }
}
