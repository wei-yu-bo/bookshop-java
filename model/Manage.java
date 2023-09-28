package BookManager.model;

public class Manage {
    private String mid;
    private String mpassword;

    public String getMid() {
        return mid;
    }
    public void setMid(String mid) {
        this.mid = mid;
    }
    public String getMpassword() {
        return mpassword;
    }
    public void setMpassword(String mpassword) {
        this.mpassword = mpassword;
    }
    public Manage() {
    }
    public Manage(String mid, String mpassword) {
        this.mid = mid;
        this.mpassword = mpassword;
    }
}
