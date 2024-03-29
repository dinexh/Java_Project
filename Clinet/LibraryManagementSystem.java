import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LibraryManagementSystem extends Application {

    private List<Book> books = new ArrayList<>();
    private TextField bookIdField, bookNameField, authorField, priceField;
    private TextArea outputArea;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Library Management System");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label bookIdLabel = new Label("Book ID:");
        grid.add(bookIdLabel, 0, 0);
        bookIdField = new TextField();
        grid.add(bookIdField, 1, 0);

        Label bookNameLabel = new Label("Book Name:");
        grid.add(bookNameLabel, 0, 1);
        bookNameField = new TextField();
        grid.add(bookNameField, 1, 1);

        Label authorLabel = new Label("Author:");
        grid.add(authorLabel, 0, 2);
        authorField = new TextField();
        grid.add(authorField, 1, 2);

        Label priceLabel = new Label("Price:");
        grid.add(priceLabel, 0, 3);
        priceField = new TextField();
        grid.add(priceField, 1, 3);

        Button addButton = new Button("Add Book");
        addButton.setOnAction(e -> addBook());
        grid.add(addButton, 0, 4, 2, 1);

        outputArea = new TextArea();
        grid.add(outputArea, 0, 5, 2, 1);

        Scene scene = new Scene(grid, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addBook() {
        String bookId = bookIdField.getText();
        String bookName = bookNameField.getText();
        String author = authorField.getText();
        String price = priceField.getText();

        Book book = new Book(bookId, bookName, author, price);
        books.add(book);

        saveBooksToFile();

        outputArea.appendText("Book added: " + book + "\n");
    }

    private void saveBooksToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("books.txt"))) {
            for (Book book : books) {
                writer.println(book);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class Book {
        String bookId;
        String bookName;
        String author;
        String price;

        Book(String bookId, String bookName, String author, String price) {
            this.bookId = bookId;
            this.bookName = bookName;
            this.author = author;
            this.price = price;
        }

        @Override
        public String toString() {
            return bookId + ", " + bookName + ", " + author + ", " + price;
        }
    }
}
