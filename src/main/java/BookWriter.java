import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class BookWriter {
    private Scanner scanner = new Scanner(System.in);
    private StorageManager storageManager = new StorageManager();
    private HashMap<Book, Chapter> writer = new HashMap<>();
    ArrayList<FileName> chapterFiles = new ArrayList<>();
    ArrayList bookChaptersList = new ArrayList<>();
    ArrayList finishedBooksFiles = new ArrayList<>();

    public void createNewBook() {
        Book book = collectBook();
        Chapter chapter = collectChapter();
        writer.put(new Book(book.getBookTitle()), new Chapter(chapter.getChapterTitle()));

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
        viewAllBooksWithChapters();
        try {
            System.out.print("Please enter Book Title to delete: ");
            String bookTitle = scanner.nextLine();
            removeBooksAndChapters(bookTitle);
        } catch (Exception e) {
            System.out.println("Something went wrong!");
            e.printStackTrace();
        }
    }

    public void viewAllBooksWithChapters() {
        if (bookChaptersList.size() == 0) {
            if (writer.size() != 0) {
                writer.entrySet().forEach(entry -> {
                    System.out.println(entry.getKey() + " " + entry.getValue());
                });
            } else {
                System.out.println("No Added Books for now");
            }
        } else {
            for (int i = 0; i < bookChaptersList.size(); i++) {
                HashMap<Book, Chapter> tmpData = (HashMap<Book, Chapter>) bookChaptersList.get(i);
                for (Map.Entry<Book, Chapter> entry : tmpData.entrySet()) {
                    System.out.println("Book: " + entry.getKey().getBookTitle() + " Chapter: " + entry.getValue().getChapterTitle());
                }
            }
        }
    }

    public void syncBooks() {
        try {
            this.chapterFiles = storageManager.getFileNames();
            this.finishedBooksFiles = storageManager.getFinishedBooks();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (FileName fileName : chapterFiles) {
            try {
                writer = storageManager.getBookAndChapters(fileName.getFileName());
                bookChaptersList.add(writer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean findBook(String bookTitle) {
        if (bookChaptersList.size() == 0) {
            for (Book key : writer.keySet()) {
                if (key.getBookTitle().contains(bookTitle)) {
                    return true;
                }
            }
        } else {
            for (int i = 0; i < bookChaptersList.size(); i++) {
                HashMap<Book, Chapter> tmpData = (HashMap<Book, Chapter>) bookChaptersList.get(i);
                for (Map.Entry<Book, Chapter> entry : tmpData.entrySet()) {
                    if (entry.getKey().getBookTitle().contains(bookTitle)) {
                        return true;
                    }
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
            System.out.print("Please enter chapter title to open it:");
            String chapterTitle = scanner.nextLine();
            System.out.println(" ");
            if (doesChapterExists(chapterTitle)) {

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
    }

    private boolean doesChapterExists(String chapterTitle) {
        for (int i = 0; i < bookChaptersList.size(); i++) {
            HashMap<Book, Chapter> tmpData = (HashMap<Book, Chapter>) bookChaptersList.get(i);
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
        if (bookChaptersList.size() == 0) {
            writer.forEach((key, value) -> System.out.println(key + " : " + value));
        } else {
            for (int i = 0; i < bookChaptersList.size(); i++) {
                HashMap<Book, Chapter> tmpData = (HashMap<Book, Chapter>) bookChaptersList.get(i);
                for (Map.Entry<Book, Chapter> entry : tmpData.entrySet()) {
                    if (entry.getKey().getBookTitle().contains(bookTitle)) {
                        System.out.println(entry.getValue().getChapterTitle());
                    }
                }
            }
        }
    }

    public void viewChaptersWithContent(String bookTitle) {

        displayChapter(bookTitle);
        try {
            System.out.print("Please enter chapter title to view it:");
            String chapterTitle = scanner.nextLine();
            System.out.println(" ");
            if (doesChapterExists(chapterTitle)) {

                String chapter = chapterTitle.replaceAll("\\s+", "");
                String fileNameExtension = ".txt";
                String fileName = chapter.concat(fileNameExtension);

                storageManager.displayChapterContent(fileName);

            } else {
                System.out.println("Chapter with the title \"" + chapterTitle + "\" not found!");
                System.out.println("Please try again!");
            }
        } catch (Exception e) {
            System.out.println("Something went wrong!");
        }
    }

    public void mergeAllChapters(String bookTitle) {
        String fileName = bookTitle.replaceAll("\\s+", "").concat(".txt");
        try {
            storageManager.createBookFile(fileName);
            storageManager.addFinishedBookToFile(fileName);

            getChapters(bookTitle, fileName);
            finishedBooksFiles.add(bookTitle.concat(",\n"));
            removeBooksAndChapters(bookTitle);
            System.out.println("Chapter merged! The Book \"" + bookTitle + "\" successfully finished!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getChapters(String bookTitle, String fileName) {
        for (int i = 0; i < bookChaptersList.size(); i++) {
            HashMap<Book, Chapter> tmpData = (HashMap<Book, Chapter>) bookChaptersList.get(i);
            for (Map.Entry<Book, Chapter> entry : tmpData.entrySet()) {
                if (entry.getKey().getBookTitle().contains(bookTitle)) {
                    String result = entry.getValue().getChapterTitle();
                    String chapterFileName = result.replaceAll("\\s+", "").concat(".txt");

                    chapterFiles.remove(result);
                    try {
                        storageManager.mergeFiles(chapterFileName, fileName);

                        storageManager.overWriteFilesName(chapterFiles);
                        storageManager.deleteChapters(chapterFileName);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void removeBooksAndChapters(String bookTitle) {
        writer.remove(bookTitle);
    }

    public void viewFinishedBooks() {
        try {
            System.out.print("Enter Book title to view: ");
            String bookTitle = scanner.nextLine();
            try {
                storageManager.displayChapterContent(bookTitle.replaceAll("\\s+", "").concat(".txt"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println("Something went wrong!");
        }
    }

    public void displayFinishedBooks() {
        if (finishedBooksFiles.size() == 0) {
            System.out.println("No Finished Books for now");
        } else {
            System.out.println("All finished Books: ");
            for (int i = 0; i < finishedBooksFiles.size(); i++) {
                System.out.println(finishedBooksFiles.get(i));
            }
            viewFinishedBooks();
        }
    }
}
