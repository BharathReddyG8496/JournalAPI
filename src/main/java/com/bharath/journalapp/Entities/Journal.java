package com.bharath.journalapp.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;



import java.time.LocalDate;
@Entity
@Data
@NoArgsConstructor
public class Journal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank
    private String journal_name;

    private LocalDate publishDate;

    @NotBlank
    private String description;

    @ManyToOne
    @JsonBackReference
    private User user;

    @Override
    public String toString() {
        return "Journal{" +
                "id=" + id +
                ", journal_name='" + journal_name + '\'' +
                ", publishDate=" + publishDate +
                ", description='" + description + '\'' +
                '}';
    }
}
