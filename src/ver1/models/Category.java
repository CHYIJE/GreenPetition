package ver1.models;

public enum Category {
	facility("facility"), teacher("teacher");

	private final String value;

	Category(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static Category fromValue(String value) {
		for (Category category : Category.values()) {
			if (category.value.equalsIgnoreCase(value)) {
				return category;
			}
		}
		throw new IllegalArgumentException("Unknown enum value: " + value);
	}
}