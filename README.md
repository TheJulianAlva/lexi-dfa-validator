# Lexi Validator - Validador de Expresiones Regulares Binarias

[![Java 21](https://img.shields.io/badge/Java-21-blue.svg)](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)
[![Maven](https://img.shields.io/badge/Maven-Build-orange.svg)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)
[![GitHub release](https://img.shields.io/github/v/release/TheJulianAlva/lexi-dfa-validator.svg)](https://github.com/TheJulianAlva/lexi-dfa-validator/releases)

**Lexi Validator** es un proyecto informático desarrollado con un enfoque académico. Su propósito principal es analizar y validar cadenas que pertenecen de forma estricta a un alfabeto binario (`Σ = {0, 1}`) mediante la evaluación formal de Expresiones Regulares apoyadas, internamente, en Autómatas Finitos Deterministas (DFA) y No Deterministas (NFA).

---

## Contexto Académico

Este software documenta de manera práctica los conceptos teóricos clásicos sobre analizadores léxicos y procesadores de lenguajes formales, materializándolos mediante una aplicación modular que engloba un núcleo comprobable y algoritmos de validación abstractos implementados con orientación a objetos.

---

## Características Principales

*   **Validación Semántica Estricta:** Comprueba con fiabilidad cadenas pertenecientes a un alfabeto binario. 
*   **Motor basado en Estados:** Implementación 'core' cimentada en [dk.brics.automaton](https://www.brics.dk/automaton/) para asegurar un procesamiento constante y asertivo de la RegEx.
*   **Interfaz Gráfica (Swing / FlatLaf):** Entorno visual empleando componentes de FlatLaf.
*   **Construcción Unificada:** Listo para ambientes de integración utilizando directivas de empaquetado hacia un único binario ejecutable.

---

## Stack Tecnológico

*   **Java 21:** Lenguaje de desarrollo principal en Backend y UI.
*   **Apache Maven:** Gestión del ciclo de construcción del software y resolución de dependencias.
*   **dk.brics.automaton:** Construcción algorítmica y teórica.
*   **FlatLaf:** Motor para apariencia multiplataforma.

---

## Guía de Instalación y Uso

Para ejecutar el programa fácilmente desde la distribución lista para producción:

1. Ve a la pestaña **[Releases](https://github.com/TheJulianAlva/lexi-dfa-validator/releases)** de este repositorio.
2. Descarga la versión más reciente alojada como ejecutable.
3. El programa puede ser iniciado desde cualquier terminal con Java instalado:
   ```bash
   java -jar lexi-validator-1.0.0.jar
   ```
   *(También es compatible dando doble clic sobre el archivo si tu SO está configurado con tu JRE/JDK).*

---

## Estructura del Proyecto

A continuación, una vista general simplificada del encapsulamiento del código:

```text
lexi-validator/
├── src/
│   ├── main/
│   │   └── java/edu/ittol/is/automatas/lexi/
│   │       ├── core/         # Núcleo de automatas y utilidades
│   │       ├── gui/          # Vistas Swing
│   │       └── LexiApp.java  # Clase Bootstrap / Punto de arranque principal
│   └── test/
│       └── java/ 
├── docs/     
├── pom.xml   
└── README.md 
```

---

## 📝 Licencias y Contribución

Este proyecto ha sido desarrollado bajo un modelo de aprendizaje y por el interés de divulgar en materia de lenguajes de programación. Toda sugerencia, PR (Pull Request) o reporte (Issue) está abierto y será muy valioso de revisar.

> Creado para la materia de *Lenguajes y Autómatas* | Semestre 2026-A
