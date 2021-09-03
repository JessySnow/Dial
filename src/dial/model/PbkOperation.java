/* All operation about pbk file */

package dial.model;

public interface PbkOperation {
    String target_path = "C:\\Users\\"+ System.getProperty("user.name") + "\\AppData\\Roaming\\Microsoft\\Network\\Connections\\Pbk\\rasphone.pbk";
    String source_path = "resources\\pbk\\rasphone-demo.pbk";

    boolean scanPbk();
    void copyPbk();
    void createPbk();
}
