package cn.fusionfuture.bugu.utils.auth;

import java.util.LinkedList;
import java.util.List;

/**
 * @author thomas
 * @version 1.0
 * @class AuthUtil
 * @description TODO
 * @date 2020/9/16 2:14 下午
 */
public class AuthUtil {

    public static List<String> authNumToList(Integer num) {
        List<String> grantedList = new LinkedList<>();
        switch (num) {
            case 0:
                grantedList.add("admin");
                grantedList.add("employee");
                grantedList.add("user");
                break;
            case 1:
                grantedList.add("employee");
                grantedList.add("user");
                break;
            case 2:
                grantedList.add("user");
                break;
            default:
                grantedList = null;
                break;
        }
        return grantedList;
    }
}
