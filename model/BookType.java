package BookManager.model;

public class BookType {
    private Integer typeId;
    private String typeName;
    private String remark;//备注

    public BookType(Integer typeId, String typeName, String remark) {
        this.typeId = typeId;
        this.typeName = typeName;
        this.remark = remark;
    }
    public Integer getTypeId() {
        return typeId;
    }
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BookType() {
    }

    public BookType(String typeName, String remark) {
        this.typeName = typeName;
        this.remark = remark;
    }

    public void getModel() {
    }
}
