/**
 * Copyright (c) Lambda Innovation, 2013-2015
 * 本作品版权由Lambda Innovation所有。
 * http://www.li-dev.cn/
 *
 * This project is open-source, and it is distributed under 
 * the terms of GNU General Public License. You can modify
 * and distribute freely as long as you follow the license.
 * 本项目是一个开源项目，且遵循GNU通用公共授权协议。
 * 在遵照该协议的情况下，您可以自由传播和修改。
 * http://www.gnu.org/licenses/gpl.html
 */
package cn.academy.energy.block;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * @author WeathFolD
 */
public class ContainerNode extends Container {
    
    final double STEP = 17.230769230769230769230769230769;

    public final TileNode node;

    public ContainerNode(TileNode _node, EntityPlayer player) {
        node = _node;
        initInventory(player.inventory);
    }
    
    private void initInventory(InventoryPlayer inv) {
        this.addSlotToContainer(new Slot(node, 0, 24, 73));
        this.addSlotToContainer(new Slot(node, 1, 136, 37));
        
        for(int i = 0; i < 9; ++i) {
            addSlotToContainer(new Slot(inv, i, (int) Math.round(10.49 + i * STEP), 152));
        }
        
        for(int i = 1; i < 4; ++i) {
            for(int j = 0; j < 9; ++j) {
                int slot = (4 - i) * 9 + j;
                addSlotToContainer(new Slot(inv, slot, (int) Math.round(10.49 + j * STEP), 
                        (int) Math.round(148 - i * STEP)));
            }
        }
    }
    
    /**
     * This already become a template...
     */
    public ItemStack transferStackInSlot(EntityPlayer player, int id) {
        ItemStack stack = null;
        Slot slot = (Slot)this.inventorySlots.get(id);

        if (slot != null && slot.getHasStack()) {
            ItemStack stack1 = slot.getStack();
            stack = stack1.copy();

            if (id < 2) { //tileInv->playerInv
                if (!this.mergeItemStack(stack1, 2, this.inventorySlots.size(), true))
                    return null;
            } else if (!this.mergeItemStack(stack1, 0, 2, false)) { //playerInv->tileInv
                return null;
            }

            if (stack1.stackSize == 0) {
                slot.putStack((ItemStack)null);
            } else {
                slot.onSlotChanged();
            }
        }

        return stack;
    }

    @Override
    public boolean canInteractWith(EntityPlayer var1) {
        return var1.getDistanceSq(node.xCoord + .5, node.yCoord + .5, node.zCoord + .5) < 64.0;
    }

}