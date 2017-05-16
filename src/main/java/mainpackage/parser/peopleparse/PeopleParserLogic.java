package mainpackage.parser.peopleparse;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import mainpackage.view.login.AuthorizationOnVkCom;

class PeopleParserLogic {
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

    String getLink() {
        return groupLink;
    }

    void setLink(String personLink) {
        this.groupLink = personLink;
    }

}