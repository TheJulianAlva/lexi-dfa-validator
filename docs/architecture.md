# Documento de Diseño Arquitectónico: Proyecto "Lexi"

## 1. Información General del Proyecto

* **Nombre del Proyecto:** Lexi (Validador de Expresiones Regulares Binarias).

* **Propósito:** Software académico para la validación de cadenas binarias basadas en una Expresión Regular (ER) específica mediante la implementación de un Autómata Finito Determinista (AFD).

* **Entorno de Desarrollo:** Apache NetBeans 23.

* **Lenguaje y Plataforma:** Java (JDK 21 LTS).

* **Gestor de Dependencias:** Apache Maven.

## 2. Definición del Problema y Sustento Teórico

### 2.1. El Lenguaje Formal

El sistema debe procesar cadenas que pertenezcan al alfabeto $\Sigma = \{0, 1\}$ y que cumplan al menos una de las siguientes condiciones:

1. Terminen en el sufijo estricto `10`.

2. Terminen en `1` seguido de cualquier combinación binaria de longitud 2 (`100`, `101`, `110`, `111`).

### 2.2. La Expresión Regular (ER)

El motor de validación implementa la siguiente ER unificada:

$$
(0|1)^*(10 | 1(0|1)(0|1))
$$

## 3. Arquitectura del Sistema

El proyecto "Lexi" adopta el patrón de diseño arquitectónico **MVC (Modelo-Vista-Controlador)** adaptado para aplicaciones de escritorio (Java Swing). Esta separación de responsabilidades garantiza un código mantenible, escalable y altamente cohesivo.

### 3.1. Vista (Capa de Presentación - GUI)

Responsable única de la interacción con el usuario. No contiene lógica matemática ni de validación formal.

* **Tecnología:** Java Swing + FlatLaf (Look and Feel moderno).

### 3.2. Modelo (Capa de Lógica de Negocio - Core)

Constituye el "Cerebro" del validador. Aquí reside la implementación teórica del autómata, aislada completamente de la interfaz gráfica.

* **Tecnología Principal:** Librería `dk.brics.automaton`.

* **Componente Principal:** `AutomataEngine`.

* **Responsabilidades:**

  * **Construcción Dinámica:** Convertir la ER definida en un AFD al inicializar la aplicación.

  * **Minimización:** Ejecutar el algoritmo de minimización de estados de Brics para asegurar el procesamiento $O(n)$ más eficiente.

  * **Ejecución Rápida:** Mantener en memoria una instancia de `RunAutomaton` para procesar la cadena `String` entrante y devolver un valor booleano (`true`/`false`).

### 3.3. Controlador (Capa de Integración)

Actúa como puente entre la interfaz de usuario (`LexiUI`) y el motor de validación (`AutomataEngine`).

* **Componente Principal:** `LexiApp` (Contiene el método `main`).

* **Responsabilidades:**

  * Inicializar el Look & Feel de la aplicación (ej. FlatLaf Dark Mode).

  * Instanciar el objeto `AutomataEngine`.

  * Instanciar e inicializar la ventana `LexiUI`, inyectando la dependencia del motor de validación.

  * Manejar los eventos de teclado o clics de la vista, invocar al modelo y actualizar la vista con el resultado.

## 4. Estructura de Paquetes (Convención Maven)

La estructura física del código fuente respeta el estándar jerárquico de Maven y el "Reverse Domain Name", reflejando el patrón MVC:

```text
Lexi/
├── pom.xml                                  (Configuración y Dependencias)
└── src/
    ├── main/
    │   ├── java/
    │   │   └── edu/ittol/is/automatas/lexi/
    │   │       ├── core/
    │   │       ├── gui/
    │   │       └── utils/
    │   └── resources/                       (Iconos y assets visuales)
    └── test/                                (Opcional: Pruebas unitarias JUnit)
```

## 5. Decisiones Técnicas y Justificación

### 5.1. Incorporación de `dk.brics.automaton`

* **Justificación:** Cumpliendo con el requerimiento de "no reinventar la rueda", se integra esta librería especializada. Permite construir y minimizar el AFD directamente desde la Expresión Regular con precisión matemática, eliminando la propensión a errores humanos inherente a la construcción manual de matrices de transición mediante múltiples sentencias condicionales.

* **Versión seleccionada:** `1.12-4`.

## 6. Requerimientos del Entorno (Deployment)

Para compilar y ejecutar el proyecto desde el código fuente:

1. **JDK:** Java Development Kit versión 21 (LTS) o superior.

2. **IDE:** Apache NetBeans versión 21, 23 o superior.

3. **Conexión a Internet:** Requerida en la primera compilación ("Clean and Build") para que Maven descargue las dependencias del repositorio central hacia el directorio local `.m2`.
