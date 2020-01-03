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
 * 1���ӿڱ��
 *  ԭ���� Dao ==>>DaoImpl
 *  mybatis: Mapper ==>>xxMapper.xml
 * 2��SQLSession��������ݿ��һ�λỰ���������ر�
 * 3��SQLSession��connectionһ�����Ƿ��̰߳�ȫ�ģ�ÿ��ʹ�ö�Ӧ��ȥ��ȡ�µĶ���
 * 4��mapper�ӿ�û��ʵ���࣬����MaBatis��Ϊ�ӿ�����һ��������󣬽��ӿں�xml���а�
 *   EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
 * 5��������Ҫ�������ļ���
 *      mybatis��ȫ�������ļ����������ݿ����ӳ���Ϣ�������������Ϣ��...ϵͳ���л�����Ϣ
 *      sqlӳ���ļ�������ÿһ��sql����ӳ����Ϣ
 *                  ��sql��ȡ����
 */

public class MyBatisTest {
    /**
     * ��ȡSqlSessionFactory����
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
     * 1������xml�����ļ���ȫ�������ļ�������һ��SqlSessionFactory����
     * 2��SQLӳ���ļ� ������һ��SQL���Լ�SQL�ķ�װԭ��
     * 3����SQLӳ���ļ�ע�ᵽȫ�������ļ���
     * 4��д����
     *      1)����ȫ�������ļ��õ�SqlSessionFactory
     *      2)ʹ��sqlSession��������ȡ��SqlSession����ʹ���������ɾ���
     *          һ��sqlSession���Ǵ�������ݿ��һ�λỰ������ر�
     *      3)ʹ��sql��Ψһ��־������MyBatisִ���ĸ�sql��sql���Ǳ�����sql��ӳ���ļ��еġ�
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
        //2����ȡSqlSessionʵ������ֱ��ִ���Ѿ�ӳ���SQL���
        SqlSession session = sqlSessionFactory.openSession();
        try {
            //selectOne�ĵ�һ�������������ռ� + select��id��
            Employee employee = session.selectOne("MyBatis.EmployeeMapper.getEmployeeId",1);
            System.out.println(employee);
        }finally {
            session.close();
        }
    }
    /**
     * ͨ���󶨽ӿ�����ȡ���ݿ����Ϣ
     *
     *
     */
    @Test
    public void testInterface() throws IOException {
        //1����ȡsqlSessionFactory����
        SqlSessionFactory sessionFactory = getSqlSessionFactory();
        //2����ȡopenSession����
        SqlSession session = sessionFactory.openSession();
        //3����ȡ�ӿڵ�ʵ�������
        //��Ϊ�ӿ��Զ��Ĵ���һ��������󣬴������ȥִ����ɾ�Ĳ鷽��
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
     * ������ɾ��
     *1��mybatis������ɾ��ֱ�Ӷ����������ͷ���ֵ
     *  Integer��Long��Boolean
     *2��������Ҫ�ֶ��ύ����
     *  sessionFactory.openSession();==>>��Ҫ�ֶ��ύ
     *  sessionFactory.openSession(true);===>>�Զ��ύ
     */
  @Test
    public void testOperation() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession(true);
        try {
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
//            Employee employee = new Employee(null,"Jerry", "Ů", "32220260@qq.com");
//            mapper.addEmp(employee);
//            println(employee.getId());
//            boolean res = mapper.deleteEmp(2);
//            println(res);
//            2���ֶ��ύ����
//            session.commit();

            //��ѯ�������
//            Employee emp = mapper.getEmpByIdAndLastName(4,"Gson");
//            println(emp);

            //����map���в�ѯ
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
     * ��ѯ
     *
     */
    @Test
    public void testLook() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession(true);
        try {
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
            //��ѯ�����а���r��
//            List<Employee> Emps = mapper.getEmpByLastNameLike("%r%");
//            for(Employee emp:Emps){
//                println(emp);
//            }

            //��ѯ���ص�ֵ��map
            Map<String, Employee> empByIdReturnMap = mapper.getEmpByLastNameLikeReturnMap("%r%");
            println(empByIdReturnMap);
        }finally {
            session.close();
        }
    }
}
