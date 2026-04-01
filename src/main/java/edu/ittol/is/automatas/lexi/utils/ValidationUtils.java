package edu.ittol.is.automatas.lexi.utils;

/**
 * Clase utilitaria que provee funciones para la sanitización y validación de datos.
 * <p>
 * Agrupa responsabilidades de verificación previa para asegurar que las entradas
 * antes de pasarlas a la capa de lógica, pertenezcan al alfabeto requerido.
 * </p>
 */
public class ValidationUtils {
    
    /**
     * Verifica que la cadena introducida esté conformada estrictamente por caracteres del alfabeto binario.
     * <p>
     * Específicamente, inspecciona en tiempo lineal si la cadena incluye elementos 
     * distintos a '0' o '1'. Adicionalmente, evalúa reglas de nulidad.
     * </p>
     *
     * @param text Cadena de caracteres a validar.
     * @return {@code true} si la cadena no es nula y contiene exclusivamente digitos '0' y '1'; {@code false} de lo contrario.
     */
    public static boolean isStrictBinary(String text) {
        if (text == null) {
            return false;
        }
        // Expresión regular de Java simple para verificar formato antes del motor O(N)
        return text.matches("[01]*");
    }
}
