package com.dj.cloud.test.io.protostuff.util;

import com.dj.cloud.test.io.protostuff.domain.MsgInfo;

public class MsgUtil {

    public static MsgInfo buildMsg(String channelId, String msgContent) {
        return new MsgInfo(channelId,msgContent);
    }
}
