package org.example;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class CalculadoraFreteTest {

    private static final double DELTA = 0.001;

    @ParameterizedTest(name = "Peso: {0}kg | Expresso: {1} | Esperado: R$ {2}")
    @CsvSource({
            "0.01, false,  8.02",   // VALOR de fronteira 0.01 (comum)
            "0.01, true,  12.03",   // valor de fronteira 0.01 (expresso)
            "5.00, false, 18.00",   // peso igual (comum)
            "5.00, true,  27.00",   // peso igual (expresso)
            "10.0, false, 28.00",
            "10.0, true,  42.00"
    })
    void deveCalcularFreteComSucesso(double peso, boolean entregaExpressa, double valorEsperado) {
        assertEquals(valorEsperado, CalculadoraFrete.calcular(peso, entregaExpressa), DELTA);
    }

    @ParameterizedTest(name = "testando peso invalido: {0}kg")
    @ValueSource(doubles = {0.0, -0.01, -1.0, -10.0})
    void deveLancarExcecaoParaPesoZeroOuNegativo(double pesoInvalido) {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> CalculadoraFrete.calcular(pesoInvalido, false)
        );

        assertEquals("o peso deve ser maior que zero", exception.getMessage());
    }
}
