package cn.academy.medicine.buffs;


import cn.academy.ability.api.data.CPData;
import cn.academy.medicine.api.BuffApplyData;
import cn.academy.medicine.api.BuffPerTick;
import cn.academy.medicine.api.RegBuff;
import cn.lambdalib.annoreg.core.Registrant;
import net.minecraft.entity.player.EntityPlayer;

@Registrant
@RegBuff
public class BuffCPRecovery extends BuffPerTick {

    public BuffCPRecovery(float perTick){
        super("cp_recovery");
        this.perTick = perTick;
    }

    @Override
    public void onBegin(EntityPlayer player) {

    }

    @Override
    public void onTick(EntityPlayer player, BuffApplyData applyData){
        CPData cpData = CPData.get(player);
        cpData.setCP(cpData.getCP() + perTick);
    }

    @Override
    public void onEnd(EntityPlayer player) {

    }

}