import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Collectors;

public class StorageManager {
    private static final String allChapterFilesName = "chapterFiles.txt";
    private static final String allFinishedBookFilesName = "finishedBooks.txt";

    public StorageManager() {
        try {
            createChapterFile();
            createFinishedBooksFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createChapterFile() throws IOException {
        File filesFile = new File(allChapterFilesName);
        filesFile.createNewFile();
    }

    public void createFinishedBooksFile() throws IOException {
        File filesFile = new File(allFinishedBookFilesName);
        filesFile.createNewFile();
    }

    public void addToFilesFile(FileName fileName) throws IOException {
        FileWriter savedFilesName = new FileWriter(allChapterFilesName, true);
        savedFilesName.write(fileName.toString());
        savedFilesName.close();
    }

    public void addToFilesFile(String fileName) throws IOException {
        FileWriter savedFilesName = new FileWriter(allChapterFilesName);
        savedFilesName.write(fileName);
        savedFilesName.close();
    }

    public void addFinishedBookToFile(String fileName) throws IOException {
        FileWriter savedFilesName = new FileWriter(allFinishedBookFilesName, true);
        savedFilesName.write(fileName);
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
        File booksInformation = new File(allChapterFilesName);
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

    public ArrayList getFinishedBooks() throws FileNotFoundException {
        File booksInformation = new File(allFinishedBookFilesName);
        Scanner reader = new Scanner(booksInformation);
        ArrayList finishedBooks = new ArrayList<>();
        while (reader.hasNextLine()) {
            String booksFileNameString = reader.nextLine();
            String[] booksFileNameDetails = booksFileNameString.split(",");
            if (booksFileNameDetails.length < 1) break;
            finishedBooks.add(new FileName(booksFileNameDetails[0]));
        }
        return finishedBooks;
    }


    public void displayChapterContent(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();
    }

    public void deleteChapters(String fileName) {
        File deleteFile = new File(fileName);
        deleteFile.delete();
    }

    public void mergeFiles(String fileName, String newFile) throws IOException {
        FileWriter newFileBook = new FileWriter(newFile, true);
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = reader.readLine()) != null) {
            newFileBook.write(line.concat("\n"));
        }
        reader.close();
        newFileBook.close();
    }

    public void createBookFile(String getBookTitleFileName) throws IOException {
        File booksTitleFile = new File(getBookTitleFileName);
        booksTitleFile.createNewFile();
    }


    public void overWriteFilesName(ArrayList<FileName> files) throws IOException {
        this.addToFilesFile(files.stream()
                .map(FileName::toString)
                .collect(Collectors.joining(""))
        );
    }
}
