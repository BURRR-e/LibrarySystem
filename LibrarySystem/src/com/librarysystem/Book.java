package com.librarysystem;

public class Book {
    private String title;
    private String description;
    private String author;
    private String isbn;
    private String publisher;
    private int publishYear;
    private String genre;
    private int pages;
    private String language;

    public Book(String title, String description, String author, String isbn, 
                String publisher, int publishYear, String genre, int pages, String language) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.isbn = isbn;
        this.publisher = publisher;
        this.publishYear = publishYear;
        this.genre = genre;
        this.pages = pages;
        this.language = language;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getMetadataString() {
        StringBuilder sb = new StringBuilder();
        sb.append("========================================================================\n");
        sb.append("                       BOOK DETAILS: ").append(title.toUpperCase()).append("\n");
        sb.append("========================================================================\n");
        sb.append("  Title:        ").append(title).append("\n");
        sb.append("  Description:  ").append(description).append("\n");
        sb.append("  Author:       ").append(author).append("\n");
        sb.append("  ISBN:         ").append(isbn).append("\n");
        sb.append("  Genre:        ").append(genre).append("\n");
        sb.append("  Publisher:    ").append(publisher).append("\n");
        sb.append("  Publish Year: ").append(publishYear).append("\n");
        sb.append("  Pages:        ").append(pages).append(" pages\n");
        sb.append("  Language:     ").append(language).append("\n");
        sb.append("========================================================================");
        return sb.toString();
    }

    @Override
    public String toString() {
        return "'" + title + "' by " + author + " (ISBN: " + isbn + ")";
    }
}
