package net.blastmc.onyx.bukkit.packet;

import org.bukkit.entity.Player;

public abstract class PacketReadEvent extends PacketListener{

    public abstract void onRead(Player p, Packet packet);

}
