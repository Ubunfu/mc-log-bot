package com.github.ubunfu.parser;

import com.github.ubunfu.client.discord.request.Field;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;
import java.util.Set;

@AllArgsConstructor
@Data
public class ParserResponse {

    private Map<String, String> attributes;
    private Set<Field> fields;
}
