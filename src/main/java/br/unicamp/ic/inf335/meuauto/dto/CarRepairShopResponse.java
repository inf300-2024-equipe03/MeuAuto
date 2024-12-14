package br.unicamp.ic.inf335.meuauto.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

import java.util.UUID;

public record CarRepairShopResponse(
        UUID id,
        String name,
        double rating,
        String distanceFromUser,
        String addressDescription
) {
}
