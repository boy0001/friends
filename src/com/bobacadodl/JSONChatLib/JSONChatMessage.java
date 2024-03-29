package com.bobacadodl.JSONChatLib;

import java.util.List;

import net.minecraft.server.v1_7_R4.ChatSerializer;
import net.minecraft.server.v1_7_R4.PacketPlayOutChat;

import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * User: bobacadodl
 * Date: 10/27/13
 * Time: 8:03 PM
 */
public class JSONChatMessage {
    private JSONObject chatObject;

    public JSONChatMessage(String text, JSONChatColor color, List<JSONChatFormat> formats) {
        chatObject = new JSONObject();
        chatObject.put("text", text);
        if (color != null) {
            chatObject.put("color", color.getColorString());
        }
        if (formats != null) {
            for (JSONChatFormat format : formats) {
                chatObject.put(format.getFormatString(), true);
            }
        }
    }

    public void addExtra(JSONChatExtra extraObject) {
        if (!chatObject.containsKey("extra")) {
            chatObject.put("extra", new JSONArray());
        }
        JSONArray extra = (JSONArray) chatObject.get("extra");
        extra.add(extraObject.toJSON());
        chatObject.put("extra", extra);
    }

    public void sendToPlayer(Player player) {
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(new PacketPlayOutChat(ChatSerializer.a(chatObject.toJSONString()), true));

    }

    public String toString() {
        return chatObject.toJSONString();
    }
}
