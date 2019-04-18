package Util;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ControlPerson {

    ConectaBanco conecta = new ConectaBanco();

    public void inserir(ModelPerson mod) {
        try {
            conecta.conexao();
            PreparedStatement pst = conecta.conn.prepareStatement("INSERT INTO mscit (regno, name) VALUES (?, ?)");
            pst.setInt(1, mod.getId());
            pst.setString(2, mod.getFirst_name());
            pst.executeUpdate();
            System.out.println("data inserted: " + mod.getFirst_name());
            conecta.desconecta();
        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }
    }

    public void attendance(ModelPerson mod) {
        try {

            System.out.println("----------------------------------------------------------------------------------");
            conecta.conexao();

            PreparedStatement pst = conecta.conn.prepareStatement("INSERT INTO attendance (regno, present, dateon) VALUES (?, ?, ?)");
            pst.setInt(1, mod.getId());
            pst.setInt(2, mod.getPresent());
            pst.setString(3, mod.getDateon());
            pst.executeUpdate();

            System.out.println("attendance registered: " + mod.getDateon());
            conecta.desconecta();
        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }
    }

    public void deleteattendance(ModelPerson mod) {
     
        System.out.println("******************************************************************************");

        try {

            conecta.conexao();

            PreparedStatement pst = conecta.conn.prepareStatement("DELETE from attendance where regno = ? and dateon = ?");
            pst.setInt(1, mod.getId());
            pst.setString(2, mod.getFirst_name());
            pst.executeUpdate();

            System.out.println("deleted: " + mod.getDateon());
            conecta.desconecta();
        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }
    }

    public void putabsent(ModelPerson mod) {
        try {

            System.out.println("///////////////////////////////////////////////////////////////////////////////");
            conecta.conexao();

            PreparedStatement pst = conecta.conn.prepareStatement("DELETE from attendance where id = ? and dateon = ?");
            pst.setInt(1, mod.getId());
            pst.setString(2, mod.getDateon());
            pst.executeUpdate();

            System.out.println("deleted: " + mod.getDateon());
            conecta.desconecta();
        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }
    }
}
