package cn.fusionfuture.bugu.pojo.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author thomas
 * @version 1.0
 * @class UserRelationship
 * @description TODO
 * @date 2020/10/7 10:11 上午
 */
@Getter
@AllArgsConstructor
public enum UserRelationship {

    /*
     * 表示用户之间的关系
     **/
    FRIENDS(0, "互相关注"),
    FOLLOWED(1, "已关注"),
    FANS(2, "对方是你的粉丝"),
    NON(3, "未关注"),;

    private final Integer index;

    private final String relationship;

    public static String getRelationship(Integer index) {
        for (UserRelationship o : UserRelationship.values()) {
            if (o.getIndex().equals(index)) {
                return o.getRelationship();
            }
        }
        return null;
    }
}
