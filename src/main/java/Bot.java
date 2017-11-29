
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

public class Bot extends TelegramLongPollingBot {

    private String helpString= "/createGcPost\n/createScPost\n/testLine\n/help\n";




    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Bot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    public String getBotUsername() {
        return "CrutcherBot";
    }

    @Override
    public String getBotToken() {
        return "408569487:AAHBH5CsDhqyo_6Vhv5mK51OazPjNRxD_vQ";
    }


    public void onUpdateReceived(Update update) {

        Message incomingMessage = update.getMessage();

        try {
            detectNeededReply(incomingMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    private void detectNeededReply(Message incomingMessage) throws TelegramApiException
    {
        switch (getFirstWord(incomingMessage.getText())) {
            case "/createGcPost":  new MessageSender().sendGovnocodePost(incomingMessage.getChatId().toString(), incomingMessage.getText(), this); break;
            case "/createScPost":  new MessageSender().sendSelfCreatedPost(incomingMessage.getChatId().toString(), incomingMessage.getText(), this); break;
            case "/help": getHelp(incomingMessage); break;
            case "/testLine" : new MessageSender().sendTestText(incomingMessage.getChatId().toString(), incomingMessage.getText(),this); break;
            default: uRNotRight(incomingMessage);
        }
    }





    private void getHelp (Message message) throws TelegramApiException
    {
        SendMessage helpMessage = new SendMessage();
        helpMessage.setText(helpString);
        helpMessage.setChatId(message.getChatId().toString());
        helpMessage.enableMarkdown(true);
        sendMessage(helpMessage);
    }

    private void uRNotRight (Message message) throws TelegramApiException
    {
        SendMessage helpMessage = new SendMessage();
        helpMessage.setText("u r not right");
        helpMessage.setChatId(message.getChatId().toString());
        helpMessage.enableMarkdown(true);
        sendMessage(helpMessage);
    }


    private String getFirstWord(String string)
    {
        int i=0;
        StringBuilder stringBuilder = new StringBuilder();
        try{
            while (string.charAt(i) != ' ')
            {
                stringBuilder.append(string.charAt(i));
                i++;
            }
        }
        catch (StringIndexOutOfBoundsException e)
        {
            return "/help";
        }

        if(stringBuilder.toString().equals(""))
        {
            return "Why couldn't we find first word???";
        }
        else
        {
            return stringBuilder.toString();
        }
    }

}