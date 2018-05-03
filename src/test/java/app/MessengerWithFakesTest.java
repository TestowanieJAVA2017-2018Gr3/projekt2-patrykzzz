package app;

import app.fakes.FakeMessageService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MessengerWithFakesTest
{
    private static Messenger _target;

    @BeforeAll
    public static void SetUp()
    {
        _target = new Messenger(new FakeMessageService());
    }

    @Test
    public void TestConnection_ForEverythingOk_ShouldReturnZero()
    {
        //Arrange & Act
        var result = _target.TestConnection("test");

        //Assert
        assertEquals(0,result);
    }

    @Test
    public void TestConnection_ForWrongServerName_ShouldReturnOne()
    {
        //Arrange & Act
        var result = _target.TestConnection("wrong name");

        //Assert
        assertEquals(1, result);
    }

    @Test
    public void SendMessage_ForEverythingOk_ShouldReturnZero()
    {
        //Arrange & Act
        var result = _target.SendMessage("test", "Message");

        //Assert
        assertThat(result).isEqualTo(0);
    }

    @Test
    public void SendMessage_ForProblemsWithSending_ShouldReturnOne()
    {
        //Arrange & Act
        var result = _target.SendMessage("problems", "test");

        //Assert
        assertThat(result).isEqualTo(1);
    }

    @Test
    public void SendMessage_ForWrongServer_ShouldReturnTwo()
    {
        //Arrange & Act
        var result = _target.SendMessage("tests", "message");

        //Assert
        assertThat(result).isEqualTo(2);
    }

    @Test
    public void SendMessage_ForWrongMessage_ShouldReturnTwo()
    {
        //Arrange & Act
        var result = _target.SendMessage("tests", "");

        //Assert
        assertThat(result).isEqualTo(2);
    }
}