# Habit Tracker

Gestor de hábitos personal construido en Java puro como proyecto de práctica.

## Conceptos cubiertos
- POO: encapsulamiento, herencia, polimorfismo, abstracción
- Excepciones checked y unchecked
- Interfaces y Generics
- Principios SOLID
- Stream API y colecciones
- java.time para manejo de fechas

## Estructura
- `model` — clases Habit, DailyHabit, WeeklyHabit
- `enums` — Category, Frequency
- `exceptions` — HabitNotFoundException, InvalidHabitException
- `repository` — interface genérica Repository<T> y su implementación
- `service` — lógica de negocio
- `ui` — menú interactivo en consola

## Fase 2

Spring Boot REST API + interfaz web — completada.

### Lo que se agregó
- API REST con Spring Boot 3.5
- Endpoints: GET, POST, DELETE
- Anotaciones: @RestController, @GetMapping, @PostMapping, @DeleteMapping, @PathVariable, @RequestBody
- Inyección de dependencias con @Service y @Repository
- Interfaz web en HTML/CSS/JS consumiendo la API
- Diseño inspirado en la identidad visual de Pragma

## Cómo correrlo

### Requisitos
- JDK 17 o superior
- Maven
- IntelliJ IDEA (recomendado)

### Pasos
1. Clonar el repositorio: `git clone https://github.com/Pipe-C/habit-tracker.git`
2. Abrir la carpeta en IntelliJ
3. Esperar a que Maven descargue las dependencias
4. Correr `Main.java`
5. Abrir el navegador en `http://localhost:8080`****
