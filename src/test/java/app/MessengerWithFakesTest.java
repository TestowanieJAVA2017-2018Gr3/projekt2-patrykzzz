package app;

import app.fakes.FakeMessageService;
import messenger.MessageService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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
        assertEquals(result, 0);
    }

    @Test
    public void TestConncetion_ForWrongServerName_ShouldReturnOne()
    {
        //Arrange & Act
        var result = _target.TestConnection("wrong name");

        //Assert
        assertEquals(result, 1);
    }
}