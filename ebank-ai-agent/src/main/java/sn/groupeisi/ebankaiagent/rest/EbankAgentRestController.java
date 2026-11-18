package sn.groupeisi.ebankaiagent.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sn.groupeisi.ebankaiagent.agents.EbankAiAgent;

import java.awt.*;

@RestController
public class EbankAgentRestController  {
    private EbankAiAgent  eBankAiAgent;

    public EbankAgentRestController(EbankAiAgent eBankAiAgent){
        this.eBankAiAgent = eBankAiAgent;
    }

    @GetMapping(value = "/chat", produces = MediaType.TEXT_PLAIN_VALUE)
    public String chat(String query){
       return eBankAiAgent.chat(query);
    }
}
