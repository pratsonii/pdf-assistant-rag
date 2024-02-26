package com.programming.techie.pdfassistant;

import dev.langchain4j.chain.ConversationalRetrievalChain;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
@Slf4j
public class ChatController {

	@Autowired
    private ConversationalRetrievalChain conversationalRetrievalChain;

    @PostMapping
    public String chatWithPdf(@RequestBody String text) {
        var answer = conversationalRetrievalChain.execute(text);
        return answer;
    }
    
    @GetMapping
    public String chatWithPdf() {
        var answer = conversationalRetrievalChain.execute("When did Pratik born?");
        return answer;
    }
}
