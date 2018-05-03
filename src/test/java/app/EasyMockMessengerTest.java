package app;

import messenger.ConnectionStatus;
import messenger.MalformedRecipientException;
import messenger.MessageService;
import messenger.SendingStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.easymock.EasyMock.*;

public class EasyMockMessengerTest
{
    private MessageService messageService;
    private Messenger _target;

    @BeforeEach
    void SetUp()
    {
        messageService = createNiceMock(MessageService.class);
        _target = new Messenger(messageService);
    }

    @Test
    void TestConnection_ForEverythingOk_ShouldReturnZero()
    {
        //Arrange
        expect(messageService.CheckConnection("test")).andReturn(ConnectionStatus.SUCCESS);
        replay(messageService);

        //Act
        var result = _target.TestConnection("test");

        //Assert
        assertThat(result).isEqualTo(0);
    }

    @Test
    void TestConnection_ForEverythingOk_ShouldServiceMethodBeCalledOnce()
    {
        //Arrange
        expect(messageService.CheckConnection(isA(String.class)))
                .andReturn(ConnectionStatus.SUCCESS)
                .times(1);
        replay(messageService);

        //Act
        _target.TestConnection("test");

        //Assert
        verify(messageService);
    }

    @Test
    void TestConnection_ForWrongServerName_ShouldReturnOne()
    {
        //Arrange
        expect(messageService.CheckConnection(anyString())).andReturn(ConnectionStatus.FAILURE);
        replay(messageService);

        //Act
        var result = _target.TestConnection(anyString());

        //Assert
        assertThat(result).isOne();
    }

    @Test
    void SendMessage_ForEverythingOk_ShouldReturnZero() throws MalformedRecipientException
    {
        //Arrange
        expect(messageService.Send("test", "message")).andStubReturn(SendingStatus.SENT);
        replay(messageService);

        //Act
        var result = _target.SendMessage("test", "message");

        //Assert
        assertThat(result).isEqualTo(0);
    }

    @Test
    void SendMessage_ForNullServer_ShouldNotThrowException() throws MalformedRecipientException
    {
        //Arrange
        expect(messageService.Send(null, "Test")).andThrow(new MalformedRecipientException());
        replay(messageService);

        //Act
        var result = _target.SendMessage(null, "Test");

        //Assert
        assertThat(result).isEqualTo(2);
    }

    @Test
    void SendMessage_ForSomethingWrong_ShouldReturnOne() throws MalformedRecipientException
    {
        //Arrange
        expect(messageService.Send(anyString(), anyString())).andReturn(SendingStatus.SENDING_ERROR).once();
        replay(messageService);

        //Act
        var result = _target.SendMessage("test", "Test");

        //Assert
        assertThat(result).isOne();
    }
}