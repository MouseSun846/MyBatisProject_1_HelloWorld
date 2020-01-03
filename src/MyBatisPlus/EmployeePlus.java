package MyBatisPlus;

public class EmployeePlus {
    private Integer id;
    private String last_name;
    private String gender;
    private String email;
    private Department dept;
    /**
     * 创建有参构造器必须要创建无参构造器，否则许多框架不能使用
     */
    public EmployeePlus() {
    }

    public EmployeePlus(Integer id, String last_name, String gender, String email,Department dept) {
        this.id = id;
        this.last_name = last_name;
        this.gender = gender;
        this.email = email;
        this.dept = dept;
    }

    //名称需要与数据库对应
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", last_name='" + last_name + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
