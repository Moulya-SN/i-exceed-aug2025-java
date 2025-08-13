import java.io.*;
import java.util.*;

interface Workout {
    String getWorkoutType();
    int getCaloriesBurned();
    void displayWorkout();
}

class CardioWorkout implements Workout {
    private int duration;

    public CardioWorkout(int duration) {
        this.duration = duration;
    }

    public String getWorkoutType() {
        return "Cardio";
    }

    public int getCaloriesBurned() {
        return duration * 10;
    }

    public void displayWorkout() {
        System.out.println("Cardio: " + duration + " min - " + getCaloriesBurned() + " cal");
    }
}

class StrengthWorkout implements Workout {
    private int sets, reps;

    public StrengthWorkout(int sets, int reps) {
        this.sets = sets;
        this.reps = reps;
    }

    public String getWorkoutType() {
        return "Strength";
    }

    public int getCaloriesBurned() {
        return sets * reps * 2;
    }

    public void displayWorkout() {
        System.out.println("Strength: " + sets + " sets of " + reps + " reps - " + getCaloriesBurned() + " cal");
    }
}

class YogaWorkout implements Workout {
    private int minutes;

    public YogaWorkout(int minutes) {
        this.minutes = minutes;
    }

    public String getWorkoutType() {
        return "Yoga";
    }

    public int getCaloriesBurned() {
        return minutes * 5;
    }

    public void displayWorkout() {
        System.out.println("Yoga: " + minutes + " min - " + getCaloriesBurned() + " cal");
    }
}

class HIITWorkout implements Workout {
    private int rounds;

    public HIITWorkout(int rounds) {
        this.rounds = rounds;
    }

    public String getWorkoutType() {
        return "HIIT";
    }

    public int getCaloriesBurned() {
        return rounds * 20;
    }

    public void displayWorkout() {
        System.out.println("HIIT: " + rounds + " rounds - " + getCaloriesBurned() + " cal");
    }
}

// ========== User Class (with Nutrition) ==========
class User {
    private String name;
    private List<Workout> workouts;
    private int totalCaloriesConsumed;

    public User(String name) {
        this.name = name;
        this.workouts = new ArrayList<>();
        this.totalCaloriesConsumed = 0;
    }

    public String getName() {
        return name;
    }

    public void addWorkout(Workout w) {
        workouts.add(w);
    }

    public void addCalories(int calories) {
        this.totalCaloriesConsumed += calories;
    }

    public int getCaloriesConsumed() {
        return totalCaloriesConsumed;
    }

    public int getCaloriesBurned() {
        return workouts.stream().mapToInt(Workout::getCaloriesBurned).sum();
    }

    public void showWorkouts() {
        if (workouts.isEmpty()) {
            System.out.println("No workouts logged.");
        } else {
            for (Workout w : workouts) w.displayWorkout();
        }
    }

    public String toDataString() {
        return name + "," + totalCaloriesConsumed;
    }

    public static User fromDataString(String data) {
        String[] parts = data.split(",");
        User user = new User(parts[0]);
        user.totalCaloriesConsumed = Integer.parseInt(parts[1]);
        return user;
    }
}

// ========== Repository Layer ==========
class UserRepository {
    private static final String FILE_PATH = "fitness_data.txt";

    public void saveUsers(Map<String, User> users) {
        try (PrintWriter writer = new PrintWriter(FILE_PATH)) {
            for (User user : users.values()) {
                writer.println(user.toDataString());
            }
            System.out.println("Data saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    public Map<String, User> loadUsers() {
        Map<String, User> users = new HashMap<>();
        File file = new File(FILE_PATH);

        if (!file.exists()) return users;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                User user = User.fromDataString(line);
                users.put(user.getName(), user);
            }
            System.out.println("Data loaded from file.");
        } catch (IOException e) {
            System.out.println(" Error loading data: " + e.getMessage());
        }

        return users;
    }
}

// ========== Main App ==========
public class FitnessApp {
    private static Scanner scanner = new Scanner(System.in);
    private static Map<String, User> users = new HashMap<>();
    private static UserRepository repo = new UserRepository();

    public static void main(String[] args) {
        users = repo.loadUsers();

        while (true) {
            System.out.println("\n=== FITNESS APP ===");
            System.out.println("1. Register");
            System.out.println("2. Log Workout");
            System.out.println("3. Add Calories Consumed");
            System.out.println("4. Show Stats");
            System.out.println("5. Save and Exit");
            System.out.print("Choose: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (option) {
                case 1 -> register();
                case 2 -> logWorkout();
                case 3 -> addCalories();
                case 4 -> showStats();
                case 5 -> {
                    repo.saveUsers(users);
                    System.out.println(" Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static void register() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        if (users.containsKey(name)) {
            System.out.println("User already exists.");
        } else {
            users.put(name, new User(name));
            System.out.println(" User registered.");
        }
    }

    private static void logWorkout() {
        User user = getUser();
        if (user == null) return;

        System.out.println("Select Workout Type:");
        System.out.println("1. Cardio\n2. Strength\n3. Yoga\n4. HIIT");
        int choice = scanner.nextInt();

        Workout workout = switch (choice) {
            case 1 -> {
                System.out.print("Duration (min): ");
                yield new CardioWorkout(scanner.nextInt());
            }
            case 2 -> {
                System.out.print("Sets: ");
                int sets = scanner.nextInt();
                System.out.print("Reps per set: ");
                int reps = scanner.nextInt();
                yield new StrengthWorkout(sets, reps);
            }
            case 3 -> {
                System.out.print("Minutes: ");
                yield new YogaWorkout(scanner.nextInt());
            }
            case 4 -> {
                System.out.print("Rounds: ");
                yield new HIITWorkout(scanner.nextInt());
            }
            default -> {
                System.out.println(" Invalid workout type.");
                yield null;
            }
        };

        if (workout != null) {
            user.addWorkout(workout);
            System.out.println(" " + workout.getWorkoutType() + " workout logged.");
        }
    }

    private static void addCalories() {
        User user = getUser();
        if (user == null) return;

        System.out.print("Enter calories consumed: ");
        user.addCalories(scanner.nextInt());
        System.out.println("Calories added.");
    }

    private static void showStats() {
        User user = getUser();
        if (user == null) return;

        System.out.println("\n--- Stats for " + user.getName() + " ---");
        user.showWorkouts();
        System.out.println("Calories Consumed: " + user.getCaloriesConsumed());
        System.out.println("Calories Burned: " + user.getCaloriesBurned());
    }

    private static User getUser() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        if (!users.containsKey(name)) {
            System.out.println("User not found.");
            return null;
        }
        return users.get(name);
    }
}
