import java.util.Scanner;

public class Menu {
    BookWriter bookWriter = new BookWriter();
    private Scanner scanner = new Scanner(System.in);

    public void starts() {
        displayBookMenu();

        bookWriter.syncBooks();
        try {
            int userChoice = Integer.parseInt(scanner.nextLine());
            handleBookChoice(userChoice);
        } catch (Exception e) {
            exceptionMessage();
            e.printStackTrace();
        }
    }

    private void exceptionMessage() {
        System.out.println("Something went wrong!");
    }

    private void handleBookChoice(int userChoice) {
        switch (userChoice) {
            case 1:
                bookWriter.createNewBook();
                break;
            case 2:
                openBook();
                break;
            case 3:
                bookWriter.deleteBook();
                break;
            case 4:
                bookWriter.viewAllBooksWithChapters();
                break;
            case 5:
                bookWriter.displayFinishedBooks();
                break;
            case 6:
                System.out.println("Thank you for visiting Book Writer!");
                System.exit(0);
                break;
            default:
                handleBook();
                break;
        }
        handleBook();
    }

    private void handleBook() {
        displayBookMenu();
        try {
            int userChoice = Integer.parseInt(scanner.nextLine());
            handleBookChoice(userChoice);
        } catch (Exception e) {
            exceptionMessage();
            e.printStackTrace();
        }
    }

    private void openBook() {
        try {
            System.out.print("Please enter Book title to open it: ");
            String bookTitle = scanner.nextLine();
            if (bookWriter.findBook(bookTitle)) {
                displayChapterMenu();
                try {
                    int choice = Integer.parseInt(scanner.nextLine());
                    handleChapterChoice(choice, bookTitle);
                } catch (Exception e) {
                }
            } else {
                System.out.println("Book with the title \"" + bookTitle + "\" not found!");
                System.out.println("Please try again!");
            }
        } catch (Exception e) {
            System.out.println("Something went wrong!");
            e.printStackTrace();
        }
    }

    private void handleChapterChoice(int userChoice, String bookTitle) {
        switch (userChoice) {
            case 1:
                bookWriter.createNewChapter(bookTitle);
                break;
            case 2:
                bookWriter.openChapter(bookTitle);
                break;
            case 3:
                bookWriter.viewChaptersWithContent(bookTitle);
                break;
            case 4:
                bookWriter.mergeAllChapters(bookTitle);
                break;
            case 5:
                displayBookMenu();
                int choice = Integer.parseInt(scanner.nextLine());
                handleBookChoice(choice);
                break;
            default:
                handleChapter(bookTitle);
                break;
        }
        handleChapter(bookTitle);
    }

    private void handleChapter(String bookTitle) {
        displayChapterMenu();
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            handleChapterChoice(choice, bookTitle);
        } catch (Exception e) {
            exceptionMessage();
            e.printStackTrace();
        }
    }

    public void displayBookMenu() {
        System.out.println("\nPlease choose an action: ");
        System.out.println("\t 1 - Create a New Book");
        System.out.println("\t 2 - Open a Book");
        System.out.println("\t 3 - Delete a Book");
        System.out.println("\t 4 - View List of All Books with Chapters");
        System.out.println("\t 5 - View Finished Books");
        System.out.println("\t 6 - Exit");
    }

    public void displayChapterMenu() {
        System.out.println("\nPlease choose an action: ");
        System.out.println("\t 1 - Create New Chapter");
        System.out.println("\t 2 - Open Chapter to Continue Writing");
        System.out.println("\t 3 - View Chapter");
        System.out.println("\t 4 - Merge All Chapters");
        System.out.println("\t 5 - Return to Main");
    }

    public void welcomeMessage() {
        System.out.println("Welcome to the Book Writer!");
    }
}
