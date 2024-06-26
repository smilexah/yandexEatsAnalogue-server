package sdu.edu.kz.YandexEatsAnalogue.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sdu.edu.kz.YandexEatsAnalogue.dto.OrderDeliveryDTO;
import sdu.edu.kz.YandexEatsAnalogue.entity.DeliveryPartner;
import sdu.edu.kz.YandexEatsAnalogue.entity.Order;
import sdu.edu.kz.YandexEatsAnalogue.entity.OrderDelivery;
import sdu.edu.kz.YandexEatsAnalogue.repository.OrderDeliveryRepository;
import sdu.edu.kz.YandexEatsAnalogue.service.OrderDeliveryService;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderDeliveryServiceImpl implements OrderDeliveryService {
    private final OrderDeliveryRepository orderDeliveryRepository;

    @Override
    public List<OrderDelivery> findAllOrderDeliveries() {
        return orderDeliveryRepository.findAll();
    }

    @Override
    public Optional<OrderDelivery> findOrderDeliveryById(Long id) {
        return orderDeliveryRepository.findById(id);
    }

    @Override
    public void saveOrderDelivery(OrderDeliveryDTO orderDeliveryDTO) {
        OrderDelivery orderDelivery = new OrderDelivery();

        Order order = new Order();
        order.setOrderId(orderDeliveryDTO.getOrderId());
        orderDelivery.setOrder(order);

        DeliveryPartner deliveryPartner = new DeliveryPartner();
        deliveryPartner.setPartnerId(orderDeliveryDTO.getPartnerId());
        orderDelivery.setDeliveryPartner(deliveryPartner);

        orderDelivery.setPickupTime(orderDeliveryDTO.getPickupTime());
        orderDelivery.setDeliveryTime(orderDeliveryDTO.getDeliveryTime());
        orderDelivery.setStatus(orderDeliveryDTO.getStatus());

        orderDeliveryRepository.save(orderDelivery);
    }

    @Override
    public void updateOrderDelivery(OrderDeliveryDTO orderDeliveryDTO, Long id) {
        Optional<OrderDelivery> orderDeliveryOptional = orderDeliveryRepository.findById(id);
        if (orderDeliveryOptional.isEmpty()) {
            throw new EntityNotFoundException("Order delivery not found");
        }
        OrderDelivery orderDelivery = orderDeliveryOptional.get();

        orderDelivery.setPickupTime(orderDeliveryDTO.getPickupTime());
        orderDelivery.setDeliveryTime(orderDeliveryDTO.getDeliveryTime());
        orderDelivery.setStatus(orderDeliveryDTO.getStatus());

        orderDeliveryRepository.save(orderDelivery);
    }

    @Override
    @Transactional
    public void deleteOrderDelivery(Long id) {
        orderDeliveryRepository.deleteById(id);
    }

}
