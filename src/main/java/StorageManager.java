import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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

    public HashMap<String, String> getBookAndChapters(String fileName) throws FileNotFoundException {
        File books = new File(fileName);
        //getting info from file
        Scanner reader = new Scanner(books);
        HashMap<String, String> bookChapterHashMap = new HashMap();
        while (reader.hasNextLine()) {
            String businessClassString = reader.nextLine();
            String[] businessClassSeatsDetails = businessClassString.split(",");
            if (businessClassSeatsDetails.length < 2) break;
            bookChapterHashMap.put((businessClassSeatsDetails[0]), businessClassSeatsDetails[1]);
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
