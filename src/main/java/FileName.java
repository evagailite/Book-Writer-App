public class FileName {
    private String fileName;

    public FileName(String fileName) {
        this.fileName = fileName;
    }

    public FileName() {
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return fileName + ",\n";
    }
}
