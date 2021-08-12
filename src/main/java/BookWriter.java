import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class BookWriter {
    private Scanner scanner = new Scanner(System.in);
    private StorageManager storageManager = new StorageManager();
    private HashMap<Book, Chapter> writer = new HashMap<>();
    ArrayList<FileName> files = new ArrayList<>();
    ArrayList lists = new ArrayList<>();

    public void createNewBook() {
        Book book = collectBook();
        Chapter chapter = collectChapter();
        writer.put(new Book(book.getBookTitle()), new Chapter(chapter.getChapterTitle()));

        String chapterTitle = chapter.getChapterTitle().replaceAll("\\s+", "");
        String fileNameExtension = ".txt";
        String fileName = chapterTitle.concat(fileNameExtension);

//        StringBuilder content = chapterContent();

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

    public void deleteBook() {
    }

    public void viewAllBooks() {

        for (int i = 0; i < lists.size(); i++) {
            HashMap<Book, Chapter> tmpData = (HashMap<Book, Chapter>) lists.get(i);
            for (Map.Entry<Book, Chapter> entry : tmpData.entrySet()) {
                System.out.println((i + 1) + " - Book: " + entry.getKey().getBookTitle() + " Chapter: " + entry.getValue().getChapterTitle());
            }
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
                writer = storageManager.getBookAndChapters(fileName.getFileName());
                lists.add(writer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean findBook(String bookTitle) {
        for (int i = 0; i < lists.size(); i++) {
            HashMap<Book, Chapter> tmpData = (HashMap<Book, Chapter>) lists.get(i);
            for (Map.Entry<Book, Chapter> entry : tmpData.entrySet()) {
                if (entry.getKey().getBookTitle().contains(bookTitle)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void createNewChapter(String bookTitle) {
        Chapter chapter = collectChapter();
        Book book = new Book(bookTitle);
        writer.put(book, new Chapter(chapter.getChapterTitle()));
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

        System.out.println("Chapter \"" + chapter.getChapterTitle() + "\" " +
                "successfully added to the Book \"" + bookTitle + "\"!");
    }

    public void openChapter(String bookTitle) {

        displayChapter(bookTitle);

        try {
            System.out.println("Please enter chapter title to open it:");
            String chapterTitle = scanner.nextLine();
            System.out.println(" ");
            if (findChapter(chapterTitle)) {

                String chapter = chapterTitle.replaceAll("\\s+", "");
                String fileNameExtension = ".txt";
                String fileName = chapter.concat(fileNameExtension);

                storageManager.displayChapterContent(fileName);

                createContent(fileName);

            } else {
                System.out.println("Chapter with the title \"" + chapterTitle + "\" not found!");
                System.out.println("Please try again!");
            }
        } catch (Exception e) {
            System.out.println("Something went wrong!");
        }
    }

    private void createContent(String fileName) {
        String content = null;
        System.out.println("\nStart writing here (to finish type \"THE-END\")");
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (!line.equals("THE-END")) {
                try {
                    storageManager.addChapter(fileName, line.concat("\n"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                break;
            }
        }

        try {
            storageManager.addChapter(fileName, String.valueOf(content));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean findChapter(String chapterTitle) {
        for (int i = 0; i < lists.size(); i++) {
            HashMap<Book, Chapter> tmpData = (HashMap<Book, Chapter>) lists.get(i);
            for (Map.Entry<Book, Chapter> entry : tmpData.entrySet()) {
                if (entry.getValue().getChapterTitle().contains(chapterTitle)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void displayChapter(String bookTitle) {
        System.out.println("All chapters within Book \"" + bookTitle + "\": ");
        for (int i = 0; i < lists.size(); i++) {
            HashMap<Book, Chapter> tmpData = (HashMap<Book, Chapter>) lists.get(i);
            for (Map.Entry<Book, Chapter> entry : tmpData.entrySet()) {
                if (entry.getKey().getBookTitle().contains(bookTitle)) {
                    System.out.println(entry.getValue().getChapterTitle());
                }
            }
        }
    }

    public void viewChapters() {

    }
}
