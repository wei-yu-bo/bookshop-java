package BookManager.model;

public class Book {
    private Integer bookId;
    private String bookname;
    private String author;
    private String bookType;
    private  String publish;
    private Integer number;
    private String remark;
    private  String status;

    public Book() {

    }

   
    public Integer getBookId() {
        return bookId;
    }
    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }
    public String getBookname() {
        return bookname;
    }
    public void setBookname(String bookname) {
        this.bookname = bookname;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getBookType() {
        return bookType;
    }
    public void setBookType(String bookType) {
        this.bookType = bookType;
    }
    public String getPublish() {
        return publish;
    }
    public void setPublish(String publish) {
        this.publish = publish;
    }
    public Integer getNumber() {
        return number;
    }
    public void setNumber(Integer number) {
        this.number = number;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Book(Integer bookId, String status) {
        this.bookId = bookId;
        this.status = status;
    }
    public Book(Integer bookId, String bookname, String publish, String author, Integer number, String bookType, String remark,String status) {
        this.bookId = bookId;
        this.bookname = bookname;
        this.author = author;
        this.bookType = bookType;
        this.publish = publish;
        this.number = number;
        this.remark = remark;
        this.status = status;
    }


//    public Book(Integer bookId, String bookname, String author, String bookType, String publish, Integer number, String remark, String status) {
//        this.bookId = bookId;
//        this.bookname = bookname;
//        this.author = author;
//        this.bookType = bookType;
//        this.publish = publish;
//        this.number = number;
//        this.remark = remark;
//        this.status = status;
//
//    }

}
