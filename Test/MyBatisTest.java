import MyBatis.Employee;
import MyBatis.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mouse.sun.myprint.Print.println;

/**
 * 1、接口编程
 *  原生： Dao ==>>DaoImpl
 *  mybatis: Mapper ==>>xxMapper.xml
 * 2、SQLSession代表和数据库的一次会话，用完必须关闭
 * 3、SQLSession和connection一样都是非线程安全的，每次使用都应该去获取新的对象。
 * 4、mapper接口没有实现类，但是MaBatis会为接口生成一个代理对象，将接口和xml进行绑定
 *   EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
 * 5、两个重要的配置文件：
 *      mybatis的全局配置文件：包含数据库连接池信息，事务管理器信息等...系统运行环境信息
 *      sql映射文件：保存每一个sql语句的映射信息
 *                  将sql抽取出来
 */

public class MyBatisTest {
    /**
     * 获取SqlSessionFactory对象
     * @return
     * @throws IOException
     */
    public SqlSessionFactory getSqlSessionFactory() throws IOException{
        String resource = "conf/MyBatis-config.xml";
        SqlSessionFactory sqlSessionFactory;

        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory;

    }

    /**
     * 1、根据xml配置文件（全局配置文件）创建一个SqlSessionFactory对象
     * 2、SQL映射文件 ，配置一个SQL，以及SQL的封装原则
     * 3、将SQL映射文件注册到全局配置文件中
     * 4、写代码
     *      1)根据全局配置文件得到SqlSessionFactory
     *      2)使用sqlSession工厂，获取到SqlSession对象使用其进行增删查改
     *          一个sqlSession就是代表和数据库的一次会话，用完关闭
     *      3)使用sql的唯一标志来告诉MyBatis执行哪个sql。sql都是保存在sql的映射文件中的。
     */
    @Test
    public  void test(){
        String resource = "conf/MyBatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //2、获取SqlSession实例，能直接执行已经映射的SQL语句
        SqlSession session = sqlSessionFactory.openSession();
        try {
            //selectOne的第一个参数：命名空间 + select的id名
            Employee employee = session.selectOne("MyBatis.EmployeeMapper.getEmployeeId",1);
            System.out.println(employee);
        }finally {
            session.close();
        }
    }
    /**
     * 通过绑定接口来获取数据库的信息
     *
     *
     */
    @Test
    public void testInterface() throws IOException {
        //1、获取sqlSessionFactory对象
        SqlSessionFactory sessionFactory = getSqlSessionFactory();
        //2、获取openSession对象
        SqlSession session = sessionFactory.openSession();
        //3、获取接口的实现类对象
        //会为接口自动的创建一个代理对象，代理对象去执行增删改查方法
        try {
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
            Employee employeeId = mapper.getEmployeeId(1);
            println(mapper.getClass());
            println(employeeId);

        }finally {
            session.close();
        }

    }


    /**
     * 测试增删改
     *1、mybatis允许增删改直接定义以下类型返回值
     *  Integer、Long、Boolean
     *2、我们需要手动提交数据
     *  sessionFactory.openSession();==>>需要手动提交
     *  sessionFactory.openSession(true);===>>自动提交
     */
  @Test
    public void testOperation() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession(true);
        try {
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
//            Employee employee = new Employee(null,"Jerry", "女", "32220260@qq.com");
//            mapper.addEmp(employee);
//            println(employee.getId());
//            boolean res = mapper.deleteEmp(2);
//            println(res);
//            2、手动提交数据
//            session.commit();

            //查询多个参数
//            Employee emp = mapper.getEmpByIdAndLastName(4,"Gson");
//            println(emp);

            //根据map进行查询
            Map<String, Object> map = new HashMap<>();
            map.put("id",4);
            map.put("last_name","Gson");
            Employee empByMap = mapper.getEmpByMap(map);
            println(empByMap);

        } finally {
            session.close();
        }
    }

    /**
     * 查询
     *
     */
    @Test
    public void testLook() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession(true);
        try {
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
            //查询名字中包含r的
//            List<Employee> Emps = mapper.getEmpByLastNameLike("%r%");
//            for(Employee emp:Emps){
//                println(emp);
//            }

            //查询返回的值是map
            Map<String, Employee> empByIdReturnMap = mapper.getEmpByLastNameLikeReturnMap("%r%");
            println(empByIdReturnMap);
        }finally {
            session.close();
        }
    }
}
