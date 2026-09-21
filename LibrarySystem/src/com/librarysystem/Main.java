package com.librarysystem;

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
            String choice = prompt("Enter your choice (1-7): ");

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

    private static String prompt(String message) {
        System.out.println(message);
        System.out.flush();
        return scanner.nextLine().trim();
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
        prompt("Press ENTER to return to the Main Menu...");
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
        Shelf[] shelves = system.getShelves();
        if (shelves.length == 0) {
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
            Book[] books = shelf.getBooks();
            int bookCount = shelf.getBookCount();
            if (bookCount == 0) {
                System.out.println("  * This shelf is currently empty.");
            } else {
                for (int i = 0; i < bookCount; i++) {
                    Book b = books[i];
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
        Shelf[] shelves = system.getShelves();
        if (shelves.length == 0) {
            System.out.println("[Info] No shelves exist to select from.");
            return;
        }

        System.out.println("Available Shelves:");
        for (int i = 0; i < shelves.length; i++) {
            Shelf s = shelves[i];
            System.out.println("  " + (i + 1) + ". Shelf " + s.getShelfId() + " [" + s.getCategory() + "]");
        }

        String shelfInput = prompt("\nSelect a Shelf by number (1-" + shelves.length + ") or enter Shelf ID: ");

        Shelf selectedShelf = null;
        try {
            int shelfIdx = Integer.parseInt(shelfInput) - 1;
            if (shelfIdx >= 0 && shelfIdx < shelves.length) {
                selectedShelf = shelves[shelfIdx];
            }
        } catch (NumberFormatException ignored) {}

        if (selectedShelf == null) {
            selectedShelf = system.findShelfById(shelfInput);
        }

        if (selectedShelf == null) {
            System.out.println("[Error] Invalid shelf selection!");
            return;
        }

        Book[] books = selectedShelf.getBooks();
        int bookCount = selectedShelf.getBookCount();
        if (bookCount == 0) {
            System.out.println("[Info] Shelf '" + selectedShelf.getShelfId() + "' is empty!");
            return;
        }

        System.out.println("\nBooks on Shelf " + selectedShelf.getShelfId() + ":");
        for (int i = 0; i < bookCount; i++) {
            System.out.println("  " + (i + 1) + ". " + books[i].getTitle());
        }

        String bookInput = prompt("\nSelect a book number (1-" + bookCount + ") to view full metadata: ");

        Book selectedBook = null;
        try {
            int bookIdx = Integer.parseInt(bookInput) - 1;
            if (bookIdx >= 0 && bookIdx < bookCount) {
                selectedBook = books[bookIdx];
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
        String shelfId = prompt("Enter Shelf ID (e.g. SH-04): ");
        if (shelfId.isEmpty()) {
            System.out.println("[Error] Shelf ID cannot be empty!");
            return;
        }

        if (system.findShelfById(shelfId) != null) {
            System.out.println("[Error] A shelf with ID '" + shelfId + "' already exists!");
            return;
        }

        String category = prompt("Enter Shelf Category/Genre: ");
        if (category.isEmpty()) {
            category = "General";
        }

        String capacityStr = prompt("Enter Shelf Capacity (integer): ");
        int capacity;
        try {
            capacity = Integer.parseInt(capacityStr);
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
        Shelf[] shelves = system.getShelves();
        if (shelves.length == 0) {
            System.out.println("[Error] No shelves exist! Create a shelf first.");
            return;
        }

        System.out.println("Available Shelves:");
        for (Shelf s : shelves) {
            System.out.println("  - " + s.getShelfId() + " (" + s.getCategory() + ") Capacity: " + s.getBookCount() + "/" + s.getCapacity());
        }

        String shelfId = prompt("\nEnter Shelf ID to add book to: ");
        Shelf shelf = system.findShelfById(shelfId);

        if (shelf == null) {
            System.out.println("[Error] Shelf '" + shelfId + "' not found!");
            return;
        }

        if (shelf.isFull()) {
            System.out.println("[Error] Shelf '" + shelfId + "' is full! Cannot add more books.");
            return;
        }

        String title = prompt("Enter Book Title: ");
        if (title.isEmpty()) {
            System.out.println("[Error] Title cannot be empty!");
            return;
        }

        String description = prompt("Enter Book Description (Synopsis): ");
        if (description.isEmpty()) {
            description = "No description provided.";
        }

        String author = prompt("Enter Author: ");
        if (author.isEmpty()) {
            author = "Unknown";
        }

        String isbn = prompt("Enter ISBN: ");
        if (isbn.isEmpty()) {
            isbn = "N/A";
        }

        String publisher = prompt("Enter Publisher: ");
        if (publisher.isEmpty()) {
            publisher = "Unknown";
        }

        int publishYear = 2026;
        try {
            String pyStr = prompt("Enter Publish Year (integer): ");
            if (!pyStr.isEmpty()) {
                publishYear = Integer.parseInt(pyStr);
            }
        } catch (NumberFormatException e) {
            System.out.println("[Warning] Invalid year. Defaulting to 2026.");
        }

        String genre = prompt("Enter Genre: ");
        if (genre.isEmpty()) {
            genre = shelf.getCategory();
        }

        int pages = 100;
        try {
            String pgStr = prompt("Enter Page Count (integer): ");
            if (!pgStr.isEmpty()) {
                pages = Integer.parseInt(pgStr);
            }
        } catch (NumberFormatException e) {
            System.out.println("[Warning] Invalid page count. Defaulting to 100.");
        }

        String language = prompt("Enter Language: ");
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

        String shelfId = prompt("Enter Shelf ID: ");
        Shelf shelf = system.findShelfById(shelfId);

        if (shelf == null) {
            System.out.println("[Error] Shelf '" + shelfId + "' not found!");
            return;
        }

        String query = prompt("Enter Book Title or ISBN to remove: ");

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

        String fromShelfId = prompt("Enter Source (From) Shelf ID: ");
        Shelf fromShelf = system.findShelfById(fromShelfId);
        if (fromShelf == null) {
            System.out.println("[Error] Source shelf '" + fromShelfId + "' not found!");
            return;
        }

        String toShelfId = prompt("Enter Destination (To) Shelf ID: ");
        Shelf toShelf = system.findShelfById(toShelfId);
        if (toShelf == null) {
            System.out.println("[Error] Destination shelf '" + toShelfId + "' not found!");
            return;
        }

        if (toShelf.isFull()) {
            System.out.println("[Error] Destination shelf '" + toShelfId + "' is already full!");
            return;
        }

        String query = prompt("Enter Book Title or ISBN to transfer: ");

        if (system.transferBook(fromShelfId, toShelfId, query)) {
            System.out.println("[Success] Book '" + query + "' successfully transferred to '" + toShelfId + "'!");
        } else {
            System.out.println("[Error] Failed to transfer book. Verify book existence and capacity.");
        }
    }
}
