package sdu.edu.kz.YandexEatsAnalogue.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "delivery_partners")
@Getter
@Setter
@NoArgsConstructor
public class DeliveryPartner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long partnerId;

    private String name;
    private String phone;
    private Boolean isActive;

    @OneToMany(mappedBy = "deliveryPartner", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<OrderDelivery> orderDeliveries = new HashSet<>();
}
