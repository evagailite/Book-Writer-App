public class Book {
    private String bookTitle;

    public Book() {
    }

    public Book(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    @Override
    public String toString() {
//        return "Book: \"" + bookTitle + "\"\n";
        return bookTitle + ",\n";
    }
}
