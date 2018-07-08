package devy.kave.server.controller;

import devy.kave.server.db.model.Contents;
import devy.kave.server.db.model.Deck;
import devy.kave.server.db.model.User;
import devy.kave.server.db.service.ContentsService;
import devy.kave.server.db.service.DeckService;
import devy.kave.server.db.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class DeckController {

    private final Logger logger = LoggerFactory.getLogger(DeckController.class);

    @Autowired
    private DeckService deckService;

    @GetMapping("/deck/add")
    public String add(Principal principal, String contentsNo) {
        boolean isAdded = deckService.add(principal.getName(), contentsNo);
        if(isAdded) {
            logger.info("added Deck userName " + principal.getName() + ", contentsNo " + contentsNo);
        }
        return "redirect:/contents?contentsNo=" + contentsNo;
    }

    @GetMapping("/deck/remove")
    public String remove(Principal principal, String contentsNo, HttpServletRequest request, Model model) {
        Contents deckContents = deckService.getContents(contentsNo);
        model.addAttribute("userName", principal.getName());
        model.addAttribute("deckContents", deckContents);
        model.addAttribute("referer", request.getHeader("referer"));

        return "deck-remove";
    }

    @PostMapping("/deck/remove")
    public String remove(Principal principal, String contentsNo) {
        Deck remove = deckService.remove(principal.getName(), contentsNo);
        logger.info("removed " + remove.toString());
        return "redirect:/index";
    }

}
