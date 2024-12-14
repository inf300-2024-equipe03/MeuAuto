package br.unicamp.ic.inf335.meuauto.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateSchedulingRequest(
        LocalDateTime datetime,
        UUID repairShopId
) {
}