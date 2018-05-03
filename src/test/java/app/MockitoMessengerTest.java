package app;

import messenger.ConnectionStatus;
import messenger.MessageService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        Assertions.assertThat(result).isEqualTo(0);
    }
}
