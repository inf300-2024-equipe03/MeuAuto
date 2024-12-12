package br.unicamp.ic.inf335.meuauto.dto;

import java.util.UUID;

public record CompleteCarDTO(
        String value,
        String brand,
        String modelName,
        String year,
        String fuel,
        UUID id
) {
}
