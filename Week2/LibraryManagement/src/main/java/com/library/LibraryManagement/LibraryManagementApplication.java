package com.library.LibraryManagement;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.library.LibraryManagement.repository.BookRepository;
import com.library.LibraryManagement.service.BookService;

public class LibraryManagementApplication {

    public static void main(String[] args) {

        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        BookRepository repository =
                context.getBean("bookRepository", BookRepository.class);

        BookService service =
                context.getBean("bookService", BookService.class);

        repository.displayRepository();
        service.displayService();

        ((ClassPathXmlApplicationContext) context).close();
    }
}