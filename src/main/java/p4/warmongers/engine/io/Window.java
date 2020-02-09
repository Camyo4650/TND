package p4.warmongers.engine.io;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import p4.warmongers.Logger;

public class Window {
    private int width, height;
    private String title;
    private long window;
    public int frames;
    public long time;
    public Input input;

    public Window(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;
    }

    public void create() {
        if (!GLFW.glfwInit()) {
            Logger.logEvent("GLFW failed to initialize.");
        }

        input = new Input();
        window = GLFW.glfwCreateWindow(width,height,title,0,0);

        if (window == 0) {
            Logger.logEvent("Window failed to initiate.");
        }

        GLFWVidMode videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
        GLFW.glfwSetWindowPos(window,(videoMode.width()-width)/2, (videoMode.height()-height)/2);
        GLFW.glfwMakeContextCurrent(window);

        GLFW.glfwSetKeyCallback(window, input.getKeyboardCallBack());
        GLFW.glfwSetCursorPosCallback(window, input.getMouseMoveCallBack());
        GLFW.glfwSetMouseButtonCallback(window, input.getMouseButtonsCallBack());

        GLFW.glfwShowWindow(window);

        GLFW.glfwSwapInterval(1);
        time = System.currentTimeMillis();
    }

    public void update() {
        GLFW.glfwPollEvents();
        frames++;
        if (System.currentTimeMillis() > time + 1000) {
            GLFW.glfwSetWindowTitle(window, title + " | FPS: " + frames);
            time = System.currentTimeMillis();
            frames = 0;
        }
    }

    public void swapBuffers() {
        GLFW.glfwSwapBuffers(window);
    }

    public boolean shouldClose() {
        return GLFW.glfwWindowShouldClose(window);
    }

    public void dispose() {
        input.dispose();
        GLFW.glfwWindowShouldClose(window);
        GLFW.glfwDestroyWindow(window);
        GLFW.glfwTerminate();
    }
}
