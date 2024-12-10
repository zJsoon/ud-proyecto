package main;

import components.Users;
import window.*;
import window.logged.admin.WindowAdmin;

public class Main {
	public static void main(String[] args) {
		new WindowAdmin(null, new Users("admin", "admin", true));
	}
}
