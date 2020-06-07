package com.github.ubunfu.mclogbot.parser;

import com.github.ubunfu.mclogbot.client.discord.request.Field;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractLogParser implements LogParser {
    protected Set<Field> fields = new HashSet<>();
}
