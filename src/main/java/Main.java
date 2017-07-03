public class Main {

    public static void main(String[] args) {

        String searchUrl = "https://www.reddit.com/r/EarthPorn/top/";
        Emulator emulator = new Emulator(searchUrl);
        emulator.printTitles();
    }
}
