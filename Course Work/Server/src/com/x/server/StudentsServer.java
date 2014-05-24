package com.x.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class StudentsServer {
	
	private static ServerSocket server;
	private static Connection databaseConnection;
	
	private static void SetupServer() {
		try {
			server = new ServerSocket(1234);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public static final String DATABASE_USERNAME = "root";
	public static final String DATABASE_PASSWORD = "velizar1";
	public static final String DATABASE_NAME = "kursova";
	
	
	private static void SetupDatabase() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			databaseConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + DATABASE_NAME, DATABASE_USERNAME, DATABASE_PASSWORD);
			System.out.println("successfully connected to " + DATABASE_NAME);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			Statement st = databaseConnection.createStatement();
			st.executeUpdate("insert into school_subjects values(1, 'Matematika')");
			st.executeUpdate("insert into school_subjects values(2, 'Fizika')");
			st.executeUpdate("insert into school_subjects values(3, 'Pluvane')");
			st.executeUpdate("insert into school_subjects values(4, 'Informatika')");
		} catch (Exception e) {
		}
	}
	
	private static List<StudentClient> clients = new ArrayList<StudentClient>();
	
	public static synchronized void removeClient(StudentClient client) {
		clients.remove(client);
	}
	
	public static void main(String[] args) {
		SetupServer();
		SetupDatabase();
		
		System.out.println("waiting for connections..");
		while(true) {
			System.out.println();
			try {
				Socket socket = server.accept();
				System.out.println("a client cpnnected");
				StudentClient studentClient = new StudentClient(socket,
						databaseConnection.createStatement()
						);
				clients.add(studentClient);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
