package app;

import messenger.ConnectionStatus;
import messenger.MalformedRecipientException;
import messenger.MessageService;
import messenger.SendingStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class MockitoMessengerTest
{
    private Messenger _target;
    private MessageService _messageService;

    @BeforeEach
    void SetUp()
    {
        _messageService = mock(MessageService.class);
        _target = new Messenger(_messageService);
    }

    @Test
    void TestConnection_ForEverythingOk_ShouldReturnZero()
    {
        //Arrange
        when(_messageService.CheckConnection(anyString())).thenReturn(ConnectionStatus.SUCCESS);

        //Act
        var result = _target.TestConnection("test");

        //Assert
        verify(_messageService).CheckConnection("test");
        assertThat(result).isEqualTo(0);
    }

    @Test
    void SendMessage_ForWrongMessage_ShouldInvocationSucceed() throws MalformedRecipientException
    {
        //Arrange
        final String serverName = "test";
        when(_messageService.Send(serverName, null)).thenThrow(MalformedRecipientException.class);

        //Act
        var result = _target.SendMessage(serverName, null);

        //Assert
        verify(_messageService).Send(serverName, null);
    }

    @Test
    void SendMessage_ForNullServer_ShouldReturnTwo() throws MalformedRecipientException
    {
        //Arrange
        when(_messageService.Send(null, "any")).thenThrow(MalformedRecipientException.class);

        //Act
        var result = _target.SendMessage(null, "any");

        //Assert
        assertThat(result).isEqualTo(2);
    }

    @Test
    void SendMessage_ForInternalServiceError_ShouldReturnOne() throws MalformedRecipientException
    {
        //Arrange
        when(_messageService.Send(anyString(), anyString())).thenReturn(SendingStatus.SENDING_ERROR);

        //Act
        var result = _target.SendMessage(anyString(), anyString());

        //Assert
        assertThat(result).isEqualTo(1);
    }

    @Test
    void SendMessage_ForEverythingOk_ShouldInvocationSucceed() throws MalformedRecipientException
    {
        //Arrange
        final String serverName = "Test", message = "Message";
        when(_messageService.Send(serverName, message)).thenReturn(SendingStatus.SENT);

        //Act
        var result = _target.SendMessage(serverName, message);

        //Assert
        verify(_messageService, atLeastOnce()).Send(serverName, message);
    }

    @Test
    void TestConnection_ForInternalServiceMalfunction_ShouldReturnOne()
    {
        //Arrange
        when(_messageService.CheckConnection(anyString())).thenReturn(ConnectionStatus.FAILURE);

        //Act
        var result = _target.TestConnection(anyString());

        //Assert
        Assertions.assertTrue(result == 1);
    }
}
