package com.librarysystem;

public class Shelf {
    private String shelfId;
    private String category;
    private int capacity;
    private Book[] books;
    private int bookCount;

    public Shelf(String shelfId, String category, int capacity) {
        this.shelfId = shelfId;
        this.category = category;
        this.capacity = capacity;
        this.books = new Book[capacity];
        this.bookCount = 0;
    }

    public String getShelfId() {
        return shelfId;
    }

    public String getCategory() {
        return category;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getBookCount() {
        return bookCount;
    }

    public Book[] getBooks() {
        return books;
    }

    public boolean isFull() {
        return bookCount >= capacity;
    }

    public boolean addBook(Book book) {
        if (isFull()) {
            return false;
        }
        books[bookCount] = book;
        bookCount++;
        return true;
    }

    public Book removeBookByIsbn(String isbn) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getIsbn().equalsIgnoreCase(isbn)) {
                Book removed = books[i];
                // Shift elements to the left to maintain order
                for (int j = i; j < bookCount - 1; j++) {
                    books[j] = books[j + 1];
                }
                books[bookCount - 1] = null;
                bookCount--;
                return removed;
            }
        }
        return null;
    }

    public Book removeBookByTitle(String title) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getTitle().equalsIgnoreCase(title)) {
                Book removed = books[i];
                // Shift elements to the left to maintain order
                for (int j = i; j < bookCount - 1; j++) {
                    books[j] = books[j + 1];
                }
                books[bookCount - 1] = null;
                bookCount--;
                return removed;
            }
        }
        return null;
    }

    public Book findBook(String query) {
        for (int i = 0; i < bookCount; i++) {
            Book book = books[i];
            if (book.getTitle().equalsIgnoreCase(query) || book.getIsbn().equalsIgnoreCase(query)) {
                return book;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return shelfId + " [Category: " + category + ", Capacity: " + bookCount + "/" + capacity + "]";
    }
}
