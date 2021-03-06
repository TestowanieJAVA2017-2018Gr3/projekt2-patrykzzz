package app.fakes;

import messenger.ConnectionStatus;
import messenger.MalformedRecipientException;
import messenger.MessageService;
import messenger.SendingStatus;

public class FakeMessageService implements MessageService
{
    @Override
    public ConnectionStatus CheckConnection(String server)
    {
        if (server == "test")
        {
            return ConnectionStatus.SUCCESS;
        }
        return ConnectionStatus.FAILURE;
    }

    @Override
    public SendingStatus Send(String server, String message) throws MalformedRecipientException
    {
        if (server == "problems")
        {
            return SendingStatus.SENDING_ERROR;
        }
        if (server == "test" && !message.isEmpty())
        {
            return SendingStatus.SENT;
        } else
        {
            throw new MalformedRecipientException();
        }
    }
}
