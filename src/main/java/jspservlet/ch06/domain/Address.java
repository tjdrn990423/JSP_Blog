package jspservlet.ch06.domain;

public class Address {
    private int seq;
    private int zipcode;
    private String sido;
    private String gugun;
    private String dong;
    private String road;
    private int bldg;
    private String bldgname;
    private String dongprev;
    private int bldgprev;
    private String bunji;

    public Address() {

    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public String getSido() {
        return sido;
    }

    public void setSido(String sido) {
        this.sido = sido;
    }

    public String getGugun() {
        return gugun;
    }

    public void setGugun(String gugun) {
        this.gugun = gugun;
    }

    public String getDong() {
        return dong;
    }

    public void setDong(String dong) {
        this.dong = dong;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public int getBldg() {
        return bldg;
    }

    public void setBldg(int bldg) {
        this.bldg = bldg;
    }

    public String getBldgname() {
        return bldgname;
    }

    public void setBldgname(String bldgname) {
        this.bldgname = bldgname;
    }

    public String getDongprev() {
        return dongprev;
    }

    public void setDongprev(String dongprev) {
        this.dongprev = dongprev;
    }

    public int getBldgprev() {
        return bldgprev;
    }

    public void setBldgprev(int bldgprev) {
        this.bldgprev = bldgprev;
    }

    public String getBunji() {
        return bunji;
    }

    public void setBunji(String bunji) {
        this.bunji = bunji;
    }
}
