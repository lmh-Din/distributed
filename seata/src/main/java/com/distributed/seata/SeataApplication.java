package com.distributed.seata;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class,
		JdbcTemplateAutoConfiguration.class})
@Slf4j
public class SeataApplication implements CommandLineRunner {

	@Autowired
	private DataSource storageDataSource;

	public static void main(String[] args) {
		SpringApplication.run(SeataApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		log.info("seata is running.....");
		storageQuery();
	}

	private void storageQuery() throws SQLException {
		Connection connection = storageDataSource.getConnection();
		PreparedStatement statement = connection.prepareStatement("SELECT * FROM storage_tbl");
		ResultSet resultSet = statement.executeQuery();
		log.info("resultSet:[{}]", resultSet.toString());
		statement.close();
	}

}