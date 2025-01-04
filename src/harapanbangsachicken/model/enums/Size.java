package harapanbangsachicken.model.enums;

public enum Size {
    SMALL, MEDIUM, LARGE, EXTRALARGE;

    public static boolean isSize(String size) {
        for (Size s : Size.values()) {
            if (s.name().equalsIgnoreCase(size)) {
                return true;
            }
        }
        return false;
    }
}
