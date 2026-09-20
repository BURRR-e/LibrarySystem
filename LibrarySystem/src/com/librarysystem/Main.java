package com.librarysystem;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final ShelvingSystem system = new ShelvingSystem();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        initializeSampleData();

        System.out.println("\n");
        System.out.println("========================================================================");
        System.out.println("                     LIBRARY SHELF MANAGEMENT SYSTEM                    ");
        System.out.println("========================================================================");
        System.out.println();

        boolean running = true;
        while (running) {
            printMenu();
            System.out.print("Enter your choice (1-7): ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    displayShelvesWithTitlesAndDescriptions();
                    pressEnterToContinue();
                    break;
                case "2":
                    viewBookFullMetadata();
                    pressEnterToContinue();
                    break;
                case "3":
                    addNewShelf();
                    pressEnterToContinue();
                    break;
                case "4":
                    addBookToShelf();
                    pressEnterToContinue();
                    break;
                case "5":
                    removeBookFromShelf();
                    pressEnterToContinue();
                    break;
                case "6":
                    transferBook();
                    pressEnterToContinue();
                    break;
                case "7":
                    running = false;
                    System.out.println("\nExiting the Library Shelf Management System. Goodbye!\n");
                    break;
                default:
                    System.out.println("\n[Error] Invalid choice! Please select a number between 1 and 7.");
                    pressEnterToContinue();
            }
        }
    }

    private static void printMenu() {
        System.out.println("------------------------------------------------------------------------");
        System.out.println("                               MAIN MENU                                ");
        System.out.println("------------------------------------------------------------------------");
        System.out.println("  1. Display Shelves (Show Book Titles & Descriptions)");
        System.out.println("  2. Select & View Full Book Metadata (by Shelf Selection)");
        System.out.println("  3. Add a New Shelf");
        System.out.println("  4. Add a Book to a Shelf (with full metadata)");
        System.out.println("  5. Remove a Book from a Shelf");
        System.out.println("  6. Transfer a Book between Shelves");
        System.out.println("  7. Exit");
        System.out.println("------------------------------------------------------------------------");
    }

    private static void pressEnterToContinue() {
        System.out.println("\n------------------------------------------------------------------------");
        System.out.print("Press ENTER to return to the Main Menu...");
        scanner.nextLine();
        System.out.println("\n\n");
    }

    private static void initializeSampleData() {
        // Shelf A: Science Fiction
        Shelf sciFiShelf = new Shelf("SF-01", "Science Fiction", 4);
        sciFiShelf.addBook(new Book(
            "Dune",
            "A mythic and emotionally charged hero's journey in a harsh desert world managing 'spice'.",
            "Frank Herbert",
            "9780441172719",
            "Chilton Books",
            1965,
            "Science Fiction",
            604,
            "English"
        ));
        sciFiShelf.addBook(new Book(
            "Neuromancer",
            "The quintessential cyberpunk novel about a washed-up computer hacker hired for a final job.",
            "William Gibson",
            "9780441569595",
            "Ace Books",
            1984,
            "Cyberpunk / Sci-Fi",
            271,
            "English"
        ));
        sciFiShelf.addBook(new Book(
            "The Left Hand of Darkness",
            "A groundbreaking sci-fi novel exploring themes of gender, politics, and technology on an icy planet.",
            "Ursula K. Le Guin",
            "9780441478125",
            "Ace Books",
            1969,
            "Social Sci-Fi",
            304,
            "English"
        ));

        // Shelf B: Philosophy & History
        Shelf philHistShelf = new Shelf("PH-02", "Philosophy & History", 3);
        philHistShelf.addBook(new Book(
            "Sapiens: A Brief History of Humankind",
            "An investigation into how Homo sapiens came to dominate the Earth, spanning evolutionary history.",
            "Yuval Noah Harari",
            "9780062316097",
            "Harper",
            2014,
            "History / Anthropology",
            443,
            "English"
        ));
        philHistShelf.addBook(new Book(
            "Meditations",
            "A series of personal writings by Marcus Aurelius, Roman Emperor, offering stoic philosophy guidance.",
            "Marcus Aurelius",
            "9780812968255",
            "Modern Library",
            180,
            "Stoic Philosophy",
            256,
            "English (Translated)"
        ));

        // Shelf C: Classic Literature
        Shelf classicsShelf = new Shelf("CL-03", "Classic Literature", 3);
        classicsShelf.addBook(new Book(
            "The Great Gatsby",
            "A story of obsession, wealth, and social prestige in the roaring twenties of Long Island.",
            "F. Scott Fitzgerald",
            "9780743273565",
            "Scribner",
            1925,
            "Classic Fiction",
            180,
            "English"
        ));
        classicsShelf.addBook(new Book(
            "To Kill a Mockingbird",
            "A deeply moving story about racism, justice, and childhood innocence in the Deep South.",
            "Harper Lee",
            "9780446310789",
            "J. B. Lippincott & Co.",
            1960,
            "Classic Fiction",
            281,
            "English"
        ));

        system.addShelf(sciFiShelf);
        system.addShelf(philHistShelf);
        system.addShelf(classicsShelf);
    }

    private static void displayShelvesWithTitlesAndDescriptions() {
        List<Shelf> shelves = system.getShelves();
        if (shelves.isEmpty()) {
            System.out.println("\n[Info] No shelves currently exist in the system.");
            return;
        }

        System.out.println("\n========================================================================");
        System.out.println("                      CURRENT SHELVES AND BOOK OVERVIEWS                ");
        System.out.println("========================================================================");
        for (Shelf shelf : shelves) {
            System.out.println("\nShelf: " + shelf.getShelfId() + " (" + shelf.getCategory() + ")");
            System.out.println("Capacity: " + shelf.getBookCount() + "/" + shelf.getCapacity());
            System.out.println("------------------------------------------------------------------------");
            List<Book> books = shelf.getBooks();
            if (books.isEmpty()) {
                System.out.println("  * This shelf is currently empty.");
            } else {
                for (int i = 0; i < books.size(); i++) {
                    Book b = books.get(i);
                    System.out.println("  [" + (i + 1) + "] Title:       " + b.getTitle());
                    System.out.println("      Description: " + b.getDescription());
                    System.out.println();
                }
            }
        }
        System.out.println("========================================================================");
    }

    private static void viewBookFullMetadata() {
        System.out.println("\n--- CHOOSE A BOOK TO VIEW FULL METADATA ---");
        List<Shelf> shelves = system.getShelves();
        if (shelves.isEmpty()) {
            System.out.println("[Info] No shelves exist to select from.");
            return;
        }

        System.out.println("Available Shelves:");
        for (int i = 0; i < shelves.size(); i++) {
            Shelf s = shelves.get(i);
            System.out.println("  " + (i + 1) + ". Shelf " + s.getShelfId() + " [" + s.getCategory() + "]");
        }

        System.out.print("\nSelect a Shelf by number (1-" + shelves.size() + ") or enter Shelf ID: ");
        String shelfInput = scanner.nextLine().trim();

        Shelf selectedShelf = null;
        try {
            int shelfIdx = Integer.parseInt(shelfInput) - 1;
            if (shelfIdx >= 0 && shelfIdx < shelves.size()) {
                selectedShelf = shelves.get(shelfIdx);
            }
        } catch (NumberFormatException ignored) {}

        if (selectedShelf == null) {
            selectedShelf = system.findShelfById(shelfInput);
        }

        if (selectedShelf == null) {
            System.out.println("[Error] Invalid shelf selection!");
            return;
        }

        List<Book> books = selectedShelf.getBooks();
        if (books.isEmpty()) {
            System.out.println("[Info] Shelf '" + selectedShelf.getShelfId() + "' is empty!");
            return;
        }

        System.out.println("\nBooks on Shelf " + selectedShelf.getShelfId() + ":");
        for (int i = 0; i < books.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + books.get(i).getTitle());
        }

        System.out.print("\nSelect a book number (1-" + books.size() + ") to view full metadata: ");
        String bookInput = scanner.nextLine().trim();

        Book selectedBook = null;
        try {
            int bookIdx = Integer.parseInt(bookInput) - 1;
            if (bookIdx >= 0 && bookIdx < books.size()) {
                selectedBook = books.get(bookIdx);
            }
        } catch (NumberFormatException ignored) {}

        if (selectedBook == null) {
            selectedBook = selectedShelf.findBook(bookInput);
        }

        if (selectedBook == null) {
            System.out.println("[Error] Invalid book selection!");
            return;
        }

        System.out.println("\n" + selectedBook.getMetadataString());
    }

    private static void addNewShelf() {
        System.out.println("\n--- ADD A NEW SHELF ---");
        System.out.print("Enter Shelf ID (e.g. SH-04): ");
        String shelfId = scanner.nextLine().trim();
        if (shelfId.isEmpty()) {
            System.out.println("[Error] Shelf ID cannot be empty!");
            return;
        }

        if (system.findShelfById(shelfId) != null) {
            System.out.println("[Error] A shelf with ID '" + shelfId + "' already exists!");
            return;
        }

        System.out.print("Enter Shelf Category/Genre: ");
        String category = scanner.nextLine().trim();
        if (category.isEmpty()) {
            category = "General";
        }

        System.out.print("Enter Shelf Capacity (integer): ");
        int capacity;
        try {
            capacity = Integer.parseInt(scanner.nextLine().trim());
            if (capacity <= 0) {
                System.out.println("[Error] Capacity must be greater than 0!");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("[Error] Invalid number format for capacity!");
            return;
        }

        Shelf newShelf = new Shelf(shelfId, category, capacity);
        if (system.addShelf(newShelf)) {
            System.out.println("[Success] Shelf '" + shelfId + "' created successfully!");
        } else {
            System.out.println("[Error] Failed to create shelf.");
        }
    }

    private static void addBookToShelf() {
        System.out.println("\n--- ADD A BOOK TO A SHELF ---");
        List<Shelf> shelves = system.getShelves();
        if (shelves.isEmpty()) {
            System.out.println("[Error] No shelves exist! Create a shelf first.");
            return;
        }

        System.out.println("Available Shelves:");
        for (Shelf s : shelves) {
            System.out.println("  - " + s.getShelfId() + " (" + s.getCategory() + ") Capacity: " + s.getBookCount() + "/" + s.getCapacity());
        }

        System.out.print("\nEnter Shelf ID to add book to: ");
        String shelfId = scanner.nextLine().trim();
        Shelf shelf = system.findShelfById(shelfId);

        if (shelf == null) {
            System.out.println("[Error] Shelf '" + shelfId + "' not found!");
            return;
        }

        if (shelf.isFull()) {
            System.out.println("[Error] Shelf '" + shelfId + "' is full! Cannot add more books.");
            return;
        }

        System.out.print("Enter Book Title: ");
        String title = scanner.nextLine().trim();
        if (title.isEmpty()) {
            System.out.println("[Error] Title cannot be empty!");
            return;
        }

        System.out.print("Enter Book Description (Synopsis): ");
        String description = scanner.nextLine().trim();
        if (description.isEmpty()) {
            description = "No description provided.";
        }

        System.out.print("Enter Author: ");
        String author = scanner.nextLine().trim();
        if (author.isEmpty()) {
            author = "Unknown";
        }

        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine().trim();
        if (isbn.isEmpty()) {
            isbn = "N/A";
        }

        System.out.print("Enter Publisher: ");
        String publisher = scanner.nextLine().trim();
        if (publisher.isEmpty()) {
            publisher = "Unknown";
        }

        System.out.print("Enter Publish Year (integer): ");
        int publishYear = 2026;
        try {
            String pyStr = scanner.nextLine().trim();
            if (!pyStr.isEmpty()) {
                publishYear = Integer.parseInt(pyStr);
            }
        } catch (NumberFormatException e) {
            System.out.println("[Warning] Invalid year. Defaulting to 2026.");
        }

        System.out.print("Enter Genre: ");
        String genre = scanner.nextLine().trim();
        if (genre.isEmpty()) {
            genre = shelf.getCategory();
        }

        System.out.print("Enter Page Count (integer): ");
        int pages = 100;
        try {
            String pgStr = scanner.nextLine().trim();
            if (!pgStr.isEmpty()) {
                pages = Integer.parseInt(pgStr);
            }
        } catch (NumberFormatException e) {
            System.out.println("[Warning] Invalid page count. Defaulting to 100.");
        }

        System.out.print("Enter Language: ");
        String language = scanner.nextLine().trim();
        if (language.isEmpty()) {
            language = "English";
        }

        Book book = new Book(title, description, author, isbn, publisher, publishYear, genre, pages, language);
        if (shelf.addBook(book)) {
            System.out.println("[Success] Book '" + title + "' added successfully to shelf '" + shelfId + "'!");
        } else {
            System.out.println("[Error] Failed to add book to shelf.");
        }
    }

    private static void removeBookFromShelf() {
        System.out.println("\n--- REMOVE A BOOK FROM A SHELF ---");
        displayShelvesWithTitlesAndDescriptions();

        System.out.print("Enter Shelf ID: ");
        String shelfId = scanner.nextLine().trim();
        Shelf shelf = system.findShelfById(shelfId);

        if (shelf == null) {
            System.out.println("[Error] Shelf '" + shelfId + "' not found!");
            return;
        }

        System.out.print("Enter Book Title or ISBN to remove: ");
        String query = scanner.nextLine().trim();

        Book removedBook = system.removeBookFromShelf(shelfId, query);
        if (removedBook != null) {
            System.out.println("[Success] Removed book '" + removedBook.getTitle() + "' from shelf '" + shelfId + "'!");
        } else {
            System.out.println("[Error] Book not found on shelf '" + shelfId + "'.");
        }
    }

    private static void transferBook() {
        System.out.println("\n--- TRANSFER A BOOK BETWEEN SHELVES ---");
        displayShelvesWithTitlesAndDescriptions();

        System.out.print("Enter Source (From) Shelf ID: ");
        String fromShelfId = scanner.nextLine().trim();
        Shelf fromShelf = system.findShelfById(fromShelfId);
        if (fromShelf == null) {
            System.out.println("[Error] Source shelf '" + fromShelfId + "' not found!");
            return;
        }

        System.out.print("Enter Destination (To) Shelf ID: ");
        String toShelfId = scanner.nextLine().trim();
        Shelf toShelf = system.findShelfById(toShelfId);
        if (toShelf == null) {
            System.out.println("[Error] Destination shelf '" + toShelfId + "' not found!");
            return;
        }

        if (toShelf.isFull()) {
            System.out.println("[Error] Destination shelf '" + toShelfId + "' is already full!");
            return;
        }

        System.out.print("Enter Book Title or ISBN to transfer: ");
        String query = scanner.nextLine().trim();

        if (system.transferBook(fromShelfId, toShelfId, query)) {
            System.out.println("[Success] Book '" + query + "' successfully transferred to '" + toShelfId + "'!");
        } else {
            System.out.println("[Error] Failed to transfer book. Verify book existence and capacity.");
        }
    }
}
