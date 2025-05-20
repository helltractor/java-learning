package com.helltractor.demo.redis;

public interface RedisCache {

    interface Topic {

        String TOPIC_ONE = "__topic_one__";

        String TOPIC_TWO = "__topic_two__";
    }

    interface Key {

        String KEY_ONE = "__key_one__";

        String KEY_TWO = "__key_two__";

        String KEY_THREE = "__key_three__";
    }
}
