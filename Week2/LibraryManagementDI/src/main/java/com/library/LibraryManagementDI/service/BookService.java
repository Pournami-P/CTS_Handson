package com.library.LibraryManagementDI.service;

import com.library.LibraryManagementDI.repository.BookRepository;

public class BookService {

    private BookRepository bookRepository;

    // Setter Injection
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void displayService() {
        System.out.println("Book Service Bean Created");
        bookRepository.displayRepository();
    }
}