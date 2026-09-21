package com.librarysystem;

public class ShelvingSystem {
    private Shelf[] shelves;
    private int shelfCount;

    public ShelvingSystem() {
        this.shelves = new Shelf[50];
        this.shelfCount = 0;
    }

    public boolean addShelf(Shelf shelf) {
        if (findShelfById(shelf.getShelfId()) != null) {
            return false;
        }
        if (shelfCount >= shelves.length) {
            Shelf[] temp = new Shelf[shelves.length * 2];
            System.arraycopy(shelves, 0, temp, 0, shelves.length);
            shelves = temp;
        }
        shelves[shelfCount] = shelf;
        shelfCount++;
        return true;
    }

    public Shelf[] getShelves() {
        Shelf[] activeShelves = new Shelf[shelfCount];
        System.arraycopy(shelves, 0, activeShelves, 0, shelfCount);
        return activeShelves;
    }

    public Shelf findShelfById(String shelfId) {
        for (int i = 0; i < shelfCount; i++) {
            if (shelves[i].getShelfId().equalsIgnoreCase(shelfId)) {
                return shelves[i];
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

    public SearchResult[] searchBook(String query) {
        SearchResult[] tempResults = new SearchResult[500];
        int resultCount = 0;

        for (int i = 0; i < shelfCount; i++) {
            Shelf shelf = shelves[i];
            Book[] shelfBooks = shelf.getBooks();
            for (int j = 0; j < shelf.getBookCount(); j++) {
                Book book = shelfBooks[j];
                if (book.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                    book.getAuthor().toLowerCase().contains(query.toLowerCase()) ||
                    book.getIsbn().equalsIgnoreCase(query)) {
                    if (resultCount < tempResults.length) {
                        tempResults[resultCount] = new SearchResult(shelf, book);
                        resultCount++;
                    }
                }
            }
        }

        SearchResult[] results = new SearchResult[resultCount];
        System.arraycopy(tempResults, 0, results, 0, resultCount);
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
