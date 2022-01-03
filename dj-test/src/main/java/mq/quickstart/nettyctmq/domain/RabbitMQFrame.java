package mq.quickstart.nettyctmq.domain;

public class RabbitMQFrame {

    /**
     * 帧类型
     */
    private byte frameType;
    /**
     * 信道编号
     */
    private short channelNo;
    /**
     * 帧大小
     */
    private long frameLength;

    /**
     * 帧有效负载
     */
    private AbstractPayload payload;

    /**
     * 结束标记字符
     */
    private String endFlg = "ce";

    public byte getFrameType() {
        return frameType;
    }

    public void setFrameType(byte frameType) {
        this.frameType = frameType;
    }

    public short getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(short channelNo) {
        this.channelNo = channelNo;
    }

    public long getFrameLength() {
        return frameLength;
    }

    public void setFrameLength(long frameLength) {
        this.frameLength = frameLength;
    }

    public AbstractPayload getPayload() {
        return payload;
    }

    public void setPayload(AbstractPayload payload) {
        this.payload = payload;
    }

    public String getEndFlg() {
        return endFlg;
    }

    public void setEndFlg(String endFlg) {
        this.endFlg = endFlg;
    }

    @Override
    public String toString() {
        return "RabbitMQFrame{" +
                "frameType=" + frameType +
                ", channelNo=" + channelNo +
                ", frameLength=" + frameLength +
                ", payload=" + payload +
                ", endFlg='" + endFlg + '\'' +
                '}';
    }
}
