package classes;

import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class SpringTelegramBot extends TelegramLongPollingBot{
	private static final String TOKEN  = "5165964950:AAHZyEXRQkHqp6Vbh9AKeVCv9iauwJGLCN8";
	private static final String USERNAME  = "spring_mvc_webapp_bot";
	public SpringTelegramBot(DefaultBotOptions options) {
		// TODO Auto-generated constructor stub
		super(options);
	}

	@Override
	public void onUpdateReceived(Update update) {
		// TODO Auto-generated method stub
		if(update.getMessage()!=null && update.getMessage().hasText()) {
			String chatId = update.getMessage().getChatId().toString();
			try {
				execute(new SendMessage(chatId,"Привет"));
			}
			catch(TelegramApiException exc){
				exc.printStackTrace();
			}
		}
	}

	@Override
	public String getBotUsername() {
		// TODO Auto-generated method stub
		return USERNAME;
	}

	@Override
	public String getBotToken() {
		// TODO Auto-generated method stub
		return TOKEN;
	}

}
