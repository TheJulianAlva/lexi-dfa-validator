package edu.ittol.is.automatas.lexi.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para el motor principal del validador Lexi (AutomataEngine).
 * Verifica rigurosamente que las cadenas sean aceptadas y/o rechazadas bajo
 * la expresión matemática `(0|1)*(10|1(0|1)(0|1))`.
 */
public class AutomataEngineTest {

    private AutomataEngine engine;

    @BeforeEach
    public void setUp() {
        // Se inicializa el motor con su autómata minimizado antes de cada iteración
        engine = new AutomataEngine();
    }

    @Test
    public void testValidStrings_EndsWith10() {
        // Prueba de la condición 1: Cadenas que terminan estrictamente en "10"
        assertTrue(engine.isValid("10"), "La cadena base '10' debe ser aceptada");
        assertTrue(engine.isValid("010"), "Las combinaciones previas ceros deben ser aceptadas");
        assertTrue(engine.isValid("111110"), "Terminación estricta en '10' superando varios unos");
        assertTrue(engine.isValid("000010"), "Terminación estricta en '10' superando varios ceros");
    }

    @Test
    public void testValidStrings_EndsWith1AndTwoBits() {
        // Prueba de la condición 2: Cadenas que terminan en "1" seguido por ("00"|"01"|"10"|"11")
        assertTrue(engine.isValid("100"), "Debe aceptar si termina en 1 + 00");
        assertTrue(engine.isValid("101"), "Debe aceptar si termina en 1 + 01");
        assertTrue(engine.isValid("110"), "Debe aceptar si termina en 1 + 10");
        assertTrue(engine.isValid("111"), "Debe aceptar si termina en 1 + 11");
        
        // Evaluando pre-combinaciones
        assertTrue(engine.isValid("001100"), "Cadenas extendidas con sufijo de 3 posiciones funcionales deben aplicar");
    }

    @Test
    public void testInvalidStrings_NullOrTooShort() {
        // Cadenas que no alcanzan a cumplir los requisitos de sufijo mínimo (2 o 3 componentes)
        assertFalse(engine.isValid(null), "Las secuencias nulas deben ser rechazadas");
        assertFalse(engine.isValid(""), "Cadenas vacías no entran al lenguaje regular");
        assertFalse(engine.isValid("1"), "El autómata exige por lo menos 2 caracteres mínimos ('10')");
        assertFalse(engine.isValid("0"), "No alcanza los caracteres y no termina de la forma necesaria");
    }

    @Test
    public void testInvalidStrings_DoesNotMeetSuffix() {
        // Cadenas binarias correctas en alfabeto, pero que la teoría indica deben RECHAZARSE
        assertFalse(engine.isValid("11"), "Termina en 11, lo cual no es '10' ni tiene dos bits extra");
        assertFalse(engine.isValid("000"), "Termina en 00, su prefijo/sufijo es un '0', falla con ambas condiciones");
        assertFalse(engine.isValid("011"), "Falla para la segunda condición, solo le precede un '0' en vez del '1' pivote");
    }
}
