package edu.ittol.is.automatas.lexi.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para las utilidades de validación complementarias.
 */
public class ValidationUtilsTest {

    @Test
    public void testIsStrictBinary_ValidCases() {
        // Cadenas completamente puras bajo alfabeto {0,1} que DEBEN ser aceptadas
        assertTrue(ValidationUtils.isStrictBinary(""), "Una cadena vacía debe permitirse");
        assertTrue(ValidationUtils.isStrictBinary("0"), "Un simple 0 es válido");
        assertTrue(ValidationUtils.isStrictBinary("1"), "Un simple 1 es válido");
        assertTrue(ValidationUtils.isStrictBinary("01010101"), "Multiples composiciones son válidas");
    }

    @Test
    public void testIsStrictBinary_InvalidCases() {
        // Cadenas con errores que DEBEN evitar pasar al Autómata Finito y bloquearse
        assertFalse(ValidationUtils.isStrictBinary(null), "Un valor nulo debe ser denegado automáticamente");
        assertFalse(ValidationUtils.isStrictBinary("2"), "Números fuera del sistema binario fallan");
        assertFalse(ValidationUtils.isStrictBinary("1001a"), "Los alfanuméricos fallan");
        assertFalse(ValidationUtils.isStrictBinary("0 1"), "Los espacios en blanco deben rechazar de tajo la validación");
    }
}
