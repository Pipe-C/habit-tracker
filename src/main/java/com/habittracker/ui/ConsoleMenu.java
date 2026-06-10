package com.habittracker.ui;

import com.habittracker.enums.Category;
import com.habittracker.exceptions.HabitNotFoundException;
import com.habittracker.exceptions.InvalidHabitException;
import com.habittracker.model.Habit;
import com.habittracker.repository.HabitRepository;
import com.habittracker.service.HabitService;

import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.List;

public class ConsoleMenu {

    private final HabitService service;
    private final Scanner scanner;

    public ConsoleMenu() {
        HabitRepository repository = new HabitRepository();
        this.service = new HabitService(repository);
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        printWelcome();

        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = readInt("👉 Elige una opción: ");

            switch (choice) {
                case 1 -> showHabits();
                case 2 -> createHabitMenu();
                case 3 -> markCompletedMenu();
                case 4 -> service.showStats();
                case 5 -> deleteHabitMenu();
                case 0 -> {
                    System.out.println("\n 👋 ¡Hasta luego! Sigue con tus hábitos. 💪\n");
                    running = false;
                }
                default -> System.out.println("⚠️ Opción inválida. Intenta de nuevo.");
            }

            if (running) {
                System.out.println("\nPresiona Enter para continuar...");
                scanner.nextLine();
            }
        }
        scanner.close();
    }

    private int readInt(String prompt) {
        System.out.println(prompt);
        try {
            String input = scanner.nextLine().trim();
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private String readString(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine().trim();
    }

    private void printWelcome() {
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║        🌱 HABIT TRACKER CLI          ║");
        System.out.println("║    Tu gestor de hábitos personal     ║");
        System.out.println("╚══════════════════════════════════════╝\n");
    }

    private void printMainMenu() {
        System.out.println("\n┌─────────────────────────────────┐");
        System.out.println("│           MENÚ PRINCIPAL         │");
        System.out.println("├─────────────────────────────────┤");
        System.out.println("│  1. Ver mis hábitos              │");
        System.out.println("│  2. Crear hábito                 │");
        System.out.println("│  3. Marcar como completado       │");
        System.out.println("│  4. Ver estadísticas             │");
        System.out.println("│  5. Eliminar hábito              │");
        System.out.println("│  0. Salir                        │");
        System.out.println("└─────────────────────────────────┘");
    }

    private void deleteHabitMenu() {
        showHabits();
        List<Habit> habits = service.getAllActiveHabits();

        if (habits.isEmpty()) return;

        int index = readInt("\n Número del hábito a eliminar: ");
        String confirm = readString(" ¿Estás seguro? (s/n): ");

        if ("s".equalsIgnoreCase(confirm)) {
            try {
                service.deleteHabit(index);
            } catch (HabitNotFoundException e) {
                System.out.println("❌ " + e.getMessage());
            }
        } else {
            System.out.println(" Operación completada.");
        }
    }

    private void markCompletedMenu() {
        showHabits();
        List<Habit> habits = service.getAllActiveHabits();

        if (habits.isEmpty()) return;

        int index = readInt("\n Número del hábito a marcar: ");

        try {
            service.markHabitCompleted(index);
        } catch (HabitNotFoundException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    private void createHabitMenu() {
        System.out.println("\n── ➕ CREAR HÁBITO ─────────────────────────");

        System.out.println("  Tipo de hábito:");
        System.out.println("  1. Diario:");
        System.out.println("  2. Semanal:");
        int type = readInt(" Elige: ");

        String name = readString(" Nombre del hábito: ");
        String description = readString(" Descripción (opcional, Enter para omitir): ");

        System.out.println(" Categoría:");
        Category[] categories = Category.values();
        for (int i = 0; i < categories.length; i++) {
            System.out.println(" " + (i + 1) + ". " + categories[i].name());
        }
        int categoryChoice = readInt(" Elige ");
        Category category = Category.fromIndex(categoryChoice);

        try {
            if (type == 1) {
                service.createDailyHabit(name, description, category);
                System.out.println("✅ ¡Hábito diario creado!");
            } else if (type == 2) {
                int times = readInt(" ¿Cuántas veces por semana (1-7): ");
                service.createWeeklyHabit(name, description, category, times);
                System.out.println("✅ ¡Hábito semanal creado!");
            } else {
                System.out.println("⚠️ Tipo inválido.");
            }
        } catch (InvalidHabitException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private void showHabits() {
        List<Habit> habits = service.getAllActiveHabits();
        System.out.println("\n── 📋 MIS HÁBITOS ─────────────────────────");

        if (habits.isEmpty()) {
            System.out.println("  No tienes hábitos activos. ¡Crea uno!");
            return;
        }

        for (int i = 0; i < habits.size(); i++) {
            System.out.println("  " + (i + 1) + ". ");
            habits.get(i).display();
        }
    }
}
