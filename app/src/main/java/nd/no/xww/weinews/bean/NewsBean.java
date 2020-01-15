package nd.no.xww.weinews.bean;

/**
 * @author xww
 * @desciption :
 * @date 2020/1/7
 * @time 22:50
 */
public class NewsBean {

    public String title;
    public String url;
    public String author;
    public String image1;
    public String image2;
    public String image3;
    public String date;

    public NewsBean(String title, String url, String author, String image1, String image2, String image3, String date) {
        this.title = title;
        this.url = url;
        this.author = author;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
