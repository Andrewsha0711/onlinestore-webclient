package classes;

public class TelegramBotMembership {
	private String telegramChatId;
	private String telegramUsername;

	public String getChatId() {
		return this.telegramChatId;
	}

	public String getUsername() {
		return this.telegramUsername;
	}

	public void setChatId(String value) {
		this.telegramChatId = value;
	}

	public void setUsername(String value) {
		this.telegramUsername = value;
	}
}
