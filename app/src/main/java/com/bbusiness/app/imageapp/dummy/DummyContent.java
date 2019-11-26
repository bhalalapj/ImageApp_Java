package com.bbusiness.app.imageapp.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    private static final Map<String, String> CATEGORIES = new HashMap<>();


    static {
        CATEGORIES.put("wallpapers", "Wallpapers");
        CATEGORIES.put("textures_patterns", "Textures & Patterns");
        CATEGORIES.put("nature", "Nature");
        CATEGORIES.put("current_events", "Current Events");
        CATEGORIES.put("architecture", "Architecture");
        CATEGORIES.put("business_work", "Business & Work");
        CATEGORIES.put("film", "Film");
        CATEGORIES.put("animals", "Animals");
        CATEGORIES.put("travel", "Travel");
        CATEGORIES.put("fashion", "Fashion");
        CATEGORIES.put("food_drink", "Food & Drink");
        CATEGORIES.put("spirituality", "Spirituality");
        CATEGORIES.put("experimental", "Experimental");
        CATEGORIES.put("art_culture", "Arts & Culture");

        // Add some sample items.
        for (Map.Entry<String, String> cat : CATEGORIES.entrySet()) {
            addItem(createDummyItem(cat));
        }
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static DummyItem createDummyItem(Map.Entry<String, String> category) {
        return new DummyItem(String.valueOf(category.getKey()), category.getValue(), makeDetails(category.getValue()));
    }

    private static String makeDetails(String description) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(description);
        builder.append("\nMore details information here.");
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public final String id;
        public final String content;
        public final String details;

        public DummyItem(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
