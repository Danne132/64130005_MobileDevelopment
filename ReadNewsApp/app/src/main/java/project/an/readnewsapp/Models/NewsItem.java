package project.an.readnewsapp.Models;

public class NewsItem {
    String title, imgUrl,pupDate, link, content;
    boolean isSave;

    public NewsItem(String title, String imgUrl, String pupDate, String link, String content) {
        this.title = title;
        this.imgUrl = imgUrl;
        this.pupDate = pupDate;
        this.link = link;
        this.content = content;
        this.isSave = false;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }

    public String getPupDate() {
        return pupDate;
    }

    public void setPupDate(String pupDate) {
        this.pupDate = pupDate;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

//    public String getCategory() {
//        return category;
//    }
//
//    public void setCategory(String category) {
//        this.category = category;
//    }

    public boolean isSave() {
        return isSave;
    }

    public void setSave(boolean save) {
        isSave = save;
    }
}
