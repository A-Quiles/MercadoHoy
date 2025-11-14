package com.mercadohoy.mercadohoybackend.news.dto;

public class NewsDtos {

    public static class GenerateNewsRequest {
        private String topic;
        private String language; // "es", "en", etc.
        private String tone;     // "neutral", "educativo", etc.
        private int maxWords;

        public String getTopic() { return topic; }
        public void setTopic(String topic) { this.topic = topic; }

        public String getLanguage() { return language; }
        public void setLanguage(String language) { this.language = language; }

        public String getTone() { return tone; }
        public void setTone(String tone) { this.tone = tone; }

        public int getMaxWords() { return maxWords; }
        public void setMaxWords(int maxWords) { this.maxWords = maxWords; }
    }

    public static class GenerateNewsResponse {
        private String content;

        public GenerateNewsResponse() {
        }

        public GenerateNewsResponse(String content) {
            this.content = content;
        }

        public String getContent() { return content; }
        public void setContent(String content) { this.content = content; }
    }
}
