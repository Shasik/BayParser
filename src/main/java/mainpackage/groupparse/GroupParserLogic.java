package mainpackage.groupparse;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import mainpackage.view.login.AuthorizationOnVkCom;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class GroupParserLogic {
    private volatile String groupLink;

    private UserActor actor;

    private Integer offset = 0;

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    private VkApiClient vkApiClient = AuthorizationOnVkCom.getVkApiClient();

    String getGroupLink() {
        return groupLink;
    }

    void setGroupLink(String personLink) {
        this.groupLink = personLink;
    }

    ArrayList<String> getLinksList() throws IOException, ClientException, ApiException {
        ArrayList<String> linksList = new ArrayList<>();

        List<Integer> linkList = vkApiClient.groups().getMembers(actor).groupId(getGroupLink()).offset(getOffset()).execute().getItems();


        for (Integer friend : linkList) {
            linksList.add("https://vk.com/id"+friend+"\r\n");
        }
        return linksList;
    }

    Integer getMembersCount() throws ClientException, ApiException {
        actor = AuthorizationOnVkCom.getActor();
        return vkApiClient.groups().getMembers(actor).groupId(getGroupLink()).execute().getCount();
    }
}