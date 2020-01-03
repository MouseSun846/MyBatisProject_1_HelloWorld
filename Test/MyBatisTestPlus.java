import MyBatis.Employee;
import MyBatisPlus.EmployeeMapperPlus;
import MyBatisPlus.EmployeePlus;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static com.mouse.sun.myprint.Print.println;

public class MyBatisTestPlus {
    /**
     * 获取SqlSessionFactory对象
     * @return
     * @throws IOException
     */
    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "conf/MyBatis-config.xml";
        SqlSessionFactory sqlSessionFactory;

        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory;

    }
    @Test
    public void test() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession(true);
        try {
            EmployeeMapperPlus mapper = session.getMapper(EmployeeMapperPlus.class);
//            EmployeePlus empById = mapper.getEmpById(1);
//            EmployeePlus empAndDept = mapper.getEmpAndDept(1);
//            println(empAndDept.getDept());
            //测试分部查询
            EmployeePlus empByIdStep = mapper.getEmpByIdStep(1);
            println(empByIdStep.getDept());
        } finally {
            session.close();
        }

    }

}
