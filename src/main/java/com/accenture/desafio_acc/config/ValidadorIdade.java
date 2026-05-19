package com.accenture.desafio_acc.config;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component
public class ValidadorIdade {

    private static final int MAIORIDADE_BRASIL = 18;

    public static boolean isMaiorDeIdade(LocalDate dataNascimento) {
        int idade = Period.between(dataNascimento, LocalDate.now()).getYears();

        return idade >= MAIORIDADE_BRASIL;
    }
}
