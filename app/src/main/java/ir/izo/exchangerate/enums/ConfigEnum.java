package ir.izo.exchangerate.enums;

/**
 * Every config in shared preference should be declared here.
 */
public enum ConfigEnum {
	NAME("client.name", null),;

	private String key;
	private String defaultValue;

	ConfigEnum(String key, String defaultValue) {
		this.key = key;
		this.defaultValue = defaultValue;
	}

	public String getKey() {
		return key;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

}
