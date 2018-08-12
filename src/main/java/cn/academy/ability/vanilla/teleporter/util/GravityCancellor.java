package cn.academy.ability.vanilla.teleporter.util;

import cn.lambdalib2.util.ClientUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;

/**
 * @author WeAthFolD
 */
public class GravityCancellor extends LIHandler<ClientTickEvent> {

    private final EntityPlayer p;
    private final int ticks;
    private int ticker = 0;

    public GravityCancellor(EntityPlayer _p, int _ticks) {
        p = _p;
        ticks = _ticks;
    }

    @Override
    protected boolean onEvent(ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.START && ClientUtils.isPlayerPlaying()) {
            if (p.isDead || (++ticker == ticks)) {
                this.setDead();
            } else {
                if (!p.capabilities.isFlying) {
                    if (!p.onGround) {
                        p.motionY += 0.072;
                    }
                }
            }
        }
        return true;
    }

}