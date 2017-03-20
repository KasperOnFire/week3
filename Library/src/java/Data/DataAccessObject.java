package Data;

import User.User;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import libraryInventory.Item;

public class DataAccessObject {

    private DBConnector db = null;
    private Connection conn;
    private PreparedStatement stmt;
    private CallableStatement call;

    /**
     * The class constructor gives access to the getters in the class, which
     * give access to the database. The constructor also opens a connection to
     * the database.
     *
     * @param inputcon is the connection to the database that will be used.
     */
    public DataAccessObject(DBConnector inputcon) {
        try {
            db = inputcon;
            conn = db.getConnection();

        } catch (Exception ex) {
            Logger.getLogger(DataAccessObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Item> getInventory() {
        String sql = "select * from inventory";
        ArrayList<Item> inv = new ArrayList();
        for (Item item : inv) {
            inv.indexOf(item);
        }
        try {
            stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Item i = null;
                String ino = rs.getString("ino");
                String isbn = rs.getString("isbn");
                String title = rs.getString("title");
                String author = rs.getString("author");
                String itype = rs.getString("itype");
                String language = rs.getString("language");
                String publisher = rs.getString("publisher");
                String publishdate = rs.getString("publishdate");
                i = new Item(ino, isbn, title, author, itype, language, publisher, publishdate);
                inv.add(i);
            }

        } catch (Exception e) {
        }
        return inv;
    }

    public ArrayList<Item> getReserved(String uno) {
        String sql = "select * from reservations natural join inventory where uno=?";
        ArrayList<Item> res = new ArrayList();
        try {
            conn = db.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, uno);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Item i = null;
                String ino = rs.getString("ino");
                String isbn = rs.getString("isbn");
                String title = rs.getString("title");
                String author = rs.getString("author");
                String itype = rs.getString("itype");
                String language = rs.getString("language");
                String publisher = rs.getString("publisher");
                String publishdate = rs.getString("publishdate");
                i = new Item(ino, isbn, title, author, itype, language, publisher, publishdate);
                res.add(i);
            }

        } catch (Exception e) {
        }
        return res;
    }

    public boolean reserveItem(String ino, String uno) {
        String sql = "insert into reservations values(default, ?, ?, curdate())";
        try {
            stmt = conn.prepareCall(sql);
            stmt.setString(1, ino);
            stmt.setString(2, uno);

            int i = stmt.executeUpdate();
            if (i == 1) {
                return true;
            }
        } catch (SQLException e) {

        }
        return false;
    }

    public boolean deleteReservation(String ino, String uno) {
        String sql = "delete from reservations where ino=? and uno =? limit 1";
        try {
            stmt = conn.prepareCall(sql);
            stmt.setString(1, ino);
            stmt.setString(2, uno);

            int i = stmt.executeUpdate();
            if (i == 1) {
                return true;
            }
        } catch (SQLException e) {
        }
        return false;
    }

    public void addItem(String isbn, String title, String author, String itype, String language, String publisher, String publishdate) {
        String sql = "insert into inventory values (null, ?, ?, ?, ?, ?, ?, ?, curdate())";

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, isbn);
            stmt.setString(2, title);
            stmt.setString(3, author);
            stmt.setString(4, itype);
            stmt.setString(5, language);
            stmt.setString(6, publisher);
            stmt.setString(7, publishdate);

            stmt.executeUpdate();
        } catch (Exception e) {
        }

    }

    public String getUnoUserPass(String uname, String upass) {
        String sql = "select uno from users where uname=? and pass=?;";
        String uno = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, uname);
            stmt.setString(2, upass);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                uno = rs.getString("uno");
            }
        } catch (Exception e) {
        }
        return uno;
    }

    public String registerUser(String username, String password, String address, String phone, String mail, String ssn) {
        String sql = "insert into users values(null, ?, ?, ?, ?, ?, ?, default, default)";
        String uno = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, address);
            stmt.setString(4, phone);
            stmt.setString(5, mail);
            stmt.setString(6, ssn);

            stmt.executeUpdate();

            uno = getUnoUserPass(username, password);
        } catch (Exception e) {
        }

        return uno;
    }

    public String getEnoUserPass(String username, String password) {
        String sql = "select eno from employees where ename=? and epass=?;";
        String uno = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                uno = rs.getString("eno");
            }
        } catch (Exception e) {
        }
        return uno;
    }

    public void updateLibStatusFalse(String uno) {
        String sql = "update users set libstatus=0 where uno=?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, uno);
            stmt.execute();
        } catch (Exception e) {
        }
    }

    public void updateLibStatusTrue(String uno) {
        String sql = "update users set libstatus=1 where uno=?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, uno);
            stmt.execute();
        } catch (Exception e) {
        }
    }

    public Boolean getLibStatus(String uno) {
        String sql = "select rescount from users where uno=?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, uno);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int i = Integer.parseInt(rs.getString("rescount"));
                if (i > 9) {
                    updateLibStatusFalse(uno);
                    return false;
                } else {
                    updateLibStatusTrue(uno);
                    return true;
                }
            }
        } catch (Exception e) {
        }
        return false;
    }

    public void rescountUp(String uno) {
        String sql = "update users set rescount = rescount + 1 where uno=?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, uno);
            
            stmt.execute();
        } catch (Exception e) {
        }
    }

    public void rescountDown(String uno) {
        String sql = "update users set rescount = rescount -1 where uno=?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, uno);
            
            stmt.execute();
        } catch (Exception e) {
        }
    }

}
