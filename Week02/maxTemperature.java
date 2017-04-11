
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.*;


public class maxTemperature {
	

	Class.forName("com.mysql.jdbc.Driver");
	String URL = "jdbc:mysql://localhost/week2";
    String USERNAME = "root";
    String PASSWORD = "password";
    Connection connection = null;

	PreparedStatement insertData = null;
	PreparedStatement selectMaxTemp = null;
	ResultSet resultSet = null;

	public static void main(String [] args) throws IOException{

	try {
		connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        insertData = connection.prepareStatement(
        	"insert into week2.temp(no_mention, us_weather_id, wban, observation_date, observation_hour, unknown,latitute, longitude, data_of,elevation, phf1, phf2, wind_direction,quality_code1,
            phf3, phf4,phf5,phf6,phf7,phf8, phf9,phf10,vd, quality_code2, phf11,phf12, air_temp, quality_code3,dew_point, quality_code4, atomospheric_pressure, quality_code5)"
             + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        String l1;
		while((l1  = br.readLine()) != null){

            String no_mention = l1.substring(0,3);
            String us_weather_id = l1.substring(4,9);
            String wban= l1.substring(10,14);
		    String observation_date = l1.substring(15,22);
		    String observation_hour = l1.substring(23,26);
            String unknown = l1.substring(26,27); 
		    String latitude = l1.substring(28,33);
            String longitude = l1.substring(34,40);
            String data_of = l1.substring(41,45); 
	    	String elevation = l1.substring(46,50);
            String phf1 = l1.substring(51,55);
            String phf2 = l1.substring(56,59);
     		String wind_direction = l1.substring(60,62);
            String quality_code1 = l1.substring(62,63); 
     		String phf3 = l1.substring(63,64); 
            String phf4 = l1.substring(65,68);
            String phf5 = l1.substring(68,69); 
            String vd= l1.substring(70,74);
            String quality_code2 = l1.substring(74,75);               
            String phf11 = l1.substring(75,76); 
            String phf12 = l1.substring(76,77);
            String qc3 = l1.substring(83,84); 
            String phf11 = l1.substring(84,85);
            String phf12 = l1.substring(85,86);   
            String air_temp = l1.substring(87,91);
		    String quality_code3 = l1.substring(91,92); 
		    String dew_point = l1.substring(93,97);   
            String quality_code4 = l1.substring(97,98); 
		    String atomospheric_pressure= l1.substring(99,103);   
            String quality_code5 = l1.substring(103,104);

            insertData.setInt(1,no_mention);
            insertData.setInt(2,us_weather_id);
            insertData.setInt(3,wban);
		    insertData.setInt(4,observation_date);
		    insertData.setInt(5,observation_hour);
            insertData.setInt(6,unknown); 
		    insertData.setInt(7,latitude);
            insertData.setInt(8,longitude);
            insertData.setInt(9,data_of); 
	    	insertData.setInt(10,elevation);
            insertData.setInt(11,phf1);
            insertData.setInt(12,phf2);
     		insertData.setInt(13,wind_direction);
            insertData.setInt(14,quality_code1); 
     		insertData.setInt(15,phf3); 
            insertData.setInt(16,phf4);
            insertData.setInt(17,phf5); 
            insertData.setInt(18,vd);
            insertData.setInt(19,quality_code2);
            insertData.setInt(20,phf11);
            insertData.setInt(21,phf12);
            insertData.setInt(22,qc3);
            insertData.setInt(23,phf11);
            insertData.setInt(24,phf12);
            insertData.setInt(25,air_temp);
            insertData.setInt(26,quality_code3);
            insertData.setInt(27,dew_point);
            insertData.setInt(28,quality_code4); 
            insertData.setInt(29,dew_point); 
            insertData.setInt(30,atomospheric_pressure);
            insertData.setInt(31,quality_code5);

            insertData.executeQuery();                                                  
		}
		selectMaxTemp = connection.prepareStatement(
                           "SELECT Max(air_temp), password FROM users");
		resultSet = selectMaxTemp.executeQuery();

    } catch (SQLException e){
        e.printStackTrace();
       }

       System.out.println(resultSet);



}
}
  		
