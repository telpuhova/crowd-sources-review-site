package models;

/**
 * Created by Guest on 1/18/18.
 */
public class Review {
    private int id;
    private String title;
    private int rating;
    private String content;
    private String userName;
    private int specialId;

    public Review(String title, int rating, String content, String userName) {
        this.title = title;
        this.rating = rating;
        this.content = content;
        this.userName = userName;
    }

    public Review(String title, int rating, String content, String userName, int specialId) {
        this.title = title;
        this.rating = rating;
        this.content = content;
        this.userName = userName;
        this.specialId = specialId;
    }

    public Review(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getSpecialId() {
        return specialId;
    }

    public void setSpecialId(int specialId) {
        this.specialId = specialId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Review review = (Review) o;

        if (rating != review.rating) return false;
        if (specialId != review.specialId) return false;
        if (!title.equals(review.title)) return false;
        if (content != null ? !content.equals(review.content) : review.content != null) return false;
        return userName != null ? userName.equals(review.userName) : review.userName == null;
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + rating;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + specialId;
        return result;
    }
}
