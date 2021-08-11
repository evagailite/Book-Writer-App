import java.util.Scanner;

public class Menu {
    BookWriter bookWriter = new BookWriter();
    private Scanner scanner = new Scanner(System.in);

    public void starts() {
        displayBookMenu();

        bookWriter.syncBooks();

        int userChoice = Integer.parseInt(scanner.nextLine());
        handleBookChoice(userChoice);
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
                bookWriter.viewAllBooks();
                break;
            case 5:
                System.out.println("Thank you for visiting Book Writer!");
                System.exit(0);
                break;
            default:
                starts();
                break;
        }
        starts();
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
        }
    }

    private void handleChapterChoice(int userChoice, String bookTitle) {
        switch (userChoice) {
            case 1:
                //bookWriter.createNewChapter();
                break;
            case 2:
                //bookWriter.openChapter();
                break;
            case 3:
                //bookWriter.viewChapters();
                break;
            case 4:
                //bookWriter.viewAllChapters();
                break;
            case 5:
                displayBookMenu();
                int choice = Integer.parseInt(scanner.nextLine());
                handleBookChoice(choice);
                break;
            default:
                starts();
                break;
        }
        starts();
    }

    public void displayBookMenu() {
        System.out.println("\nPlease choose an action: ");
        System.out.println("\t 1 - Create a New Book");
        System.out.println("\t 2 - Open a Book");
        System.out.println("\t 3 - Delete a Book");
        System.out.println("\t 4 - View all Books");
        System.out.println("\t 5 - Exit");
    }

    public void displayChapterMenu() {
        System.out.println("\nPlease choose an action: ");
        System.out.println("\t 1 - Create New Chapter");
        System.out.println("\t 2 - Open a Chapter");
        System.out.println("\t 3 - View Chapter");
        System.out.println("\t 4 - View All Chapters");
        System.out.println("\t 5 - Return to Main");
    }

    public void welcomeMessage() {
        System.out.println("Welcome to the Book Writer!");
    }
}
