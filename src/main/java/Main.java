import org.apache.openejb.config.sys.Tomee;
import org.apache.tomee.embedded.Configuration;
import org.apache.tomee.embedded.Container;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import static java.lang.Integer.parseInt;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Configuration configuration = new Configuration();
        configuration.dir("src\\tomee");
        configuration.setWebXml("web\\WEB-INF\\web.xml");
        configuration.http(8080);
        configuration.setWebResourceCached(true);
        configuration.getProperties();
        try (final Container container = new Container(configuration).deployClasspathAsWebApp()) {
            System.out.println("Started on http://localhost:" + container.getConfiguration().getHttpPort());
            new CountDownLatch(1).await();
        }
    }
}
