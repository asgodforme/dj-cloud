package mq.quickstart.nettyctmq.domain;

import java.util.Map;

public class MethodFrame extends AbstractPayload {

    /**
     * 类名
     */
    private short classId;
    /**
     * 方法名
     */
    private short methodId;

    /**
     * 参数
     */
    private Map<String, Object> arguments;

    public short getClassId() {
        return classId;
    }

    public void setClassId(short classId) {
        this.classId = classId;
    }

    public short getMethodId() {
        return methodId;
    }

    public void setMethodId(short methodId) {
        this.methodId = methodId;
    }

    public Map<String, Object> getArguments() {
        return arguments;
    }

    public void setArguments(Map<String, Object> arguments) {
        this.arguments = arguments;
    }

    @Override
    public String toString() {
        return "MethodFrame{" +
                "classId=" + classId +
                ", methodId=" + methodId +
                ", arguments=" + arguments +
                '}';
    }
}
