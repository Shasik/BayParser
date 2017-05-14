package mainpackage.personparse;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.queries.friends.FriendsGetOrder;
import com.vk.api.sdk.queries.users.UsersNameCase;
import mainpackage.view.login.AuthorizationOnVkCom;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class PersonParserLogic {
    private volatile Integer personLink;

    private VkApiClient vkApiClient = AuthorizationOnVkCom.getVkApiClient();

    public Integer getPersonLink() {
        return personLink;
    }

    public void setPersonLink(Integer personLink) {
        this.personLink = personLink;
    }

    ArrayList<String> getLinksList() throws IOException, ClientException, ApiException {
        UserActor actor = AuthorizationOnVkCom.getActor();

        ArrayList<String> linksList = new ArrayList<>();

        List<Integer> linkList = vkApiClient.friends().get(actor).userId(getPersonLink()).order(FriendsGetOrder.NAME).nameCase(UsersNameCase.NOMINATIVE).execute().getItems();

        for (Integer friend : linkList) {
            linksList.add("https://vk.com/id"+friend+"\r\n");
        }
        return linksList;
     }
}