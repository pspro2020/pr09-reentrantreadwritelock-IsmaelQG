package code;

import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		Almacen almacen = new Almacen();
		Thread[] consultar = new Thread[3];
		Thread añadir = new Thread(new Añadir(almacen), "Añadir");
		añadir.start();
		for(int i = 0; i < 3; i++) {
			consultar[i] = new Thread(new Consultar(almacen, i+1), "Consultor " + i+1);
			consultar[i].start();
		}
		
		TimeUnit.SECONDS.sleep(4);
		añadir.interrupt();
		for(int i = 0; i < 3; i++) {
			consultar[i].interrupt();
		}
		añadir.join();
		for(int i = 0; i < 3; i++) {
			consultar[i].join();
		}
	}

}
