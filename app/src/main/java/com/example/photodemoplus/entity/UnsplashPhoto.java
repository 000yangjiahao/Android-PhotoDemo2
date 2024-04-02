package com.example.photodemoplus.entity;

public class UnsplashPhoto {
    private String id;
    private Urls urls;
    private String description;
    private String alt_description;

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAlt_description(String alt_description) {
        this.alt_description = alt_description;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setUrls(Urls urls) {
        this.urls = urls;
    }
    public String getDescription() {
        return description;
    }

    public String getAlt_description() {
        return alt_description;
    }

    public String getId() {
        return id;
    }

    public Urls getUrls() {
        return urls;
    }


    public static class Urls {
        private String raw;

        public String getRaw() {
            return raw;
        }

        public void setRaw(String raw) {
            this.raw = raw;
        }
    }
}
