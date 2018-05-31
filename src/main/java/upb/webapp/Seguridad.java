package upb.webapp;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class Seguridad {

    protected static String generateHash(String name, String password){
        String hash = "";
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(name.getBytes(StandardCharsets.UTF_8));
            StringBuilder str = new StringBuilder();
            for(int i=0; i< bytes.length ;i++){
                str.append(Integer.toString((bytes[i] & 0xff) + 0x100,16).substring(1));
            }
            hash = str.toString();
        }catch (Exception e){
            System.err.println("aaaaaaaaaaaoaaah!");
        }
        return hash;
    }
    protected static boolean passwordIsSecure(String nombre, String correo, String password){
        boolean res = (null != password)
                && (password.matches(".*[A-Z]+.*")
                && password.matches(".*[a-z]+.*")
                && password.matches(".*[0-9]+.*")
                && (password.length() >= 8)
                && !password.matches(".*" + nombre + "+.*")
                && !password.matches(".*" + correo + "+.*"));
        return res;
    }
}
