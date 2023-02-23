package com.clevertec.clevertectesttaskrest.jsonlib.joiner;

public class JsonDataJoiner {
    private final StringBuilder content;
    private final JoiningSigns joiningSigns;

    public JsonDataJoiner(JoiningSigns joiningSigns) {
        this.content = new StringBuilder();
        this.joiningSigns = joiningSigns;
    }

    public JsonDataJoiner join(String string) {
        content.append(string);
        return this;
    }

    public JsonDataJoiner joinWithDelimiter(String string) {
        content.append(string);
        joinDelimiter();
        return this;
    }

    public StringBuilder getResult() {
        joinPrefix();
        joinSuffix();
        return content;
    }

    private void joinPrefix() {
        content.insert(0, joiningSigns.getPrefix());
    }

    private void joinDelimiter() {
        if (content.length() > 0) {
            content.append(joiningSigns.getDivider());
        }
    }

    private void joinSuffix() {
        content.append(joiningSigns.getSuffix());
    }
}
