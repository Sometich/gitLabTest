package com.example.newgitlabtest.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public final class Pet {
    private List<String> photoUrls;
    private String name;
    private long id;
    private Category category;
    private List<TagsItem> tags;
    private String status;

    public List<String> getPhotoUrls() {
        return new ArrayList<>(photoUrls);
    }

    public List<TagsItem> getTags() {
        return new ArrayList<>(tags);
    }
}