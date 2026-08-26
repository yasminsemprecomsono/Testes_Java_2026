package org.example;

public class CalculadoraFrete {

    public static double calcular(double pesoKg, boolean entregaExpressa) {
        if (pesoKg <= 0) {
            throw new IllegalArgumentException("o peso deve ser maior que zero");
        }
        double freteComum = 8.00 + (2.00 * pesoKg);

        if (entregaExpressa) {
            return freteComum * 1.50; // 50%
        }
        return freteComum;
    }
}



