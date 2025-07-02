package main;

import java.awt.EventQueue;

import controllers.MainController;
import views.MenuView;

public class Main {

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			public void run() {

				try {
					MainController.load();

					MenuView frame = new MenuView();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}