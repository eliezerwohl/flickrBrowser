package com.eliezerwohl.flickrbrowser;

/**
 * Created by Elie on 11/25/2016.
 */

class Photo {
    private String mTitle;
    private String mAuthor;
    private String mAuthorId;
    private String mlink;
    private String mtag;
    private String mImage;

    String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String author) {
        mAuthor = author;
    }

    String getAuthorId() {
        return mAuthorId;
    }

    public void setAuthorId(String authorId) {
        mAuthorId = authorId;
    }

    String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

    String getMlink() {
        return mlink;
    }

    public void setMlink(String mlink) {
        this.mlink = mlink;
    }

    String getMtag() {
        return mtag;
    }

    public void setMtag(String mtag) {
        this.mtag = mtag;
    }

    String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Photo(String author, String authorId, String image, String mlink, String mtag, String title) {
        mAuthor = author;
        mAuthorId = authorId;
        mImage = image;
        this.mlink = mlink;
        this.mtag = mtag;
        mTitle = title;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "mAuthor='" + mAuthor + '\'' +
                ", mTitle='" + mTitle + '\'' +
                ", mAuthorId='" + mAuthorId + '\'' +
                ", mlink='" + mlink + '\'' +
                ", mtag='" + mtag + '\'' +
                ", mImage='" + mImage + '\'' +
                '}';
    }
}
