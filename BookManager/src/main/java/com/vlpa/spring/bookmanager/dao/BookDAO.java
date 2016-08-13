package com.vlpa.spring.bookmanager.dao;

import com.vlpa.spring.bookmanager.model.Book;

import java.util.List;

public interface BookDAO {

    public void addBook(Book book);
    public void updateBook(Book book);
    public void removeBook(int id);
    public Book getBookById(int id);
    public List<Book> listBooks();

}
