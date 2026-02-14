package com.example.tacocloud.configurationPropertyHolders;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.file.dsl.Files;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.messaging.MessageChannel;

import java.io.File;

@Configuration
public class FileWriterIntegrationConfig {

    @Bean
    public MessageChannel orderChannel() {
        return new PublishSubscribeChannel();
    }

    @Bean
    public IntegrationFlow fileWriterFlow() {
        return IntegrationFlow
                .from(MessageChannels.direct("textInChannel"))
                .channel("orderChannel")
                .<String, String>transform(t -> t.toUpperCase())
                .handle(Files
                        .outboundAdapter(new File("C:/sia5/files"))
                        .fileExistsMode(FileExistsMode.APPEND)
                        .appendNewLine(true))
                .get();
    }
}
