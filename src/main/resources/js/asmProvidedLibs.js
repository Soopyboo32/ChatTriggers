globalThis.Thread = Java.type("com.chattriggers.ctjs.engine.langs.js.JSWrappedThread");
globalThis.Console = Java.type("com.chattriggers.ctjs.engine.langs.js.JSLoader").INSTANCE.getConsole();

globalThis.sync = (func, lock) => new org.mozilla.javascript.Synchronizer(func, lock);

globalThis.setTimeout = function (func, delay) {
    new Thread(function () {
        Thread.sleep(delay);
        func();
    }).start();
};
