public class Chapter {
    private int id;
    private String chapterTitle;

    public Chapter() {
    }

    public Chapter(int id, String chapterTitle) {
        this.id = id;
        this.chapterTitle = chapterTitle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChapterTitle() {
        return chapterTitle;
    }

    public void setChapterTitle(String chapterTitle) {
        this.chapterTitle = chapterTitle;
    }

    @Override
    public String toString() {
        return id + " \t " + chapterTitle;
    }
}
