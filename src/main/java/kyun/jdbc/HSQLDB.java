package kyun.jdbc;

import java.io.IOException;
import java.util.Properties;

import org.hsqldb.Server;
import org.hsqldb.persist.HsqlProperties;
import org.hsqldb.server.ServerAcl.AclFormatException;
import org.springframework.context.SmartLifecycle;

public class HSQLDB implements SmartLifecycle {

    private HsqlProperties properties;
    private Server server;
    private boolean running = false;

    public HSQLDB(Properties props) {
        properties = new HsqlProperties(props);
    }

    @Override
    public boolean isRunning() {
        if (server != null)
            server.checkRunning(running);
        return running;
    }

    @Override
    public void start() {
        if (server == null) {
            server = new Server();

            try {
                server.setProperties(properties);
                server.start();
                running = true;
            } catch (AclFormatException afe) {
                afe.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void stop() {
        if (server != null) {
            server.stop();
            running = false;
        }
    }

    @Override
    public int getPhase() {
        return 0;
    }

    @Override
    public boolean isAutoStartup() {
        return true;
    }

    @Override
    public void stop(Runnable runnable) {
        stop();
        runnable.run();
    }
}