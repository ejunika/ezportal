package com.ez.portal.connection.ftp.factory;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;

public class FTPConnectionFactory {

    private String ftpHost;
    private String ftpUser;
    private String ftpPassword;
    private Session session;
    private Channel channel;
    private ChannelSftp channelSftp;
    private Boolean isSecure;
    
    public FTPConnectionFactory() {
        super();
    }
    
    public FTPConnectionFactory configure() {
        return this;
    }
    
    public FTPConnectionFactory add(String property, String value) {
        return this;
    }

    public String getFtpHost() {
        return ftpHost;
    }

    public void setFtpHost(String ftpHost) {
        this.ftpHost = ftpHost;
    }

    public String getFtpUser() {
        return ftpUser;
    }

    public void setFtpUser(String ftpUser) {
        this.ftpUser = ftpUser;
    }

    public String getFtpPassword() {
        return ftpPassword;
    }

    public void setFtpPassword(String ftpPassword) {
        this.ftpPassword = ftpPassword;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public ChannelSftp getChannelSftp() {
        return channelSftp;
    }

    public void setChannelSftp(ChannelSftp channelSftp) {
        this.channelSftp = channelSftp;
    }

    public Boolean getIsSecure() {
        return isSecure;
    }

    public void setIsSecure(Boolean isSecure) {
        this.isSecure = isSecure;
    }
    
    public Session openSession() {
        return null;
    }
    
    public Channel openChannel() {
        return null;
    }
    
}
