package service_center.controller;

import org.springframework.context.support.*;

import service_center.dao.*;
import service_center.interfaces.*;

public class Appl {

	public static void main(String[] args) {

		AbstractApplicationContext ctx = new FileSystemXmlApplicationContext("beans.xml");
		IServiceCenter controller = (IServiceCenter) ctx.getBean("database");

		ctx.close();
	}

}
