package org.example;

public class ContaBancaria {
    private String titular;
    private double saldo;

    public ContaBancaria(String titular, double saldo) {
        this.titular = titular;
        this.saldo = 100.0;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void depositar(double valor){
        if (valor <=0){
            throw new IllegalArgumentException("O valor a depositar deve ser maior que zero");
        }
        this.saldo += valor;
    }
    public void sacar(double valor){
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor a sacar deve ser maior que zero");
        }
        if (valor > saldo){
            throw new IllegalArgumentException("O valor so saque supera o saldo disponivel");
        }
        this.saldo -= valor;
    }
}