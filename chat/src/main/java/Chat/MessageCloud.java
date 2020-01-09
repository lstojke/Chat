package Chat;

import javafx.scene.image.*;
import java.io.*;
import java.nio.ByteBuffer;

public class MessageCloud implements Serializable {
    private String text;
    transient private Image image;
    private String name;
    transient private byte[] forImages;
    public boolean todelete;
    public int pos;
    public MessageCloud(String name, String text, Image image){
        this.name=name;
        this.text=text;
        this.image=image;
        todelete = false;
        pos = 0;
        if(image != null){
        forImages = new byte[(int)image.getWidth() * (int)image.getHeight() *4];}
    }
    public boolean equals(MessageCloud msg){
        if(text.equals(msg.getText())
            //image.equals(msg.getImage())
            ){
            return true;}
        return false;
    }

    private void readObject(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        inputStream.defaultReadObject(); // zakomentowana jest implementacja przesylania obrazkow ktora nie dziala
        /*if(inputStream.available() >= forImages.length){
        inputStream.readFully(forImages);
        WritableImage wImage = new WritableImage(256,256);
        PixelFormat pixelFormat = wImage.getPixelWriter().getPixelFormat();
        wImage.getPixelWriter().setPixels(0,0,256,256,pixelFormat,forImages,0,256*4);
        image = wImage;}*/
    }
    private void writeObject(ObjectOutputStream outputStream) throws IOException{
        /*if(image != null) {
            int w = (int) image.getWidth();
            int h = (int) image.getHeight();
            PixelReader pixelReader = image.getPixelReader();
            PixelFormat f = pixelReader.getPixelFormat();
            ByteBuffer buffer = ByteBuffer.allocate(w * 4 * h);
            pixelReader.getPixels(0, 0, w, h, f.getByteBgraInstance(), buffer, w * 4);
            outputStream.write(buffer.array());
            }*/
        outputStream.defaultWriteObject();

    }


    public String getText(){return text;}
    public String getName(){return name;}
    public Image getImage(){return image;}
}
