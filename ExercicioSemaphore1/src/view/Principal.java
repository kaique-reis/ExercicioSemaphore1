package view;

import java.util.concurrent.Semaphore;

import controller.ThreadServidor;

public class Principal {

	public static void main(String[] args) {

		Semaphore semaforo = new Semaphore(1);
	    int calcSegInicio = 0;
	    int calcSegFim = 0;
	    int tempTransacao = 0;
	    int repeteReq = 0;

		for (int IDThread = 1; IDThread < 22; IDThread++) {
			if (IDThread % 3 == 0) {
				repeteReq = 3;
				calcSegInicio = 1000;
				calcSegFim = 2000;
				tempTransacao = 1500;
			} else if (IDThread % 3 == 1) {
				repeteReq = 2;
				calcSegInicio = 200;
				calcSegFim = 1000;
				tempTransacao = 1000;
			} else if (IDThread % 3 == 2) {
				repeteReq = 3;
				calcSegInicio = 500;
				calcSegFim = 1500;
				tempTransacao = 1500;
			}
			
			ThreadServidor threadServidor = new ThreadServidor(IDThread, semaforo, calcSegInicio, calcSegFim, repeteReq, tempTransacao);
			threadServidor.start();
		}
	}
}