package com.example.physicsshortnotes;

public class bookModel {
    String coverpic,name,author;

    public String getCoverpic() {
        return coverpic;
    }

    public void setCoverpic(String coverpic) {
        this.coverpic = coverpic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public bookModel() {
    }

    public bookModel(String coverpic, String name, String author) {
        this.coverpic = coverpic;
        this.name = name;
        this.author = author;
    }
}
