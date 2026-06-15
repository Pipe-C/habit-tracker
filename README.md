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
