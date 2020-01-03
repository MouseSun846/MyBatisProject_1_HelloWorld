package MyBatisPlus;


public interface EmployeeMapperPlus {
        public EmployeePlus getEmpById(Integer id);
        public EmployeePlus getEmpAndDept(Integer id);
        public EmployeePlus getEmpByIdStep(Integer id);


}
