package BookManager.model;

/**
 * 建立用户实体
 */
public class User {
    private Integer userId;
    private String username;
    private String password;
    private String phone;

    public Integer getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }



    public String getPhone() {
        return phone;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
        System.out.println(username);
    }

    public void setPassword(String password) {
        this.password = password;
        System.out.println(password);
    }

    public void setPhone(String phone) {
        this.phone = phone;
        System.out.println(phone);
    }

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String phone) {
        this.username = username;
        this.password = password;
        this.phone = phone;
    }
}
