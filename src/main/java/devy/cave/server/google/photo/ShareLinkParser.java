package devy.cave.server.google.photo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;

@Component
public class ShareLinkParser {

    private static final String ATTR_CONTENTS = "content";
    private static final int TIMEOUT_MILLIS = 3 * 1000;

    public static ShareLink parse(String shareLinkUrl) throws IOException {
        ShareLink shareLink = new ShareLink();

        Document document = Jsoup.parse(new URL(shareLinkUrl), TIMEOUT_MILLIS);
        String dataDestination = document.selectFirst("div[data-destination]").attr("data-destination");
        document = Jsoup.connect(dataDestination).get();
        Element ogImage = document.selectFirst("meta[property=og:image]");
        Element ogVideo = document.selectFirst("meta[property=og:video]");
        Element ogVideoSecureUrl = document.selectFirst("meta[property=og:video:secure_url]");

        if(ogImage != null) {
            shareLink.setOgImage(ogImage.attr(ATTR_CONTENTS));
        }

        if(ogVideo != null) {
            shareLink.setOgVideo(ogVideo.attr(ATTR_CONTENTS));
        }

        if(ogVideoSecureUrl != null) {
            shareLink.setOgSecretVideo(ogVideoSecureUrl.attr(ATTR_CONTENTS));
        }

        return shareLink;
    }

}