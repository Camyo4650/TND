package p4.warmongers;

import org.lwjgl.glfw.GLFW;
import p4.warmongers.engine.io.Input;
import p4.warmongers.engine.io.Window;

public class Game implements Runnable {

    private Window window;
    public Thread gameThread;
    public static final int WIDTH = 1280, HEIGHT = 720;

    public static void main(String[] args) {
        new Game().start();
    }

    public void start() {
        gameThread = new Thread(this, "game");
        gameThread.start();
    }

    public void init() {
        window = new Window(WIDTH,HEIGHT,"WARMONGERS");
        window.setBackgroundColor(0.8f,0.4f,1.0f,1.0f);
        window.create();
    }

    public void run() {
        init();
        while (!window.shouldClose() && !Input.isKeyDown(GLFW.GLFW_KEY_ESCAPE)) {
            this.update();
            this.render();
            if (Input.isKeyDown(GLFW.GLFW_KEY_F11)) window.setFullscreen(!window.isFullscreen());
        }
        window.dispose();
    }

    public void update() {
        window.update();

        if (Input.isButtonDown(GLFW.GLFW_MOUSE_BUTTON_LEFT)) System.out.println("X: " + Input.getScrollX() + ", Y: " + Input.getScrollY());
    }

    public void render() {
        window.swapBuffers();
    }
}
