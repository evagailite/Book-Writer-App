import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Book {
    private String bookTitle;

    @Override
    public String toString() {
        return bookTitle + ",\n";
    }
}
