package com.helltractor.demo.messaging;

public interface Messaging {

    enum Topic {

        TOPIC_ONE(1),
        TOPIC_TWO(2),
        TOPIC_THREE(3);

        final int concurrency;

        Topic(int concurrency) {
            this.concurrency = concurrency;
        }

        public int getConcurrency() {
            return this.concurrency;
        }

        public int getPartitions() {
            return this.concurrency;
        }
    }
}
