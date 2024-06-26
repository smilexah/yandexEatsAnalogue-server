package sdu.edu.kz.YandexEatsAnalogue.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sdu.edu.kz.YandexEatsAnalogue.dto.DeliveryPartnerDTO;
import sdu.edu.kz.YandexEatsAnalogue.service.DeliveryPartnerService;
import sdu.edu.kz.YandexEatsAnalogue.utils.mapper.ModelMapperUtil;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/partners")
@RequiredArgsConstructor
public class DeliveryPartnerController {
    private final DeliveryPartnerService deliveryPartnerService;
    private final ModelMapperUtil modelMapperUtil;

    @GetMapping
    public ResponseEntity<?> getAllDeliveryPartners() {
        return new ResponseEntity<>(deliveryPartnerService.findAllDeliveryPartners().stream()
                .map(partner -> modelMapperUtil.map(partner, DeliveryPartnerDTO.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{partnerId}")
    public ResponseEntity<DeliveryPartnerDTO> getDeliveryPartnerById(@PathVariable Long partnerId) {
        return deliveryPartnerService.findDeliveryPartnerById(partnerId)
                .map(partner -> new ResponseEntity<>(modelMapperUtil.map(partner, DeliveryPartnerDTO.class),
                        HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<DeliveryPartnerDTO> createDeliveryPartner(
            @RequestBody DeliveryPartnerDTO deliveryPartnerDTO) {
        deliveryPartnerService.saveDeliveryPartner(modelMapperUtil.map(deliveryPartnerDTO, DeliveryPartnerDTO.class));
        return new ResponseEntity<>(deliveryPartnerDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{partnerId}")
    public ResponseEntity<DeliveryPartnerDTO> updateDeliveryPartner(@PathVariable Long partnerId,
            @RequestBody DeliveryPartnerDTO deliveryPartnerDTO) {
        deliveryPartnerService.updateDeliveryPartner(modelMapperUtil.map(deliveryPartnerDTO, DeliveryPartnerDTO.class),
                partnerId);
        return new ResponseEntity<>(deliveryPartnerDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{partnerId}")
    public ResponseEntity<Void> deleteDeliveryPartner(@PathVariable Long partnerId) {
        deliveryPartnerService.deleteDeliveryPartner(partnerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
