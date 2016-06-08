package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class Controller {
	@FXML
	TextField country;
	@FXML
	ListView<String> list;
	static ArrayList<String> array = new ArrayList<String>();
	static int count_w;
	static int count_n;

	public void firs(ActionEvent e) throws SQLException {
		Connection myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/lab11", "user", "user");
		java.sql.PreparedStatement mySta = myCon.prepareStatement("select * from lab11 where Country = ? and Age = ?");
		for (int i = 18; i <= 65; i++) {
			mySta.setString(1, country.getText());
			mySta.setInt(2, i);
			ResultSet myRes = mySta.executeQuery();
			// count_c++;
			while (myRes.next()) {
				int work = Integer.parseInt(myRes.getString("Work"));
				if (work == 0) {
					count_w++;
				}
				if (work == 1) {
					count_n++;
				}
			}
		}
		long proc = (100 / (count_w + count_n)) * count_w;
		System.out.println("in " + country.getText() + " - " + proc + "%");

	}

	public void second(ActionEvent e) throws SQLException {
		Connection myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/lab11", "user", "user");
		java.sql.PreparedStatement mySta = myCon.prepareStatement("select Name from lab11 where Country = ?");
		mySta.setString(1, country.getText());
		ResultSet myRes = mySta.executeQuery();
		while (myRes.next()) {
			String s = myRes.getString("Name");
			if (array.contains(s)) {
				System.err.println("repetition name!!!");

			} else {
				array.add(myRes.getString("Name"));
			}

		}

		list.getItems().addAll(array);
	}

}
