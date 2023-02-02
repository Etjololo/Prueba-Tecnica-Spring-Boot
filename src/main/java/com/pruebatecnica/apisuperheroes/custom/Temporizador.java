package com.pruebatecnica.apisuperheroes.custom;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
@Log4j2
@Aspect
@Component
public class Temporizador {
    @Around("@annotation(Tempo)")
    public Object temporizarEjecucion(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.nanoTime();

        Object resultado = joinPoint.proceed();

        long finish = System.nanoTime();
        long timeElapsed = (finish - start) / 1000000;

        System.out.println("Tiempo de ejecucion de la petiticion: " + timeElapsed + " milisegundos");
        log.info("Tiempo de ejecucion de la petiticion: {} milisegundos", timeElapsed);

        return resultado;
    }
}
