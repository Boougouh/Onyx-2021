package cn.newcraft.system.bukkit.packet;

import org.bukkit.entity.Player;

public abstract class PacketSendEvent extends PacketListener {

    public abstract void onWrite(Player p, Packet packet);

}
