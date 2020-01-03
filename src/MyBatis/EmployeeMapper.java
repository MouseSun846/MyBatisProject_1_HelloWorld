package MyBatis;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper {
    //返回一条记录的map；可以就是列名，值就是对应的值
    //使用注解告诉MyBatis封装这个map的时候使用哪个属性作为map的key
    @MapKey("id")
    public Map<String,Employee> getEmpByLastNameLikeReturnMap(String lastname);
    public Map<String,Object> getEmpByIdReturnMap(Integer id);
    public List<Employee> getEmpByLastNameLike(String lastName);
    public Employee  getEmployeeId(Integer id);
    public Employee getEmpByIdAndLastName(@Param("id")Integer id, @Param("last_name")String last_name);
    public Employee getEmpByMap(Map<String,Object> map);
    public void addEmp(Employee employee);
    public void updateEmp(Employee employee);
    public boolean deleteEmp(Integer id);
}
