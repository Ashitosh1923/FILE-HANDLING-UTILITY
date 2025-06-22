import java.io.*;
import java.util.Scanner;

public class FileHandler {

    public static void writeFile(String filename, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(content);
            System.out.println("File has been written successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void readFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            System.out.println("File content:");
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public static void appendToFile(String filename, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(content);
            System.out.println("Content has been appended.");
        } catch (IOException e) {
            System.out.println("Error appending to file: " + e.getMessage());
        }
    }

    public static void modifyFile(String filename, String oldContent, String newContent) {
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            StringBuilder fileContent = new StringBuilder();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                fileContent.append(line.replace(oldContent, newContent)).append("\n");
            }
            scanner.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(fileContent.toString());
            writer.close();

            System.out.println("File content has been modified.");
        } catch (IOException e) {
            System.out.println("Error modifying file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String filename = "example.txt";

        System.out.println("=== Welcome to Java File Handler ===");
        System.out.println("1. Write to file");
        System.out.println("2. Read file");
        System.out.println("3. Append to file");
        System.out.println("4. Modify file");
        System.out.print("Enter your choice: ");
        int choice = input.nextInt();
        input.nextLine();  

        switch (choice) {
            case 1:
                System.out.print("Enter content to write: ");
                String content = input.nextLine();
                writeFile(filename, content);
                break;
            case 2:
                readFile(filename);
                break;
            case 3:
                System.out.print("Enter content to append: ");
                String appendContent = input.nextLine();
                appendToFile(filename, appendContent);
                break;
            case 4:
                System.out.print("Enter text to find: ");
                String find = input.nextLine();
                System.out.print("Enter replacement text: ");
                String replace = input.nextLine();
                modifyFile(filename, find, replace);
                break;
            default:
                System.out.println("Invalid choice.");
        }

        input.close();
    }
}
