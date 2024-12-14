package br.unicamp.ic.inf335.meuauto.dto;

public record CarRequest(
        String value,
        String brand,
        String modelName,
        String year,
        String fuel
) {
}
