public class Chapter {
    private String chapterTitle;

    public Chapter() {
    }

    public Chapter(String chapterTitle) {
        this.chapterTitle = chapterTitle;
    }

    public String getChapterTitle() {
        return chapterTitle;
    }

    public void setChapterTitle(String chapterTitle) {
        this.chapterTitle = chapterTitle;
    }

    @Override
    public String toString() {
//        return "Chapter: \"" + chapterTitle + "\"\n";
        return chapterTitle + "\n";
    }
}
