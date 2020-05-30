package cn.newcraft.system.bukkit.packet;

public class Packet {

    private Object packet;
    private String name;

    public Packet(Object packet) {
        this.packet = packet;
        this.name = packet.getClass().getSimpleName();
    }

    public Object getPacket() {
        return packet;
    }

    public String getName() {
        return name;
    }

}
