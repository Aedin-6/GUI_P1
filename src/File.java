public class File
{
    int hashId;
    String fileId = "F";
    static int counter;

    public File()
    {
        counter++;
        hashId = counter;
        fileId = fileId + hashId;
    }
}
