package editor;

public class TestEditor {

    public static void main(String[] args) {
        DocumentInvoker myDocument = new DocumentInvoker("callmemaybe");
        myDocument.Write("Hey I just met you");
        myDocument.Write("And this is crazy");
        myDocument.Write("But here’s my number");
        myDocument.Write("So call me maybe");
        myDocument.Erase("And this is crazy");
        myDocument.Bold(1);
        myDocument.Undo();
        myDocument.Redo();
        System.out.println(myDocument.Read());
        myDocument.printCommandBuffer();
    }
}
