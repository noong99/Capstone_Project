package com.example.BugWiki;

public class Bug {
    private String name;
    private String image;
    private String breed; //유해성 (12.18 원래 int->string로 수정됨)
    private String habit; //습성
    private String harmfulness; //유해성 (12.18 원래 int->string로 수정됨)
    private String disease; //유해질병
    private String eradication; //퇴치법
    // 12.17 추가됨
    private String product_image; // 제품 이미지
    private String product_url; // 제품 주소

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


    // 12.18 수정됨 (int->String)
    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getHabit() {
        return habit;
    }

    public void setHabit(String habit) {
        this.habit = habit;
    }

    // 12.18 수정됨 (int->String)
    public String getHarmfulness() {
        return harmfulness;
    }

    public void setHarmfulness(String harmfulness) {
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
    
    // 12.17 추가됨
    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }


    public String getProduct_url() {
        return product_url;
    }

    public void setProduct_url(String product_url) {
        this.product_url = product_url;
    }

}
