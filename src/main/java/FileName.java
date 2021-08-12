import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class FileName {
    private String fileName;

    @Override
    public String toString() {
        return fileName + ",\n";
    }
}
