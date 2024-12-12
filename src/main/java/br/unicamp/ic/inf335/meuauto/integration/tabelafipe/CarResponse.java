package br.unicamp.ic.inf335.meuauto.integration.tabelafipe;

import java.util.List;

public record CarResponse(
        List<Car> modelos
) {
}

