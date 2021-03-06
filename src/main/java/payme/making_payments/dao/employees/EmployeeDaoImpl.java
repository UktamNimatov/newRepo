package payme.making_payments.dao.employees;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeDaoImpl setJdbcTemplate(DataSource postgres, JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = new JdbcTemplate(postgres);
        return this;
    }

    @Override
    public void create(Employee employee) {
        String sql = "insert into employees(name, gender, marital_status, recommendation) values(?, ?, ?, ?)";
        jdbcTemplate.update(sql, preparedStatement -> {
            preparedStatement.setObject(1, employee.getName());
            preparedStatement.setObject(2, employee.getGender());
            preparedStatement.setObject(3, employee.getMarital_status());
            preparedStatement.setObject(4, employee.getRecommendation());
        });
    }

    @Override
    public void update(Employee employee) {
//        Integer id = employee.getId();
        String sql = "update employees set name = ?, gender = ?, marital_status = ?, recommendation = ? where id = ?";
        jdbcTemplate.update(sql, preparedStatement -> {
            preparedStatement.setObject(1, employee.getName());
            preparedStatement.setObject(2, employee.getGender());
            preparedStatement.setObject(3, employee.getMarital_status());
            preparedStatement.setObject(4, employee.getRecommendation());
            preparedStatement.setObject(5, employee.getId());
        });
    }

    @Override
    public void deleteById(Integer id) {
        String sql = "delete from employees where id = ?";
        jdbcTemplate.update(sql, preparedStatement -> {
            preparedStatement.setObject(1, id);
        });
    }

    @Override /*This method returns an object of Student class via id*/
    public Employee getById(Integer id) {
        Employee employee = new Employee();
        String sql = "select * from students where id = ?";
        jdbcTemplate.query(sql, preparedStatement -> {
            preparedStatement.setObject(1, id);
        }, resultSet -> {
            if (resultSet.next())
                employee.setName(resultSet.getString("name"))
                        .setGender(resultSet.getString("gender"))
                        .setRecommendation(resultSet.getString("recommendation"))
                        .setMarital_status(resultSet.getString("marital_status"));
            return null;
        });
        return employee;
    }

    @Override
    public List<Employee> getList() {
        String sql = "select * from employees";
        List<Employee> employeeList = new ArrayList<>();    /*Opening a new list with Employee parameter*/
        jdbcTemplate.query(sql, (resultSet, i) -> {
            employeeList.add(new Employee()
                    .setGender(resultSet.getString("gender"))
                    .setId(resultSet.getInt("id"))
                    .setMarital_status(resultSet.getString("marital_status"))
                    .setRecommendation(resultSet.getString("recommendation"))
                    .setName(resultSet.getString("name")));
            return null;
        });
        return employeeList;
    }
}
