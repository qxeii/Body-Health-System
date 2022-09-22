package xyz.srgnis.bodyhealthsystem.body;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;


public abstract class BodyPart {
    private float maxHealth;
    private float health;
    private LivingEntity entity;
    private Identifier identifier;

    protected int armorSlot;
    protected DefaultedList<ItemStack> armorList;

    public BodyPart(float maxHealth, float health, LivingEntity entity, Identifier identifier) {
        this.maxHealth = maxHealth;
        this.health = health;
        this.entity = entity;
        this.identifier = identifier;
    }

    public float takeDamage(float amount, DamageSource source){
        //armor
        //modifyAppliedDamage
        //Absobtion

        float sub = health - amount;
        health = Math.max(0, sub);

        return Math.max(0, -sub);
    }

    public void heal(){
        health = maxHealth;
    }

    public float heal(float amount){
        float add = health + amount;
        health = Math.min(maxHealth, add);
        return add - health;
    }

    public ItemStack getAffectedArmor(){
        return armorList.get(armorSlot);
    }

    public void setHealth(float health) { this.health = health;}
    public float getHealth() {
        return health;
    }
    public float getMaxHealth() {
        return maxHealth;
    }
    public void setMaxHealth(float maxHealth) {
        this.maxHealth = maxHealth;
    }
    public Entity getEntity() {
        return entity;
    }
    public void setEntity(LivingEntity entity) {
        this.entity = entity;
    }
    public Identifier getIdentifier() {
        return identifier;
    }
    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    public void writeToNbt(NbtCompound nbt){
        NbtCompound new_nbt = new NbtCompound();
        new_nbt.putFloat("health", health);
        new_nbt.putFloat("maxHealth", maxHealth);
        nbt.put(identifier.toString(), new_nbt);
    }

    public void readFromNbt(NbtCompound nbt){
        health = nbt.getFloat("health");
        maxHealth = nbt.getFloat("maxHealth");
    }

    @Override
    public String toString() {
        return identifier + " MaxHP: " + maxHealth + " HP " + health + "\n";
    }
}
