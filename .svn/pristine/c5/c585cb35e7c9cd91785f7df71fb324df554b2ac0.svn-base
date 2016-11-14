package net.tatans.android.core.manager;

import java.util.Map;

import net.tatans.android.common.email.EmailSender;
import net.tatans.android.common.email.MessageTemplate;
import net.tatans.android.core.entity.Config;
import net.tatans.android.core.entity.Config.ConfigLogin;

public interface ConfigMng {
	public Map<String, String> getMap();

	public ConfigLogin getConfigLogin();

	public EmailSender getEmailSender();

	public MessageTemplate getForgotPasswordMessageTemplate();
	
	public MessageTemplate getRegisterMessageTemplate();

	public String getValue(String id);

	public void updateOrSave(Map<String, String> map);

	public Config updateOrSave(String key, String value);

	public Config deleteById(String id);

	public Config[] deleteByIds(String[] ids);
}