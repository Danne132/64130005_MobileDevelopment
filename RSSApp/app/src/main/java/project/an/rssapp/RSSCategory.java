package project.an.rssapp;

public class RSSCategory {
    private String title;
    private String url;

    public RSSCategory(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }
}
