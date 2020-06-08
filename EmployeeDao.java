package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.*;
import javax.json.JsonArray;
import javax.json.JsonObject;
import model.Employee_Model;

public class EmployeeDao {
private final Connection conn;
private PreparedStatement pst;
private ResultSet rs;
public EmployeeDao() throws SQLException, ClassNotFoundException
{
    conn=MyConnection.createConnection();
}
public int insertData(Employee_Model empmodel)throws SQLException
{
    pst=conn.prepareStatement("insert into JASVEER_YADAV_EMP values(?,?,?,?,?,?)");
    pst.setString(1, empmodel.getFirstName());
    pst.setString(2, empmodel.getLastName());
    pst.setString(3, empmodel.getDob());
    pst.setString(4, empmodel.getGender());
    pst.setString(5, empmodel.getMobileNumber());
    pst.setString(6, empmodel.getEmail());
    return pst.executeUpdate();
}
    public List<Employee_Model> showAll() throws SQLException
    {
        
        List<Employee_Model> list=new ArrayList<>();
        pst=conn.prepareStatement("select * from JASVEER_YADAV_EMP");
        rs=pst.executeQuery();
        while(rs.next())
        {
            Employee_Model emp=new Employee_Model();
            emp.setFirstName(rs.getString(1));
            emp.setLastName(rs.getString(2));
            emp.setDob(rs.getString(3));
            emp.setGender(rs.getString(4));
            emp.setMobileNumber(rs.getString(5));
            emp.setEmail(rs.getString(6));
            list.add(emp);
     
        }
        return list;
    }
    public Employee_Model search(String firstname) throws SQLException
    {
        pst=conn.prepareStatement("select from JASVEER_YADAV_EMP where FIRSTNAME=?");
        pst.setString(1, firstname);
        rs=pst.executeQuery();
        Employee_Model empmod=new Employee_Model();
        if(rs.next())
        {
            empmod.setFirstName(rs.getString(1));
            empmod.setLastName(rs.getString(2));
            empmod.setDob(rs.getString(3));
            empmod.setGender(rs.getString(4));
            empmod.setMobileNumber(rs.getString(5));
            empmod.setEmail(rs.getString(6));
        }
        return empmod;
    }
    public int deleteData(String firstname) throws SQLException
    {
        pst=conn.prepareStatement("delete from JASVEER_YADAV_EMP where FIRSTNAME=?");
        pst.setString(1, firstname);
        return pst.executeUpdate();
    }
    public int updateData(Employee_Model empmodel) throws SQLException
    {
        pst=conn.prepareStatement("update JASVEER_YADAV_EMP set LASTNAME=?,DATEOFBIRTH=?,GENDER=?,MOBILENUMBER=?,EMAIL=? where  FIRSTNAME=?");
        pst.setString(1, empmodel.getFirstName());
        pst.setString(2, empmodel.getLastName());
        pst.setString(3, empmodel.getDob());
        pst.setString(4, empmodel.getGender());
        pst.setString(5, empmodel.getMobileNumber());
        pst.setString(6, empmodel.getEmail());
        return pst.executeUpdate();
    }
}

