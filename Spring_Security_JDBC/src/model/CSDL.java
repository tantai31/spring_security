package model;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class CSDL {
	private static JdbcTemplate jdbc;
	public static JdbcTemplate getJdbc() {
		DriverManagerDataSource dms = new DriverManagerDataSource();
		dms.setDriverClassName("com.mysql.jdbc.Driver");
		dms.setUrl("jdbc:mysql://localhost:3308/minishop");
		dms.setUsername("root");
		dms.setPassword("");
		jdbc = new JdbcTemplate(dms);
		return jdbc;
	}
	
}
