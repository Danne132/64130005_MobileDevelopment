package project.an.rssapp;

public class RSSItem {
    private String title;       // Tiêu đề tin tức
    private String imageUrl;    // URL hình ảnh đầu tiên
    private String content;     // Nội dung tin tức
    private String pubDate;     // Thời gian xuất bản
    private String link;        // Đường dẫn để chia sẻ

    public RSSItem(String title, String imageUrl, String content ,String pubDate, String link) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.pubDate = pubDate;
        this.link = link;
        this.content = content;
    }

    // Getter và Setter
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
