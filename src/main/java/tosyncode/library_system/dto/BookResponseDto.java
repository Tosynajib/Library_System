package tosyncode.library_system.dto;

import tosyncode.library_system.entities.Book;

public class BookResponseDto {

    private Long id;
    private String title;
    private String author;
    private int availableCopies;

    public BookResponseDto(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.availableCopies = book.getAvailableCopies();
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }
}
