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

    public void addChapter(String fileName, String content) throws IOException {
        FileWriter chapterTitleFile = new FileWriter(fileName, true);
        chapterTitleFile.write(content);
        chapterTitleFile.close();
    }

    public HashMap<Book, Chapter> getBookAndChapters(String fileName) throws IOException {
        HashMap<Book, Chapter> bookChapterHashMap = new HashMap();

        BufferedReader reader = new BufferedReader(new FileReader(fileName));

        String key = reader.readLine();
        String value = reader.readLine();

        while (key != null && value != null) {
            bookChapterHashMap.put(new Book(key), new Chapter(value));
            key = reader.readLine();
            value = reader.readLine();
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

    public void displayChapterContent(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();
    }

}
