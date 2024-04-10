import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class Book {
    private int id;
    private String title;
    private String author;
    private boolean available;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.available = true;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

class Library {
    private ArrayList<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public Book findBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title) && book.isAvailable()) {
                return book;
            }
        }
        return null;
    }
}

public class LibraryManagementSystemGUI extends JFrame {
    private Library library;
    private JTextField searchField;
    private JTextArea resultArea;

    public LibraryManagementSystemGUI() {
        library = new Library();
        // Adding some initial books to the library
        library.addBook(new Book(1, "To Kill a Mockingbird", "Harper Lee"));
        library.addBook(new Book(2, "1984", "George Orwell"));
        library.addBook(new Book(3, "Pride and Prejudice", "Jane Austen"));

        setTitle("Library Management System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        JLabel searchLabel = new JLabel("Enter book title:");
        searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);

        JPanel searchPanel = new JPanel(new FlowLayout());
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        panel.add(searchPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(resultArea), BorderLayout.CENTER);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = searchField.getText();
                Book foundBook = library.findBook(title);
                if (foundBook != null) {
                    resultArea.setText("Book found!\nTitle: " + foundBook.getTitle() + "\nAuthor: " + foundBook.getAuthor());
                } else {
                    resultArea.setText("Book not found or not available.");
                }
            }
        });

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LibraryManagementSystemGUI();
            }
        });
    }
}
