package io.github.MateuszNk.voreado.domain.discovery;

import java.time.LocalDateTime;

public class Discovery {
    private Integer id;
    private String title;
    private String url;
    private String description;
    private LocalDateTime dateAdded;
    private Integer categoryId;

    public Discovery(Integer id,
                     String title,
                     String url,
                     String description,
                     LocalDateTime dateAdded,
                     Integer categoryId) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.description = description;
        this.dateAdded = dateAdded;
        this.categoryId = categoryId;
    }

    public Integer getId() { return id; }
    public String getTitle() { return title; }
    public String getUrl() { return url; }
    public String getDescription() { return description; }
    public LocalDateTime getDateAdded() { return dateAdded; }
    public Integer getCategoryId() { return categoryId; }
}
