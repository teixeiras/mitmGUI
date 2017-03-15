package Mitmgui.Managers;

import javafx.scene.image.Image;
import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;
import org.pmw.tinylog.Logger;

import javax.imageio.ImageIO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

/**
 * Created by teixeiras on 14/03/2017.
 */
public class AppManager {

    public static Image getQRCode(String text) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        QRCode.from(text).to(ImageType.PNG).writeTo(out);

        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
        return new javafx.scene.image.Image(in);
    }

}
