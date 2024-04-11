package puzzle;

import java.util.Scanner;

public class Prompt {

    private static Scanner scanner;
    private static Shape shape;

    public static void start() {
        scanner = new Scanner(System.in);
        shape = new Shape();

        System.out.println("Welcome to the GIC geometry puzzle app");
        System.out.println("[1] Create a custom shape");
        System.out.println("[2] Generate a random shape");

        while(scanner.hasNextLine()) {
            String prompt = scanner.nextLine();
            if ("1".equals(prompt)) {
                getPoints();
                System.out.println("Your finalized shape is");
                shape.print();
                getTestPoints();
                break;
            } else if ("2".equals(prompt)) {
                try {
                    shape.generateRandom();
                } catch (Exception e) {
                    System.out.println("Failed to generated a convex shape!!!");
                    break;
                }
                System.out.println("Your random shape is");
                shape.print();
                getTestPoints();
                break;
            }
        }

        scanner.close();
        shape.clear();

        System.out.println("Thank you for playing the GIC geometry puzzle app");
        System.out.println("Have a nice day!");
    }

    private static Point parsePoint(String input) {
        String[] parts = input.split(" ");

        int x = Integer.parseInt(parts[0]);
        int y = Integer.parseInt(parts[1]);

        return new Point(x, y);
    }

    private static void getPoints() {
        int n = 1;
        while (true) {
            Point point = null;
            try {
                if (n >= 4) {
                    System.out.printf(
                        "Please enter # to finalize your shape or enter coordinates %d in x y format%n", n
                    );
                } else {
                    System.out.printf("Please enter coordinates %d in x y format%n", n);
                }

                String input = scanner.nextLine();

                if ("#".equals(input)) return;

                point = parsePoint(input);

                if (shape.addPoint(point)) {
                    if (n >= 3) {
                        System.out.println("Your current shape is valid and is complete");
                    } else {
                        System.out.println("Your current shape is incomplete");
                    }
                    shape.print();
                    n++;
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                if (point != null) {
                    System.out.printf("New coordinates(%d,%d) is invalid!!!", point.x(), point.y());
                } else {
                    System.out.print("Input is invalid!!!");
                }

                System.out.println("\nNot adding new coordinates to the current shape.\n");

                if (shape.isValidConvex()) {
                    System.out.println("Your current shape is valid and is complete");
                } else {
                    System.out.println("Your current shape is incomplete");
                }
                shape.print();
            }
        }
    }

    private static void getTestPoints() {
        System.out.println("\nPlease key in test coordinates in x y format or enter # to quit the game");

        while (true) {
            Point point = null;

            try {
                String input = scanner.nextLine();

                if ("#".equals(input)) {
                    return;
                }

                point = parsePoint(input);

                if (shape.isWithinShape(point)) {
                    System.out.println("Your finalized shape is");
                    shape.print();
                    System.out.printf("\nCoordinates (%d %d) is within your finalized shape", point.x(), point.y());
                    System.out.println("\nPlease key in test coordinates in x y format or enter # to quit the game");
                } else {
                    System.out.println("Your finalized shape is");
                    shape.print();
                    System.out.printf("\nCoordinates (%d %d) is outside of your finalized shape", point.x(), point.y());
                    System.out.println("\nPlease key in test coordinates in x y format or enter # to quit the game");
                }
            } catch (Exception e) {
                if (point != null) {
                    System.out.printf("Test coordinates(%d,%d) is invalid!!!\n\n", point.x(), point.y());
                } else {
                    System.out.println("Test input is invalid!!!\n");
                }

                System.out.println("Your finalized shape is");
                shape.print();
                System.out.println("\nPlease key in test coordinates in x y format or enter # to quit the game");
            }
        }
    }

}
