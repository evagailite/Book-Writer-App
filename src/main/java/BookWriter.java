import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class BookWriter {
    private Scanner scanner = new Scanner(System.in);
    private StorageManager storageManager = new StorageManager();
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<Chapter> chapters = new ArrayList<>();
    private HashMap<Book, Chapter> writer = new HashMap<>();

    public void createNewBook() {
        Book book = collectBook();
        Chapter chapter = collectChapter();
        writer.put(book, chapter);
        //storageManager.createFile();
        System.out.println("Book with title \"" + book.getTitle() +
                "\" created successfully!");
    }

    private Book collectBook() {
        Book book = new Book();
        try {
            System.out.print("Please enter book title: ");
            book.setTitle(scanner.nextLine());
            book.setId(books.size());
            generateBooksId();
        } catch (Exception exception) {
            System.out.println("Something went wrong! Please repeat action");
            collectBook();
        }
        return book;
    }

    private Chapter collectChapter() {
        Chapter chapter = new Chapter();
        try {
            System.out.print("Please enter chapter title: ");
            chapter.setChapterTitle(scanner.nextLine());
            chapter.setId(chapters.size());
            generateChaptersId();
        } catch (Exception exception) {
            System.out.println("Something went wrong! Please repeat action");
            collectChapter();
        }
        return chapter;
    }

    public void openBook() {
    }

    public void deleteBook() {
    }

    public void viewAllBooks() {
//        for (Book allBooks : books) {
//            System.out.println(String.format("%-5s%-5s%-5s", allBooks.getId(), allBooks.getTitle(),
//                    allBooks.getChapter()));
//        }
    }

    private int generateBooksId() {
        return (books.size() < 1) ? 0 : books.get(books.size() - 1).getId() + 1;
    }

    private int generateChaptersId() {
        return (chapters.size() < 1) ? 0 : chapters.get(chapters.size() - 1).getId() + 1;
    }
}
