package controller;

import java.util.concurrent.Semaphore;

public class ThreadServidor extends Thread {
	
	private int IDThread;
	private Semaphore semaforo;
	private int calcSegInicio;
	private int calcSegFim;
	private int repeteReq;
	private int tempTransacao;
	
	public ThreadServidor(int IDThread, Semaphore semaforo, int calcSegInicio, int calcSegFim, int repeteReq, int tempTransacao) {
		this.IDThread = IDThread;
		this.semaforo = semaforo;
		this.calcSegInicio = calcSegInicio;
		this.calcSegFim = calcSegFim;
		this.repeteReq = repeteReq;
		this.tempTransacao = tempTransacao;
	}

	@Override
	public void run() {
	    for(int i = 0; i < repeteReq; i++) {
	        Calculo();
//			---------------------- Inicio Se��o Critica ----------------------
	        try {
	        	semaforo.acquire();
	        	Transacoes();
	        } catch (InterruptedException e) {
	        	e.printStackTrace();
	        } finally {
	        	semaforo.release();
	        }
//			---------------------- Fim Se��o Critica ----------------------
	    }
	}

	private void Calculo() {
		System.out.println("Thread #" + IDThread + " est� calculando");
		int temp = (int) ((Math.random() * calcSegFim) + calcSegInicio);		
		try {
			sleep(temp);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Thread #" + IDThread + " terminou os C�lculos");
	}
	
	private void Transacoes() {
		System.out.println("Thread #" + IDThread + " est� fazendo a transa��o de BD");
		try {
			sleep(tempTransacao);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Thread #"+ IDThread + " terminou a transa��o de BD");
	}
}