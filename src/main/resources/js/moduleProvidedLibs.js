// Extra libs
globalThis.ArrayList = Java.type("java.util.ArrayList");
globalThis.HashMap = Java.type("java.util.HashMap");
globalThis.Keyboard = Java.type("org.lwjgl.input.Keyboard");
globalThis.ReflectionHelper = Java.type("net.minecraftforge.fml.relauncher.ReflectionHelper");

// Triggers
globalThis.TriggerRegister = Java.type("com.chattriggers.ctjs.engine.langs.js.JSRegister").INSTANCE;
globalThis.Priority = Java.type("com.chattriggers.ctjs.triggers.OnTrigger").Priority;
//#if MC<=10809
globalThis.InteractAction = Java.type("net.minecraftforge.event.entity.player.PlayerInteractEvent").Action;
//#else
//$$ globalThis.InteractAction = Java.type("com.chattriggers.ctjs.minecraft.listeners.ClientListener").INSTANCE.PlayerInteractAction;
//#endif

// Libraries
globalThis.ChatLib = Java.type("com.chattriggers.ctjs.minecraft.libs.ChatLib");
globalThis.EventLib = Java.type("com.chattriggers.ctjs.minecraft.libs.EventLib");

globalThis.Renderer = Java.type("com.chattriggers.ctjs.minecraft.libs.renderer.Renderer");
globalThis.Shape = Java.type("com.chattriggers.ctjs.minecraft.libs.renderer.Shape");
globalThis.Rectangle = Java.type("com.chattriggers.ctjs.minecraft.libs.renderer.Rectangle");
globalThis.Text = Java.type("com.chattriggers.ctjs.minecraft.libs.renderer.Text");
globalThis.Image = Java.type("com.chattriggers.ctjs.minecraft.libs.renderer.Image");

globalThis.Tessellator = Java.type("com.chattriggers.ctjs.minecraft.libs.Tessellator");
globalThis.FileLib = Java.type("com.chattriggers.ctjs.minecraft.libs.FileLib");
globalThis.MathLib = Java.type("com.chattriggers.ctjs.minecraft.libs.MathLib");

// Objects
globalThis.Display = Java.type("com.chattriggers.ctjs.engine.langs.js.JSDisplay");
globalThis.DisplayLine = Java.type("com.chattriggers.ctjs.engine.langs.js.JSDisplayLine");
globalThis.DisplayHandler = Java.type("com.chattriggers.ctjs.minecraft.objects.display.DisplayHandler");
globalThis.Gui = Java.type("com.chattriggers.ctjs.engine.langs.js.JSGui");
globalThis.Message = Java.type("com.chattriggers.ctjs.minecraft.objects.message.Message");
globalThis.TextComponent = Java.type("com.chattriggers.ctjs.minecraft.objects.message.TextComponent");
globalThis.Book = Java.type("com.chattriggers.ctjs.minecraft.objects.Book");
globalThis.KeyBind = Java.type("com.chattriggers.ctjs.minecraft.objects.KeyBind");
globalThis.Image = Java.type("com.chattriggers.ctjs.minecraft.libs.renderer.Image");
globalThis.Sound = Java.type("com.chattriggers.ctjs.minecraft.objects.Sound");
globalThis.PlayerMP = Java.type("com.chattriggers.ctjs.minecraft.wrappers.objects.PlayerMP");

// Wrappers
globalThis.Client = Java.type("com.chattriggers.ctjs.minecraft.wrappers.Client");
globalThis.Player = Java.type("com.chattriggers.ctjs.minecraft.wrappers.Player");
globalThis.World = Java.type("com.chattriggers.ctjs.minecraft.wrappers.World");
globalThis.Server = Java.type("com.chattriggers.ctjs.minecraft.wrappers.Server");
globalThis.Inventory = Java.type("com.chattriggers.ctjs.minecraft.wrappers.objects.inventory.Inventory");
globalThis.TabList = Java.type("com.chattriggers.ctjs.minecraft.wrappers.TabList");
globalThis.Scoreboard = Java.type("com.chattriggers.ctjs.minecraft.wrappers.Scoreboard");
globalThis.CPS = Java.type("com.chattriggers.ctjs.minecraft.wrappers.CPS");
globalThis.Item = Java.type("com.chattriggers.ctjs.minecraft.wrappers.objects.inventory.Item");
globalThis.NBTBase = Java.type("com.chattriggers.ctjs.minecraft.wrappers.objects.inventory.nbt.NBTBase");
globalThis.NBTTagCompound = Java.type("com.chattriggers.ctjs.minecraft.wrappers.objects.inventory.nbt.NBTTagCompound");
globalThis.NBTTagList = Java.type("com.chattriggers.ctjs.minecraft.wrappers.objects.inventory.nbt.NBTTagList");
globalThis.Block = Java.type("com.chattriggers.ctjs.minecraft.wrappers.objects.block.Block");
globalThis.BlockFace = Java.type("com.chattriggers.ctjs.minecraft.wrappers.objects.block.BlockFace");
globalThis.BlockPos = Java.type("com.chattriggers.ctjs.minecraft.wrappers.objects.block.BlockPos");
globalThis.BlockType = Java.type("com.chattriggers.ctjs.minecraft.wrappers.objects.block.BlockType");
globalThis.Sign = Java.type("com.chattriggers.ctjs.minecraft.wrappers.objects.block.Sign");
globalThis.Vec3i = Java.type("com.chattriggers.ctjs.minecraft.wrappers.objects.block.Vec3i");
globalThis.Entity = Java.type("com.chattriggers.ctjs.minecraft.wrappers.objects.entity.Entity");
globalThis.PlayerMP = Java.type("com.chattriggers.ctjs.minecraft.wrappers.objects.entity.PlayerMP");
globalThis.Action = Java.type("com.chattriggers.ctjs.minecraft.wrappers.objects.inventory.action.Action");
globalThis.ClickAction = Java.type("com.chattriggers.ctjs.minecraft.wrappers.objects.inventory.action.ClickAction");
globalThis.DragAction = Java.type("com.chattriggers.ctjs.minecraft.wrappers.objects.inventory.action.DragAction");
globalThis.DropAction = Java.type("com.chattriggers.ctjs.minecraft.wrappers.objects.inventory.action.DropAction");
globalThis.KeyAction = Java.type("com.chattriggers.ctjs.minecraft.wrappers.objects.inventory.action.KeyAction");
globalThis.Particle = Java.type("com.chattriggers.ctjs.minecraft.wrappers.objects.Particle");

// Triggers
globalThis.OnChatTrigger = Java.type("com.chattriggers.ctjs.triggers.OnChatTrigger");
globalThis.OnCommandTrigger = Java.type("com.chattriggers.ctjs.triggers.OnCommandTrigger");
globalThis.OnRegularTrigger = Java.type("com.chattriggers.ctjs.triggers.OnRegularTrigger");
globalThis.OnRenderTrigger = Java.type("com.chattriggers.ctjs.triggers.OnRenderTrigger");
globalThis.OnSoundPlayTrigger = Java.type("com.chattriggers.ctjs.triggers.OnSoundPlayTrigger");
globalThis.OnStepTrigger = Java.type("com.chattriggers.ctjs.triggers.OnStepTrigger");
globalThis.OnTrigger = Java.type("com.chattriggers.ctjs.triggers.OnTrigger");

// Misc
globalThis.Config = Java.type("com.chattriggers.ctjs.utils.Config").INSTANCE;
globalThis.ChatTriggers = Java.type("com.chattriggers.ctjs.Reference");
/*End Built in Vars */

// GL
globalThis.GlStateManager = Java.type("net.minecraft.client.renderer.GlStateManager");
globalThis.GL11 = Java.type("org.lwjgl.opengl.GL11");
globalThis.GL12 = Java.type("org.lwjgl.opengl.GL12");
globalThis.GL13 = Java.type("org.lwjgl.opengl.GL13");
globalThis.GL14 = Java.type("org.lwjgl.opengl.GL14");
globalThis.GL15 = Java.type("org.lwjgl.opengl.GL15");
globalThis.GL20 = Java.type("org.lwjgl.opengl.GL20");
globalThis.GL21 = Java.type("org.lwjgl.opengl.GL21");
globalThis.GL30 = Java.type("org.lwjgl.opengl.GL30");
globalThis.GL31 = Java.type("org.lwjgl.opengl.GL31");
globalThis.GL32 = Java.type("org.lwjgl.opengl.GL32");
globalThis.GL33 = Java.type("org.lwjgl.opengl.GL33");
globalThis.GL40 = Java.type("org.lwjgl.opengl.GL40");
globalThis.GL41 = Java.type("org.lwjgl.opengl.GL41");
globalThis.GL42 = Java.type("org.lwjgl.opengl.GL42");
globalThis.GL43 = Java.type("org.lwjgl.opengl.GL43");
globalThis.GL44 = Java.type("org.lwjgl.opengl.GL44");
globalThis.GL45 = Java.type("org.lwjgl.opengl.GL45");

globalThis.cancel = function (event) {
    try {
        EventLib.cancel(event);
    } catch (err) {
        if (!event.isCancelable()) return;
        event.setCanceled(true);
    }
};

globalThis.register = function (triggerType, methodName) {
    return TriggerRegister.register(triggerType, methodName);
};

// String prototypes
String.prototype.addFormatting = function () {
    return ChatLib.addColor(this);
};

String.prototype.addColor = String.prototype.addFormatting;

String.prototype.removeFormatting = function () {
    return ChatLib.removeFormatting(this);
};

String.prototype.replaceFormatting = function () {
    return ChatLib.replaceFormatting(this);
};

// animation
globalThis.easeOut = function (start, finish, speed, jump) {
    if (!jump) jump = 1;

    if (Math.floor(Math.abs(finish - start) / jump) > 0) {
        return start + (finish - start) / speed;
    } else {
        return finish;
    }
};

Number.prototype.easeOut = function (to, speed, jump) {
    return easeOut(this, to, speed, jump);
};

globalThis.easeColor = function (start, finish, speed, jump) {
    return Renderer.color(
        easeOut((start >> 16) & 0xFF, (finish >> 16) & 0xFF, speed, jump),
        easeOut((start >> 8) & 0xFF, (finish >> 8) & 0xFF, speed, jump),
        easeOut(start & 0xFF, finish & 0xFF, speed, jump),
        easeOut((start >> 24) & 0xFF, (finish >> 24) & 0xFF, speed, jump)
    );
};

Number.prototype.easeColor = function (to, speed, jump) {
    return easeColor(this, to, speed, jump);
};
