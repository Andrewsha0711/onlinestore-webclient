package classes;

import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class TelegramBotController {
	private String TOKEN = "5165964950:AAHZyEXRQkHqp6Vbh9AKeVCv9iauwJGLCN8";

	public boolean sendMessage(String chatId, String message) {
		String url = "https://api.telegram.org/bot" + this.TOKEN + "/sendMessage?chat_id=" + chatId
				+ "&parse_mod=html&text=" + message;
		RestTemplate restTemplate = new RestTemplate();
		JSONParser jsonParser = new JSONParser();
		try {
			JSONObject json = (JSONObject) (jsonParser.parse(restTemplate.getForObject(url, String.class)));
			String okState = json.get("ok").toString();
			if (okState.equals("true")) {
				return true;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		return false;
	}

	public TelegramBotMembership getTelegramBotMembership(String username) {
		String url = "https://api.telegram.org/bot" + this.TOKEN + "/getUpdates";
		RestTemplate restTemplate = new RestTemplate();
		JSONParser jsonParser = new JSONParser();
		try {
			JSONObject json = (JSONObject) (jsonParser.parse(restTemplate.getForObject(url, String.class)));
			String okState = json.get("ok").toString();
			if (okState.equals("true")) {
				JSONArray jsonArray = (JSONArray) json.get("result");
				for (int i = 0; i < jsonArray.size(); i++) {
					JSONObject message = (JSONObject) ((JSONObject) jsonArray.get(i)).get("message");
					JSONObject from = (JSONObject) message.get("from");
					if (from.get("username").equals(username)) {
						TelegramBotMembership member = new TelegramBotMembership();
						member.setChatId(from.get("id").toString());
						member.setUsername(from.get("username").toString());
						return member;
					}
				}
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		return null;
	}
}
