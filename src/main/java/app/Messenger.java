package app;

import messenger.MessageService;

public class Messenger {

	private MessageService ms;

	public Messenger(MessageService ms) {
		this.ms = ms;
	}

	/*Tymczasowo -> Pozniej to zniknie */
	public Messenger() {
		// TODO Auto-generated constructor stub
	}

	public int testConnection(String server) {
		return 1;
	}

	public int sendMessage(String server, String message) {

		int result = -1;
		return result;
	}
}
