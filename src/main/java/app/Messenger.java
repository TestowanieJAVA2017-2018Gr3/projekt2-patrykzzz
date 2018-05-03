package app;

import messenger.ConnectionStatus;
import messenger.MalformedRecipientException;
import messenger.MessageService;
import messenger.SendingStatus;

public class Messenger
{

    private MessageService _messageService;

    public Messenger(MessageService ms)
    {
        this._messageService = ms;
    }

    public int TestConnection(String server)
    {
        return _messageService.CheckConnection(server) == ConnectionStatus.FAILURE ? 1 : 0;
    }

    public int SendMessage(String server, String message)
    {
        try
        {
            var status = _messageService.Send(server, message);
            return status == SendingStatus.SENT ? 0 : 1;
        }
        catch (MalformedRecipientException e)
        {
            return 2;
        }
    }
}
