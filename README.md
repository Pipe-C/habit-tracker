# 🌱 Habit Tracker

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
Spring Boot REST API + interfaz web (próximamente)
