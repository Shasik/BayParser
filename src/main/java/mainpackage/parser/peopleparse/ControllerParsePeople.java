//package mainpackage.parser.peopleparse;
//
//import com.vk.api.sdk.client.VkApiClient;
//import com.vk.api.sdk.client.actors.UserActor;
//import com.vk.api.sdk.exceptions.ApiException;
//import com.vk.api.sdk.exceptions.ClientException;
//import javafx.concurrent.Task;
//import mainpackage.parser.groupparse.ControllerParseGroup;
//import mainpackage.parser.personparse.ControllerParsePerson;
//import mainpackage.view.login.AuthorizationOnVkCom;
//
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.ArrayList;
//
//public class ControllerParsePeople extends Task<Void> {
//    private PeopleParserLogic parser = new PeopleParserLogic();
//
//    private ControllerParsePerson controllerParsePerson = new ControllerParsePerson();
//    private ControllerParseGroup controllerParseGroup = new ControllerParseGroup();
//
//    private String fileName;
//
//    private UserActor actor = AuthorizationOnVkCom.getActor();
//    private VkApiClient vkApiClient = AuthorizationOnVkCom.getVkApiClient();
//
//    public String getFileNameBegin() {
//        return fileName;
//    }
//
//    public void setFileNameBegin(String fileName) {
//        this.fileName = fileName;
//    }
//
//    public String getTypeScreenName(String link) throws ClientException, ApiException {
//        return vkApiClient.utils().resolveScreenName(actor, link).execute().getType().getValue();
//    }
//
//    @Override
//    protected Void call() throws Exception {
//        int lengthVKID
//        System.out.println(lengthVKID);
//            try (FileWriter fw = new FileWriter(new File(getFileName()))) {
//                while (count < lengthVKID) {
//                    ArrayList<String> vkid = parser.getLinksList();
//                    for (String id_vk : vkid) {
//                        fw.write(id_vk);
//                        count++;
//                    }
//                    updateMessage(String.valueOf(count));
//                    updateProgress(count, lengthVKID);
//                    if (count % 1000 == 0) {
//                        parser.setOffset(offset += 1000);
//                        Thread.sleep(221);
//                    }
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        return null;
//    }
//}