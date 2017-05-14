package mainpackage.personparse;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import javafx.concurrent.Task;
import mainpackage.utils.Transliteration;
import mainpackage.view.login.AuthorizationOnVkCom;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ControllerParsePerson extends Task<Void> {
    private PersonParserLogic parser = new PersonParserLogic();
    private volatile int count = 0;

    private String vkTransliterat;

    public void setPersonLinkInParser(String personLink) throws ClientException, ApiException {
        String vkFirstName = AuthorizationOnVkCom.getVkApiClient().users().get(AuthorizationOnVkCom.getActor()).userIds(personLink).execute().get(0).getFirstName();
        String vkLastName = AuthorizationOnVkCom.getVkApiClient().users().get(AuthorizationOnVkCom.getActor()).userIds(personLink).execute().get(0).getLastName();
        vkTransliterat = Transliteration.transliterate(vkLastName + "_" + vkFirstName);

        try {
            parser.setPersonLink(Integer.valueOf(personLink.split("id", 2)[1]));
        } catch (Exception e) {
            int nowPersonLink = AuthorizationOnVkCom.getVkApiClient().utils().resolveScreenName(AuthorizationOnVkCom.getActor(), personLink).execute().getObjectId();
            parser.setPersonLink(nowPersonLink);
        }
    }

    public String getFileName() throws IOException {
        return String.valueOf(vkTransliterat + ".txt");
    }

    @Override
    protected Void call() throws Exception {
        ArrayList<String> vkid = parser.getLinksList();
        int lengthVKID = vkid.size();
        try (FileWriter fw = new FileWriter(new File(getFileName()))) {
            for (String id_vk : vkid) {
                fw.write(id_vk);
                count++;
            }
            updateMessage(String.valueOf(count));
            updateProgress(count, lengthVKID);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}