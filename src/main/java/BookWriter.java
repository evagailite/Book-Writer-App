import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class BookWriter {
    private Scanner scanner = new Scanner(System.in);
    private StorageManager storageManager = new StorageManager();
    private HashMap<Book, Chapter> writer = new HashMap<>();
    ArrayList<FileName> files = new ArrayList<>();

    public void createNewBook() {
        Book book = collectBook();
        Chapter chapter = collectChapter();
       // writer.put(String.valueOf(book.getBookTitle()), String.valueOf(chapter.getChapterTitle()));
        String chapterTitle = chapter.getChapterTitle().replaceAll("\\s+", "");
        String fileNameExtension = ".txt";
        String fileName = chapterTitle.concat(fileNameExtension);

        try {
            FileName file = collectFileName(fileName);
            storageManager.addToFilesFile(file);
            storageManager.createBookTitleFile(fileName);
            storageManager.addBookTitleAndChapter(book, chapter, fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("The Book \"" + book.getBookTitle() +
                "\" and chapter \"" + chapter.getChapterTitle() + "\" created successfully!");
    }

    private String getFileName(Book book, Chapter chapter) {

        String chapterExtension = "_Ch_";
        String getChapterName = chapter.getChapterTitle().replaceAll("\\s+", "");
        String getBookTitleFileName = book.getBookTitle().replaceAll("\\s+", "").concat(chapterExtension);
        String fileNameExtension = ".txt";
        String fileName = getBookTitleFileName.concat(getChapterName);
        String fileNameTotal = fileName.concat(fileNameExtension);

        return fileNameTotal;
    }

    private FileName collectFileName(String fileName) {
        FileName file = new FileName();
        try {
            file.setFileName(fileName);
        } catch (Exception exception) {
            System.out.println("Something went wrong! Please repeat action");
            collectFileName(fileName);
        }
        return file;
    }

    private Book collectBook() {
        Book book = new Book();
        try {
            System.out.print("Please enter book title: ");
            book.setBookTitle(scanner.nextLine());
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
        System.out.println(writer);
        for (Map.Entry<String, String> entry : writer.entrySet()) {
            System.out.println("Book: " + entry.getKey() + ", Chapter: " + entry.getValue());
        }
    }

    public void syncBooks() {
        try {
            this.files = storageManager.getFileNames();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (FileName fileName : files) {
            try {
                this.writer = storageManager.getBookAndChapters(fileName.getFileName());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

}
