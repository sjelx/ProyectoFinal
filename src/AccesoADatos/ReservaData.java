
package AccesoADatos;

import Entidades.Reserva;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.Time;


/**
 *
 * @author matiSqui
 */
public class ReservaData {
    
    private Connection con = null;
    
    public ReservaData(){
        con = ConexionData.getConexion();
    }
    
    public void hacerReserva(Reserva reserva, int idMesa){
        
        String sql = "INSERT INTO reserva(id_mesa, nombre_cliente, dni_cliente, fecha_reserva, hora_reserva, estado) VALUES (?,?,?,?,?,?)";

        
        try {
            PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            
            ps.setInt(1,idMesa);
            ps.setString(2, reserva.getNombreCliente());
            ps.setInt(3, reserva.getDniCliente());
            ps.setDate(4, Date.valueOf(reserva.getFechaReserva()));
            ps.setTime(5, Time.valueOf(reserva.getHoraReserva()));
            
            ps.setBoolean(6,reserva.isEstado());
            
            ps.executeUpdate();
                    
            ResultSet rs = ps.getGeneratedKeys();
            
            if (rs.next()) {
                
                JOptionPane.showMessageDialog(null, "Reserva para...: " +reserva.getNombreCliente() + " reservada con exito!");
                
            }
            
            ps.close();
            
        } catch (SQLException ex) {
          JOptionPane.showMessageDialog(null,"Error al ingresar a la tabla: " + reserva + ex.getMessage());
        }

        
        
    }
    
}
