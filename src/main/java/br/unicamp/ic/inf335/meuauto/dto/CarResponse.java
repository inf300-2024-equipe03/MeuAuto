package br.unicamp.ic.inf335.meuauto.dto;

import br.unicamp.ic.inf335.meuauto.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.UUID;

public record CarResponse(
        UUID id,
        String value,
        String brand,
        String modelDescription,
        String year,
        String fuel,
        UUID owner
) {
}
