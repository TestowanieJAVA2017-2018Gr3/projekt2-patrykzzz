package messenger;

public interface MessageService
{

    ConnectionStatus CheckConnection(String server);

    SendingStatus Send(String server, String message) throws MalformedRecipientException;

}
