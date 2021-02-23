package at.markus;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.ByteArrayOutputStream;

public class Main {
    private static Config Config = new Config();

    public static void main(String[] args) throws Exception {
        String config[] = Config.configDatas();
        sshConnection(config[0], config[1], config[2], Integer.parseInt(config[3]), config[4]);
    }

    public static void sshConnection(final String username, final String password, final String host, final int port, final String command) throws Exception {
        Session session = null;
        ChannelExec channel = null;
        try {
            session = new JSch().getSession(username, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect(100000);
            channel = (ChannelExec)session.openChannel("exec");

            channel.setCommand(command);

            final ByteArrayOutputStream responseStream = new ByteArrayOutputStream();
            channel.setOutputStream(responseStream);
            channel.connect();
            while (channel.isConnected()) {
                Thread.sleep(100L);
            }
            final String responseString = new String(responseStream.toByteArray());
            System.out.println(responseString);
        }
        finally {
            if (session != null) {
                session.disconnect();
            }
            if (channel != null) {
                channel.disconnect();
            }
        }
    }
}
