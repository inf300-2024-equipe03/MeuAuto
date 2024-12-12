package br.unicamp.ic.inf335.meuauto.integration.tabelafipe;

public record CompleteModelResponse(
        String TipoVeiculo,
        String Valor,
        String Marca,
        String Modelo,
        String AnoModelo,
        String Combustivel,
        String CodigoFipe,
        String MesReferencia,
        String SiglaCombustivel
) {}
