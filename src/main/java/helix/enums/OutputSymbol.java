package helix.enums;

/**
 * Represents various output symbols used for display purposes.
 */
public enum OutputSymbol {
    CHECKMARK("✔"),
    WARNING("⚠️"),
    HELIX("🤖"),
    USER("👤"),
    CALENDAR("📅"),
    CLOCK("🕒"),
    BIN("🗑️"),
    CLIPBOARD("📋"),
    NOTE("📝"),
    WRENCH("🛠️"),
    HANDWAVE("👋"),
    SPARKLE("✨"),
    CHECK("✅"),
    CROSS("❌"),
    FOLDER("🗂️");

    private final String symbol;

    /**
     * Constructs an OutputSymbol with the specified symbol.
     *
     * @param symbol the symbol representation.
     */
    OutputSymbol(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Returns the symbol representation.
     *
     * @return the symbol.
     */
    public String getSymbol() {
        return this.symbol;
    }
}
