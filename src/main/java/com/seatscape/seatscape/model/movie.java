package com.seatscape.seatscape.model;
//import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Integer movieid;
    @JsonProperty
    private String title;
    @JsonProperty
    private String description;
    @JsonProperty
    private Integer duration;
    @JsonProperty
    private String lang;
    @JsonProperty
    private LocalDate releasedate;
    @JsonProperty
    private String genre;
    @Override
    public String toString() {
        return "movie{" +
                "movieid=" + movieid +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", duration=" + duration +
                ", lang='" + lang + '\'' +
                ", releasedate=" + releasedate +
                ", genre='" + genre + '\'' +
                '}';
    }
}
