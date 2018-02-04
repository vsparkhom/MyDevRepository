package com.perscab.model.services.options;

import com.perscab.db.AttributeConsts;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DisplayOptionsMapper {

    private static Map<String, String> INTERNET_OPTIONS_MAPPING = new HashMap<>();
    private static Map<String, String> TV_OPTIONS_MAPPING = new HashMap<>();
    private static Map<String, String> PHONE_OPTIONS_MAPPING = new HashMap<>();

    private static Map<Long, Map<String, String>> mappings = new HashMap<>();

    static {
        INTERNET_OPTIONS_MAPPING.put("downloadSpeed", "Download Speed");
        INTERNET_OPTIONS_MAPPING.put("uploadSpeed", "Upload Speed");
        INTERNET_OPTIONS_MAPPING.put("dataLimit", "Data Limit");

        TV_OPTIONS_MAPPING.put("uhdSupport", "Ultra HD Support");
        TV_OPTIONS_MAPPING.put("channelsCount", "Channels Count");

        PHONE_OPTIONS_MAPPING.put("talkLimit", "Talk Limit");
        PHONE_OPTIONS_MAPPING.put("dataLimit", "Data Limit");
        PHONE_OPTIONS_MAPPING.put("voiceMail", "Voice Mail");

        mappings.put(AttributeConsts.INTERNET_SERVICE_TYPE_ID, INTERNET_OPTIONS_MAPPING);
        mappings.put(AttributeConsts.TV_SERVICE_TYPE_ID, TV_OPTIONS_MAPPING);
        mappings.put(AttributeConsts.PHONE_SERVICE_TYPE_ID, PHONE_OPTIONS_MAPPING);
    }

    public static String getOptionDisplayName(Long serviceTypeId, String key) {

        Map<String, String> map = mappings.get(serviceTypeId);
        if (map == null) {
            return key;
        }

        String value = map.get(key);
        return value == null ? key : value;
    }
}
