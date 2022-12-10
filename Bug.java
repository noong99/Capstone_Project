package com.example.bug_1128;

public class Bug {
    private String name;
    private String image;
    private int breed; //번식력
    private String habit; //습성
    private int harmfulness; //유해성
    private String disease; //유해질병
    private String eradication; //퇴치법

    public Bug(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getBreed() {
        return breed;
    }

    public void setBreed(int breed) {
        this.breed = breed;
    }

    public String getHabit() {
        return habit;
    }

    public void setHabit(String habit) {
        this.habit = habit;
    }

    public int getHarmfulness() {
        return harmfulness;
    }

    public void setHarmfulness(int harmfulness) {
        this.harmfulness = harmfulness;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getEradication() {
        return eradication;
    }

    public void setEradication(String eradication) {
        this.eradication = eradication;
    }
}
