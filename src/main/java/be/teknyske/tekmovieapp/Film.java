package be.teknyske.tekmovieapp;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 1, message = "Title is required")
    private String title;

    @Size(min = 1, message = "Description is required")
    private String description;

    @Max(value = 2016, message = "Release year must be in the past")
    private int releaseYear;

    @Min(value = 1, message = "Length is required")
    @Max(value = 200, message = "Nobody watches movies that long!")
    private int length;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name="film_id")
    private List<Review> reviewList = new ArrayList<Review>();


    public Film() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }
}
