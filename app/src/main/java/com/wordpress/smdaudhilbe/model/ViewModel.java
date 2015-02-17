package com.wordpress.smdaudhilbe.model;

/**
 * Created by mohammed-2284 on 17/02/15.
 */
public class ViewModel {

    private long id;
    private String text;
    private String image;

    public ViewModel(long id, String text, String image) {
        this.id = id;
        this.text = text;
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
