package app;

import messenger.ConnectionStatus;
import messenger.MessageService;
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
        messageService = createMock(MessageService.class);
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
}