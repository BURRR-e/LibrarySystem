package com.librarysystem;

import java.util.ArrayList;
import java.util.List;

public class Shelf {
    private String shelfId;
    private String category;
    private int capacity;
    private List<Book> books;

    public Shelf(String shelfId, String category, int capacity) {
        this.shelfId = shelfId;
        this.category = category;
        this.capacity = capacity;
        this.books = new ArrayList<>();
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
        return books.size();
    }

    public List<Book> getBooks() {
        return books;
    }

    public boolean isFull() {
        return books.size() >= capacity;
    }

    public boolean addBook(Book book) {
        if (isFull()) {
            return false;
        }
        books.add(book);
        return true;
    }

    public Book removeBookByIsbn(String isbn) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getIsbn().equalsIgnoreCase(isbn)) {
                return books.remove(i);
            }
        }
        return null;
    }

    public Book removeBookByTitle(String title) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getTitle().equalsIgnoreCase(title)) {
                return books.remove(i);
            }
        }
        return null;
    }

    public Book findBook(String query) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(query) || book.getIsbn().equalsIgnoreCase(query)) {
                return book;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return shelfId + " [Category: " + category + ", Capacity: " + books.size() + "/" + capacity + "]";
    }
}
