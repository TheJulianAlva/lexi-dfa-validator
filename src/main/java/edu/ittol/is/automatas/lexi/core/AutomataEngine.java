package edu.ittol.is.automatas.lexi.core;

import dk.brics.automaton.Automaton;
import dk.brics.automaton.RegExp;
import dk.brics.automaton.RunAutomaton;

/**
 * Componente principal que encapsula el motor de autómata de la lógica de negocio (Capa Modelo).
 * <p>
 * Su propósito central es abstraer la complejidad teórica y algorítmica de la librería 
 * {@code dk.brics.automaton}, permitiendo a las capas superiores (Controladores y Vistas)
 * evaluar cadenas binarias mediante el paso de mensajes asilados.
 * </p>
 */
public class AutomataEngine {
    
    /**
     * Expresión regular matemáticamente formulada que describe el lenguaje aceptado:
     * cadenas del alfabeto {0,1} que terminen en "10" o en "1" seguido de cualquier de
     * sus derivadas booleanas de dos posiciones. 
     */
    private static final String ER = "(0|1)*(10|1(0|1)(0|1))";
    
    /**
     * Máquina inferida de estado precargado que garantiza la capacidad evaluatoria final en un 
     * tiempo dependiente enteramente a O(length(input)) para rendimiento escalable en tiempo de ejecución.
     */
    private final RunAutomaton runAutomaton;

    /**
     * Inicializa el compilador del autómata, traduciendo iterativamente la expresión regular.
     * <p>
     * Posteriormente la estructura generada se minimiza a su versión ideal (Autómata Finito 
     * Determinista Mínimo), transformándola entonces al contenedor principal {@link RunAutomaton} 
     * enfocado al match-execution repetitivo.
     * </p>
     */
    public AutomataEngine() {
        RegExp expr = new RegExp(ER);
        Automaton automaton = expr.toAutomaton();
        // Aplicar el proceso teórico de minimización de estados y transiciones
        automaton.minimize();
        // 4. Crear el Wrapper de Ejecución (O(n)). 'true' indica construir la tabla precomputada para mayor eficiencia.
        this.runAutomaton = new RunAutomaton(automaton, true);
    }

    /**
     * Ejecuta el Autómata Finito sobre un arreglo de entrada para verificar su pertinencia
     * en el lenguaje formal provisto.
     * <p>
     * Se recomienda encarecidamente la pre-evaluación
     * binaria estricta de la cadena solicitada usando {@link edu.ittol.is.automatas.lexi.utils.ValidationUtils#isStrictBinary(String)}.
     * </p>
     * 
     * @param input Secuencia de entrada (Cadena) en formato textual a ser corrida bajo el autómata actual.
     * @return {@code true} si la secuencia transcurre enteramente dictando detención en estado pasante o de aceptación; {@code false} de fallar con nulo o denegar el caso.
     */
    public boolean isValid(String input) {
        if (input == null) {
            return false;
        }
        return this.runAutomaton.run(input);
    }
}
