package com.librarysystem;

import java.util.ArrayList;
import java.util.List;

public class ShelvingSystem {
    private List<Shelf> shelves;

    public ShelvingSystem() {
        this.shelves = new ArrayList<>();
    }

    public boolean addShelf(Shelf shelf) {
        if (findShelfById(shelf.getShelfId()) != null) {
            return false;
        }
        shelves.add(shelf);
        return true;
    }

    public List<Shelf> getShelves() {
        return shelves;
    }

    public Shelf findShelfById(String shelfId) {
        for (Shelf shelf : shelves) {
            if (shelf.getShelfId().equalsIgnoreCase(shelfId)) {
                return shelf;
            }
        }
        return null;
    }

    public boolean addBookToShelf(String shelfId, Book book) {
        Shelf shelf = findShelfById(shelfId);
        if (shelf != null) {
            return shelf.addBook(book);
        }
        return false;
    }

    public Book removeBookFromShelf(String shelfId, String query) {
        Shelf shelf = findShelfById(shelfId);
        if (shelf != null) {
            Book book = shelf.removeBookByIsbn(query);
            if (book == null) {
                book = shelf.removeBookByTitle(query);
            }
            return book;
        }
        return null;
    }

    public boolean transferBook(String fromShelfId, String toShelfId, String query) {
        Shelf fromShelf = findShelfById(fromShelfId);
        Shelf toShelf = findShelfById(toShelfId);

        if (fromShelf == null || toShelf == null) {
            return false;
        }

        if (toShelf.isFull()) {
            return false;
        }

        Book bookToMove = fromShelf.findBook(query);
        if (bookToMove == null) {
            return false;
        }

        fromShelf.removeBookByIsbn(bookToMove.getIsbn());
        toShelf.addBook(bookToMove);
        return true;
    }

    public List<SearchResult> searchBook(String query) {
        List<SearchResult> results = new ArrayList<>();
        for (Shelf shelf : shelves) {
            for (Book book : shelf.getBooks()) {
                if (book.getTitle().toLowerCase().contains(query.toLowerCase()) || 
                    book.getAuthor().toLowerCase().contains(query.toLowerCase()) ||
                    book.getIsbn().equalsIgnoreCase(query)) {
                    results.add(new SearchResult(shelf, book));
                }
            }
        }
        return results;
    }

    public static class SearchResult {
        private Shelf shelf;
        private Book book;

        public SearchResult(Shelf shelf, Book book) {
            this.shelf = shelf;
            this.book = book;
        }

        public Shelf getShelf() {
            return shelf;
        }

        public Book getBook() {
            return book;
        }
    }
}
