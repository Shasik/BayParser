package mainpackage.groupparse;

import javafx.concurrent.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ControllerParseGroup extends Task<Void> {
    private GroupParserLogic parser = new GroupParserLogic();
    private volatile int count = 0;

    private Integer offset = 0;

    public void setPublicNameInParser(String publicName) {
        parser.setGroupLink(publicName.split("/", 2)[1]);
    }

    public String getFileName() throws IOException {
        return parser.getGroupLink()+ ".txt";
    }

    @Override
    protected Void call() throws Exception {
        int lengthVKID = parser.getMembersCount();
        System.out.println(lengthVKID);
            try (FileWriter fw = new FileWriter(new File(getFileName()))) {
                while (count < lengthVKID) {
                    ArrayList<String> vkid = parser.getLinksList();
                    for (String id_vk : vkid) {
                        fw.write(id_vk);
                        count++;
                    }
                    updateMessage(String.valueOf(count));
                    updateProgress(count, lengthVKID);
                    if (count % 1000 == 0) {
                        parser.setOffset(offset += 1000);
                        Thread.sleep(221);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        return null;
    }
}