import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class StorageManager {
    private static final String allBookFilesName = "files.txt";

    public StorageManager() {
        try {
            createFilesFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createFilesFile() throws IOException {
        File filesFile = new File(allBookFilesName);
        filesFile.createNewFile();
    }

    public void addToFilesFile(FileName fileName) throws IOException {
        FileWriter savedFilesName = new FileWriter(allBookFilesName, true);
        savedFilesName.write(fileName.toString());
        savedFilesName.close();
    }

    public void createBookTitleFile(String getBookTitleFileName) throws IOException {
        File booksTitleFile = new File(getBookTitleFileName);
        booksTitleFile.createNewFile();
    }

    public void addBookTitleAndChapter(Book book, Chapter chapter, String fileName) throws IOException {
        FileWriter bookAndChapterTitleFile = new FileWriter(fileName, true);
        bookAndChapterTitleFile.write(book.toString());
        bookAndChapterTitleFile.write(chapter.toString());
        bookAndChapterTitleFile.close();
    }

    public HashMap<Book, Chapter> getBookAndChapters(String fileName) throws IOException {
        // File books = new File(fileName);
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        HashMap<Book, Chapter> bookChapterHashMap = new HashMap();
        String line = reader.readLine();
        while (line != null) {
            String bookAndChapterString = reader.readLine();
            String[] bookAndChapterDetails = bookAndChapterString.split(",");
            if (bookAndChapterDetails.length < 2) break;
            bookChapterHashMap.put((new Book(bookAndChapterDetails[0])),
                    (new Chapter(bookAndChapterDetails[0])));
        }
        return bookChapterHashMap;
    }

    public ArrayList<FileName> getFileNames() throws FileNotFoundException {
        File booksInformation = new File(allBookFilesName);
        //getting info from file
        Scanner reader = new Scanner(booksInformation);
        ArrayList<FileName> allFileNames = new ArrayList<>();
        while (reader.hasNextLine()) {
            String booksFileNameString = reader.nextLine();
            String[] booksFileNameDetails = booksFileNameString.split(",");
            if (booksFileNameDetails.length < 1) break;
            allFileNames.add(new FileName(booksFileNameDetails[0]));
        }
        return allFileNames;
    }

}
