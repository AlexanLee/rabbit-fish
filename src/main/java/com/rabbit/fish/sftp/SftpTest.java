package com.rabbit.fish.sftp;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 *
 * @author lijianli
 * @date 2017/8/11
 */
public class SftpTest {
    static Session session = null;
    static Channel channel = null;

    public static ChannelSftp getChannel(Map<String, String> sftpDetails, int timeout) throws JSchException {
        String ftpHost = sftpDetails.get(SFTPConstants.SFTP_REQ_HOST);
        String port = sftpDetails.get(SFTPConstants.SFTP_REQ_PORT);
        String ftpUserName = sftpDetails.get(SFTPConstants.SFTP_REQ_USERNAME);
        String ftpPassword = sftpDetails.get(SFTPConstants.SFTP_REQ_PASSWORD);

        System.out.println(ftpHost);
        System.out.println(port);
        System.out.println(ftpUserName);
        System.out.println(ftpPassword);


        int ftpPort = SFTPConstants.SFTP_DEFAULT_PORT;
        if (port != null && !port.equals("")) {
            ftpPort = Integer.valueOf(port);
        }

        JSch jsch = new JSch();
        session = jsch.getSession(ftpUserName, ftpHost, ftpPort);
        if (ftpPassword != null) {
            session.setPassword(ftpPassword);
        }
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);
        session.setTimeout(timeout);
        session.connect();
        channel = session.openChannel("sftp");
        channel.connect();
        return (ChannelSftp) channel;
    }

    public static void closeChannel() throws Exception {
        if (channel != null) {
            channel.disconnect();
        }
        if (session != null) {
            session.disconnect();
        }
    }
    public static void main(String[] args) throws Exception {

        Map<String, String> sftpDetails = new HashMap<>();
//        SFTP_HOST=114.251.243.97
//        SFTP_PORT=20000
//        SFTP_USERNAME=LBYDTEST
//        SFTP_PASSWORD=w002Sm5k
        // 设置主机ip，端口，用户名，密码
        sftpDetails.put(SFTPConstants.SFTP_REQ_HOST, "114.251.243.97");
        sftpDetails.put(SFTPConstants.SFTP_REQ_USERNAME, "LBYDTEST");
        sftpDetails.put(SFTPConstants.SFTP_REQ_PASSWORD, "w002Sm5k");
        sftpDetails.put(SFTPConstants.SFTP_REQ_PORT, "20000");

        String src = "/Users/lijianli/Desktop/tomcat.jpeg"; // 本地文件名
        String dst = "/LBYD01"; // 目标文件名

        ChannelSftp chSftp = getChannel(sftpDetails, 60000);

        chSftp.put(src, dst, ChannelSftp.OVERWRITE);

        // chSftp.put(new FileInputStream(src), dst, ChannelSftp.OVERWRITE);

        chSftp.quit();
        closeChannel();
    }
}
