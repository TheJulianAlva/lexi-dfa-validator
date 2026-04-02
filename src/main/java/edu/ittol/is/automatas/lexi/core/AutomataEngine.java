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
     * Autómata Finito base subyacente. Se preserva su referencia intacta para habilitar 
     * operaciones estructurales o algebraicas avanzadas (ej. inferencia de ejemplos cortos, graficado DOT).
     */
    private final Automaton rawAutomaton;

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
        
        // Se preserva el DFA crudo para manipulación matemática
        this.rawAutomaton = automaton;

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

    /**
     * Extrae y devuelve una muestra generada algorítmicamente del lenguaje actual.
     * <p>
     * Sumamente útil para asistir al usuario final demostrándole visualmente
     * qué forma tiene una cadena que es estrictament válida para las reglas actuales del AFD.
     * </p>
     * 
     * @return La secuencia binaria válida más corta e identificable capaz de satisfacer al AFD; {@code null} si el lenguaje fuera inherentemente vacío.
     */
    public String getExample() {
        return this.rawAutomaton.getShortestExample(true);
    }
    
    /**
     * @return String identificando a la constante de Expresión Regular que domina el lenguaje actual.
     */
    public String getER() {
        return ER;
    }
}
