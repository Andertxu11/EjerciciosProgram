package main;

public class Main {

    private BibliotecaApp app;

    public Main() {
        app = new BibliotecaApp();
    }

    public void iniciar() {
        app.menu();
    }

    public static void main(String[] args) {
    	
        new Main().iniciar();
    }
}