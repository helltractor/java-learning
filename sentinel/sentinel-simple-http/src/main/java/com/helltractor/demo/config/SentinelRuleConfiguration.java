package com.helltractor.demo.config;

import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SentinelRuleConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(SentinelRuleConfiguration.class);

    @PostConstruct
    public void init() {
        logger.info("Load Sentinel Rules start!");
        // TODO: Load Sentinel Rules
        List<FlowRule> flowRules = new ArrayList<FlowRule>();
        FlowRule flowRule = new FlowRule();
        flowRule.setResource("GET:https://localhost:18080/hello");
        flowRule.setCount(1);
        flowRules.add(flowRule);
        FlowRuleManager.loadRules(flowRules);
        logger.info("Load Sentinel Rules end!");
    }

}
