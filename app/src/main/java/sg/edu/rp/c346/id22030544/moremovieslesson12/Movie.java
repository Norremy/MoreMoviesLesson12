package sg.edu.rp.c346.id22030544.moremovieslesson12;

import java.io.Serializable;

public class Movie implements Serializable {
    private int id;
    private String title;
    private String Genre;
    private String Rating;
    private int year;

    public Movie(int id, String title, String genre, String rating, int year) {
        this.id = id;
        this.title = title;
        this.Genre = genre;
        this.Rating = rating;
        this.year = year;
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

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public String getRating() {
        return Rating;
    }

    public void setRating(String rating) {
        Rating = rating;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
