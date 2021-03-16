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
//			---------------------- Inicio Seção Critica ----------------------
	        try {
	        	semaforo.acquire();
	        	Transacoes();
	        } catch (InterruptedException e) {
	        	e.printStackTrace();
	        } finally {
	        	semaforo.release();
	        }
//			---------------------- Fim Seção Critica ----------------------
	    }
	}

	private void Calculo() {
		System.out.println("Thread #" + IDThread + " está calculando");
		int temp = (int) ((Math.random() * calcSegFim) + calcSegInicio);		
		try {
			sleep(temp);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Thread #" + IDThread + " terminou os Cálculos");
	}
	
	private void Transacoes() {
		System.out.println("Thread #" + IDThread + " está fazendo a transação de BD");
		try {
			sleep(tempTransacao);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Thread #"+ IDThread + " terminou a transação de BD");
	}
}