package mypack;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dao.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Handler {

    private final Connection conn;
    private PreparedStatement pst;
    private ResultSet rs;

    public Handler() throws SQLException, ClassNotFoundException {
        conn = MyConnection.createConnection();
    }

    public String insertData(List<String> list) throws SQLException {
        pst = conn.prepareStatement("insert into JASVEER_YADAV_EMP (FIRSTNAME,LASTNAME,DATEOFBIRTH,GENDER,MOBILENUMBER,EMAIL) values(?,?,?,?,?,?)");
        pst.setString(1, list.get(0));
        pst.setString(2, list.get(1));
        pst.setString(3, list.get(2));
        pst.setString(4, list.get(3));
        pst.setString(5, list.get(4));
        pst.setString(6, list.get(5));
        return pst.executeUpdate() + " Record Added";
    }

    public JsonArray showAll() throws SQLException {
        pst = conn.prepareStatement("select * from JASVEER_YADAV_EMP");
        JsonArray ja = new JsonArray();
        rs = pst.executeQuery();
        while (rs.next()) {
            JsonObject jo = new JsonObject();
            jo.addProperty("firstname", rs.getString("FIRSTNAME"));
            jo.addProperty("lasttname", rs.getString("LASTNAME"));
            jo.addProperty("dob", rs.getString("DATEOFBIRTH"));
            jo.addProperty("gender", rs.getString("GENDER"));
            jo.addProperty("mno", rs.getString("MOBILENUMBER"));
            jo.addProperty("email", rs.getString("EMAIL"));
            jo.addProperty("id", rs.getString("ID"));
            ja.add(jo);
            new Gson().toJson(ja);
        }
        conn.close();
        return ja;
    }

    public int deleteData(String recordid) throws SQLException {
        pst = conn.prepareStatement("delete from JASVEER_YADAV_EMP where ID=?");
        pst.setString(1, recordid);
        return pst.executeUpdate();
        //conn.close();
    }

    public JsonArray searchData(Integer id) throws SQLException {
        pst = conn.prepareStatement("select *from JASVEER_YADAV_EMP where ID=?");
        pst.setInt(1, id);
        rs = pst.executeQuery();
        JsonArray ja = new JsonArray();
        while (rs.next()) {
            JsonObject jo = new JsonObject();
            jo.addProperty("firstname", rs.getString("FIRSTNAME"));
            jo.addProperty("lasttname", rs.getString("LASTNAME"));
            jo.addProperty("dob", rs.getString("DATEOFBIRTH"));
            jo.addProperty("gender", rs.getString("GENDER"));
            jo.addProperty("mno", rs.getString("MOBILENUMBER"));
            jo.addProperty("email", rs.getString("EMAIL"));
            jo.addProperty("id", rs.getInt("ID"));
            ja.add(jo);
            new Gson().toJson(ja);
        }
        conn.close();
        return ja;
    }

    public String updateData(List<String> list, int id) throws SQLException {
        int i = 0;
        try {
            System.out.println("dfgfd" + id);
            pst = conn.prepareStatement("update JASVEER_YADAV_EMP set FIRSTNAME=?, LASTNAME=?,DATEOFBIRTH=?,GENDER=?,MOBILENUMBER=?, EMAIL=? where ID=?");
            pst.setString(1, list.get(0));
            System.out.println("list.get(0) " + list.get(0));
            pst.setString(2, list.get(1));
            pst.setString(3, list.get(2));
            pst.setString(4, list.get(3));
            pst.setString(5, list.get(4));
            pst.setString(6, list.get(5));
            pst.setInt(7, id);
            i = pst.executeUpdate();
            conn.close();
            System.out.println("i " + i);
        } catch (SQLException ex) {
        }
        return i + " Record Update";

    }

}
