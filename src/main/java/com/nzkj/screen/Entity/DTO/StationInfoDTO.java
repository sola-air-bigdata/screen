package com.nzkj.screen.Entity.DTO;

/**
 * @Author: Liu yang
 * @Date: 2020/12/16 11:13
 * Describe:
 */
public class StationInfoDTO {

    private String image;

    private String name;

    private String address;

    private int score;

    private int pilesum;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getPilesum() {
        return pilesum;
    }

    public void setPilesum(int pilesum) {
        this.pilesum = pilesum;
    }
}
